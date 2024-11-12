package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.contracts.ConfirmNewGameDispatcherInterface;
import ch.supsi.connectfour.frontend.controller.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ConfirmNewGameDispatcher implements ConfirmNewGameDispatcherInterface {
    @FXML
    Button yes;
    @FXML
    Button no;

    GameController gameController;

    @Override
    public void yesClicked(ActionEvent actionEvent){
        gameController.newGame();
        gameController.closeWindowNewGame();
    }

    @Override
    public void noClicked(ActionEvent actionEvent){
        //...
        gameController.closeWindowNewGame();
    }

    @Override
    public void setButtons(String text1, String text2){
        this.yes.setText(text1);
        this.no.setText(text2);
    }

    @Override
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
