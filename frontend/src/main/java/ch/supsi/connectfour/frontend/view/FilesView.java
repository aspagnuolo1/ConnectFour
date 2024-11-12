package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.contracts.FilesViewInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.nio.file.Path;

public class FilesView implements FilesViewInterface {
    private Stage sixthStage;

    @FXML
    private Label chooseFileName;
    @FXML
    private TextField filename;

    @Override
    public void display(){
        sixthStage.show();
    }

    @Override
    public String display(Path path, String text){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(text);
        fileChooser.setInitialDirectory(path.toFile());
        // Mostra la finestra di dialogo per la scelta del file
        java.io.File selectedFile = fileChooser.showOpenDialog(sixthStage);
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            return fileName;
        } else {
            return "";
        }
    }

    @Override
    public void setSixthStage(Stage stage){
        sixthStage = stage;
    }

    @Override
    public void setChooseFileName(String text){
        this.chooseFileName.setText(text);
    }

    @Override
    public void setTextFileName(String text){
        this.filename.setText(text);
    }

    @Override
    public String getFileName() {
        return filename.getText();
    }

    @Override
    public void close(){
        sixthStage.close();
    }
}
