package ch.supsi.connectfour.frontend.view;


import ch.supsi.connectfour.frontend.contracts.ConfirmNewGameDispatcherInterface;
import ch.supsi.connectfour.frontend.contracts.ConfirmNewGameViewInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmNewGameView implements ConfirmNewGameViewInterface {
    private Stage seventhStage;

    @FXML
    private Label sure;

    @Override
    public void setSureText(String sure) {
        this.sure.setText(sure);
    }

    @Override
    public void setSeventhStage(Stage stage){
        seventhStage = stage;
    }

    @Override
    public void close(){
        seventhStage.close();
    }

    @Override
    public void display(){
        seventhStage.show();
    }
}
