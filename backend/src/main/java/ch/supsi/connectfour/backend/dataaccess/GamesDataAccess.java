package ch.supsi.connectfour.backend.dataaccess;

import ch.supsi.connectfour.backend.business.GamesDataAccessInterface;
import javafx.scene.control.Label;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import org.json.*;


public class GamesDataAccess implements GamesDataAccessInterface {
    private static final String userHomeDirectory = System.getProperty("user.home");
    private static final String preferencesDirectory = ".connectFour";
    private static final String gamesDirectory = ".games";
    private String currentGame = "";
    public static GamesDataAccess dao;

    protected GamesDataAccess() {
        createGamesDirectory();
    }

    // singleton instantiation of this data access object
    // guarantees only a single instance exists in the life of the application
    public static GamesDataAccess getInstance() {
        if (dao == null) {
            dao = new GamesDataAccess();
        }

        return dao;
    }
    private void createGamesDirectory() {
        try {
            Files.createDirectories(this.getGamesPath());

        } catch (IOException ignoredForDemoPurposes) {
            return;
        }
    }

    public Map<String, ArrayList<Label>> getGame(String fileName) {
        Map<String, ArrayList<Label>> statoGriglia = new HashMap<>();
        int turno = 0; // Variabile per memorizzare il turno
        String g1, colorG1, g2, colorG2;
        try {
            // Leggi il contenuto del file JSON
            String content = new String(Files.readAllBytes(getGamesPath(fileName)));
            JSONObject jsonStatoGriglia = new JSONObject(content);

            // Recupera il turno
            turno = jsonStatoGriglia.getInt("turno");
            g1 = jsonStatoGriglia.getString("g1");
            colorG1 = jsonStatoGriglia.getString("color1");
            g2 = jsonStatoGriglia.getString("g2");
            colorG2 = jsonStatoGriglia.getString("color2");

            // Itera attraverso le chiavi del JSONObject per recuperare lo stato della griglia
            for (String key : jsonStatoGriglia.keySet()) {
                if (!(key.equals("turno")||key.equals("g1")||key.equals("color1")||key.equals("g2")||key.equals("color2"))) { // Salta certe chiavi
                    JSONArray jsonArray = jsonStatoGriglia.getJSONArray(key);
                    ArrayList<Label> colonna = new ArrayList<>();

                    // Ricostruisci ogni Label con le sue proprietà
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonLabel = jsonArray.getJSONObject(i);
                        Label label = new Label();
                        label.setId(jsonLabel.optString("id"));
                        label.setText(jsonLabel.optString("text"));
                        String colorString = jsonLabel.optString("color");
                        if (!colorString.isEmpty()) {
                            label.setStyle(colorString); // Imposta lo stile che include il colore
                        }
                        colonna.add(label);
                    }

                    // Aggiungi la lista di Label alla mappa statoGriglia
                    statoGriglia.put(key, colonna);
                }
            }
        } catch (Exception e) {
            // ritorna nullo in caso di file/partita non trovata o altro errore
            return null;
        }
        // Aggiungi il turno alla mappa statoGriglia con una chiave speciale
        statoGriglia.put("infoGame", new ArrayList<>(Arrays.asList(new Label(String.valueOf(turno)), new Label(g1), new Label(colorG1), new Label(g2), new Label(colorG2))));

        return statoGriglia;
    }


    @Override
    public boolean saveGame(Map<String, ArrayList<Label>> statoGriglia, int turno, String g1, String colorG1, String g2, String colorG2) {
        JSONObject jToSave = this.getJsonToSave(statoGriglia, turno, g1, colorG1, g2, colorG2);
        // Salva il JSONObject in un file
        try {
            Files.write(getGamesPath(currentGame), jToSave.toString().getBytes());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private JSONObject getJsonToSave(Map<String, ArrayList<Label>> statoGriglia, int turno, String g1, String colorG1, String g2, String colorG2){
        JSONObject jsonStatoGriglia = new JSONObject();
        jsonStatoGriglia.put("turno", turno);
        jsonStatoGriglia.put("g1", g1);
        jsonStatoGriglia.put("color1", colorG1);
        jsonStatoGriglia.put("g2", g2);
        jsonStatoGriglia.put("color2", colorG2);

        for (Map.Entry<String, ArrayList<Label>> entry : statoGriglia.entrySet()) {
            String key = entry.getKey();
            ArrayList<Label> labels = entry.getValue();
            JSONArray jsonArray = new JSONArray();
            for (Label label : labels) {
                JSONObject jsonLabel = new JSONObject();
                jsonLabel.put("id", label.getId());
                jsonLabel.put("text", label.getText());
                jsonLabel.put("color", label.getStyle());
                jsonArray.put(jsonLabel);
            }
            jsonStatoGriglia.put(key, jsonArray);
        }
        return jsonStatoGriglia;
    }

    public Path getGamesPath() {
        return Path.of(userHomeDirectory, preferencesDirectory, gamesDirectory);
    }
    private Path getGamesPath(String name) {
        return Path.of(userHomeDirectory, preferencesDirectory, gamesDirectory, name);
    }


    public boolean saveGameWithName(Map<String, ArrayList<Label>> statoGriglia, int turno, String name, String g1, String colorG1, String g2, String colorG2) {
        JSONObject jToSave = this.getJsonToSave(statoGriglia, turno, g1, colorG1, g2, colorG2);

        // Salva il JSONObject in un file
        try {
            Files.write(getGamesPath(name), jToSave.toString().getBytes());
        } catch (IOException e) {
            return false;
        }
        currentGame = name;
        return true;
    }

}
