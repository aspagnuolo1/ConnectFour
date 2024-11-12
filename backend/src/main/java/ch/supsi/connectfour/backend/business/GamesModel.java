package ch.supsi.connectfour.backend.business;

import ch.supsi.connectfour.backend.application.GamesBusinessInterface;
import ch.supsi.connectfour.backend.dataaccess.GamesDataAccess;
import javafx.scene.control.Label;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class GamesModel implements GamesBusinessInterface {

    private static GamesModel myself;

    private final GamesDataAccessInterface gamesDao;


    protected GamesModel() {
        this.gamesDao = GamesDataAccess.getInstance();
    }

    public static GamesModel getInstance() {
        if (myself == null) {
            myself = new GamesModel();
        }

        return myself;
    }

    @Override
    public Map<String, ArrayList<Label>> getGame(String fileName) {
        return gamesDao.getGame(fileName);
    }

    @Override
    public boolean saveGame(Map<String, ArrayList<Label>> statoGriglia, int turno, String g1, String colorG1, String g2, String colorG2) {
        return gamesDao.saveGame(statoGriglia,turno, g1, colorG1, g2, colorG2);
    }

    @Override
    public boolean saveGameWithName(Map<String, ArrayList<Label>> statoGriglia, int turno, String name, String g1, String colorG1, String g2, String colorG2){
        return gamesDao.saveGameWithName(statoGriglia, turno, name, g1, colorG1, g2, colorG2);
    }
    @Override
    public Path getGamesPath(){
        return gamesDao.getGamesPath();
    }

}
