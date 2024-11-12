package ch.supsi.connectfour.backend.business;

import javafx.scene.control.Label;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public interface GamesDataAccessInterface {
    Map<String, ArrayList<Label>> getGame(String fileName);
    boolean saveGame(Map<String, ArrayList<Label>> statoGriglia, int turno, String g1, String colorG1, String g2, String colorG2);
    boolean saveGameWithName(Map<String, ArrayList<Label>> statoGriglia, int turno, String name, String g1, String colorG1, String g2, String colorG2);
    Path getGamesPath();
}
