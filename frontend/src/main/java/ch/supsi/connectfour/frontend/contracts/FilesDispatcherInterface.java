package ch.supsi.connectfour.frontend.contracts;

import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.controller.MenuBarController;
import javafx.event.ActionEvent;

public interface FilesDispatcherInterface {

    void saveFile(ActionEvent actionEvent);

    void setSaveFile(String text);

    void setMenuBarController(MenuBarController menuBarController) ;
}
