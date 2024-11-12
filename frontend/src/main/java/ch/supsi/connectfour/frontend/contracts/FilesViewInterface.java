package ch.supsi.connectfour.frontend.contracts;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.nio.file.Path;

public interface FilesViewInterface {
    void display();
    String display(Path path, String text);
    void setSixthStage(Stage stage);
    void setChooseFileName(String text);
    void setTextFileName(String text);
    String getFileName();
    void close();
}
