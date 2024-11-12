package ch.supsi.connectfour.frontend.contracts;

import javafx.stage.Stage;

public interface WinViewInterface {
     void display(String string);
     void setFifthStage(Stage stage);
     void setWinner(String winner);
}
