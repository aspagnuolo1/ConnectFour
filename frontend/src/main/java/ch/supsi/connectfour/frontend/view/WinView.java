package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.contracts.WinViewInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.*;



public class WinView implements WinViewInterface {
    Stage fifthStage;
    @FXML
    private Label winner;

    @Override
    public void display(String string){
        setWinner(string);
        fifthStage.show();
    }

    @Override
    public void setFifthStage(Stage stage){
        fifthStage = stage;
    }

    @Override
    public void setWinner(String winner) {
        this.winner.setText(winner);
    }
}

