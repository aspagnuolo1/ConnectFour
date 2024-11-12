package ch.supsi.connectfour.frontend.contracts;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Map;

public interface BoardViewInterface {
    void updateLabel(String numColumn, int position,String[] infoPlayer);
    void refreshColorsAndSymbols(String oldSymbolG1, String symbolG1, String colorG1, String oldSymbolG2, String symbolG2, String colorG2);
    void initializeElement();
    void setGrid(Map<String, ArrayList<Label>> grid) ;
    Map<String, ArrayList<Label>> getGridStatus();
    void emptyBoard();
}
