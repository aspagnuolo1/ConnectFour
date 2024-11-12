package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.business.GamesModel;
import javafx.scene.control.Label;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class GamesController {
    private static GamesController myself;

    private final GamesBusinessInterface gamesModel;

    protected GamesController() {
        this.gamesModel = GamesModel.getInstance();
    }

    public static GamesController getInstance() {
        if (myself == null) {
            myself = new GamesController();
        }

        return myself;
    }

    public Map<String, ArrayList<Label>> getGame(String fileName) {
        return gamesModel.getGame(fileName);
    }

    public boolean saveGame(Map<String, ArrayList<Label>> statoGriglia, int turno, String g1, String colorG1, String g2, String colorG2) {
        return gamesModel.saveGame(statoGriglia, turno, g1, colorG1, g2, colorG2);
    }
    public boolean saveGameWithName(Map<String, ArrayList<Label>> statoGriglia, int turno, String name, String g1, String colorG1, String g2, String colorG2){
        return gamesModel.saveGameWithName(statoGriglia, turno,  name, g1, colorG1, g2, colorG2);
    }
    public Path getGamesPath(){
        return gamesModel.getGamesPath();
    }
}
