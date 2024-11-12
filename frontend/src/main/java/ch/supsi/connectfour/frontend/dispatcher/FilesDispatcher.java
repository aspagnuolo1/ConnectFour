package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.contracts.FilesDispatcherInterface;
import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.controller.MenuBarController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FilesDispatcher implements FilesDispatcherInterface {
    MenuBarController menuBarController;

    @FXML
    Button saveFile;

    @Override
    public void saveFile(ActionEvent actionEvent){
        menuBarController.closeFilesWindow();
    }

    @Override
    public void setSaveFile(String text){
        this.saveFile.setText(text);
    }

    @Override
    public void setMenuBarController(MenuBarController menuBarController) {
        this.menuBarController = menuBarController;
    }
}
