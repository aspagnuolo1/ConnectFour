package ch.supsi.connectfour.frontend.contracts;

import javafx.stage.Stage;

public interface ConfirmNewGameViewInterface {
    void setSureText(String sure);
    void setSeventhStage(Stage stage);
    void close();
    void display();
}
