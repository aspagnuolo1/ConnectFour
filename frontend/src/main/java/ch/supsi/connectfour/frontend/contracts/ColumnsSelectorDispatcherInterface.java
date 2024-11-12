package ch.supsi.connectfour.frontend.contracts;

import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.view.BoardView;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public interface ColumnsSelectorDispatcherInterface {
    void playerMove(ActionEvent actionEvent);
    void toggleButtons(boolean onOff);
    void setPrimaryStage(Stage primaryStage, Scene scene);
    void setGameController(GameController gameController);
    void disableButton(String numeroColonna);
}
