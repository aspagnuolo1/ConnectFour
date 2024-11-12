package ch.supsi.connectfour.frontend.contracts;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Map;

public interface GameModelInterface {
     void resetCounter();

     int getTurn();

     void updateStateGrid(Map<String, ArrayList<Label>> grudStatus);

     boolean checkWin(String player);

     void giveBackTurn();

     String[] getInfoAndIncrementTurn();

     boolean verifySpaceColumn(String posizione);

     int verifyColumn(String posizione);

     boolean changeColorSymbols(String symbolG1, String colorG1, String symbolG2, String colorG2);

     String[] getSymbols();

     String[] getSymbolsAndColors();

     void emptyGame();

     void setTurn(int turno);

     boolean verifyTie();

     String[] getNextPlayerInfo();

}
