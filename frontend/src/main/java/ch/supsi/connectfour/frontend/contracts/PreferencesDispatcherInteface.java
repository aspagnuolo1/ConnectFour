package ch.supsi.connectfour.frontend.contracts;

import ch.supsi.connectfour.frontend.controller.GameController;
import javafx.event.ActionEvent;

public interface PreferencesDispatcherInteface {
     void savePreferences(ActionEvent actionEvent);
     void setSavePref(String text);
     void setGameController(GameController gameController);
}
