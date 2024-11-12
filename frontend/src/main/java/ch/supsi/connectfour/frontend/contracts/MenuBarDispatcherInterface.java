package ch.supsi.connectfour.frontend.contracts;

import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.controller.MenuBarController;
import javafx.event.ActionEvent;

public interface MenuBarDispatcherInterface {
    void newGame(ActionEvent actionEvent);
    void openGame(ActionEvent actionEvent);
    void saveGame(ActionEvent actionEvent) ;
    void saveGameAs(ActionEvent actionEvent) ;
    void quit(ActionEvent actionEvent) ;
    void preferenze();
    void showAbout(ActionEvent actionEvent);
    void showHelp(ActionEvent actionEvent) ;
    void enableButtonSave();
    void disableButtonSave();
    void changeLanguageTo(ActionEvent actionEvent) ;
    void setGameController(GameController gameController);
    void setMenuBarController(MenuBarController menuBarController);
}
