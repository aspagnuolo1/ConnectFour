package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.contracts.PreferencesDispatcherInteface;
import ch.supsi.connectfour.frontend.controller.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PreferencesDispatcher implements PreferencesDispatcherInteface {
    GameController gameController;

    @FXML
    Button savePref;

    @Override
    public void savePreferences(ActionEvent actionEvent){
        gameController.changeColorSymbol();
        gameController.closePreferencesWindow();
    }

    @Override
    public void setSavePref(String text){
        this.savePref.setText(text);
    }

    @Override
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
