package ch.supsi.connectfour.backend.application;

import javafx.scene.control.Label;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class ConnectFourBackendController {
    private static ConnectFourBackendController myself;

    private final PreferencesController userPreferencesController;
    private final TranslationsController translationsController;
    private final GamesController gamesController;

    protected ConnectFourBackendController() {
        this.userPreferencesController = PreferencesController.getInstance();
        this.translationsController = TranslationsController.getInstance();
        this.gamesController = GamesController.getInstance();
    }

    public static ConnectFourBackendController getInstance() {
        if (myself == null) {
            myself = new ConnectFourBackendController();
        }
        return myself;
    }

    public String translate(String key) {
        return translationsController.translate(key);
    }
    public void changeLanguage(String languageTag) {
        translationsController.changeLanguage(languageTag);
        userPreferencesController.changeLanguage(languageTag);
    }
    public boolean saveGame(Map<String, ArrayList<Label>> statoGriglia, int turno, String g1, String colorG1, String g2, String colorG2){
        return gamesController.saveGame(statoGriglia, turno, g1, colorG1, g2, colorG2);
    }
    public Map<String, ArrayList<Label>> getGame(String fileName) {
        return gamesController.getGame(fileName);
    }
    public boolean saveGameWithName(Map<String, ArrayList<Label>> statoGriglia, int turno, String name, String g1, String colorG1, String g2, String colorG2){
        return gamesController.saveGameWithName(statoGriglia, turno, name, g1, colorG1, g2, colorG2);
    }
    public Path getGamesPath(){
        return gamesController.getGamesPath();
    }
    public String getCurrentLanguage(){
        return userPreferencesController.getCurrentLanguage();
    }
}
