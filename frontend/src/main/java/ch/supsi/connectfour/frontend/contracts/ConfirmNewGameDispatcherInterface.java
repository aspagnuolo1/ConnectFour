package ch.supsi.connectfour.frontend.contracts;

import ch.supsi.connectfour.frontend.controller.GameController;
import javafx.event.ActionEvent;

public interface ConfirmNewGameDispatcherInterface {
    void yesClicked(ActionEvent actionEvent);
    void noClicked(ActionEvent actionEvent);
    void setButtons(String text1, String text2);
    void setGameController(GameController gameController);
}
