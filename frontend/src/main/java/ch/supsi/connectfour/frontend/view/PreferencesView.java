package ch.supsi.connectfour.frontend.view;
import ch.supsi.connectfour.frontend.contracts.PreferencesViewInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Locale;

public class PreferencesView implements PreferencesViewInterface{
    private Stage fourthStage;
    @FXML
    private ColorPicker colorG1;
    @FXML
    private ColorPicker colorG2;
    @FXML
    private TextField symbolG1;
    @FXML
    private TextField symbolG2;
    @FXML
    private Label player1;
    @FXML
    private Label player2;

    @Override
    public void display(){
        fourthStage.show();
    }

    @Override
    public void setFourthStage(Stage stage){
        fourthStage = stage;
    }

    @Override
    public void setText1(String text){
        this.player1.setText(text);
    }

    @Override
    public void setText2(String text){
        this.player2.setText(text);
    }

    @Override
    public void setTextGap1(String text){
        this.symbolG1.setPromptText(text);
    }

    @Override
    public void setTextGap2(String text){
        this.symbolG2.setPromptText(text);
    }

    @Override
    public ColorPicker getColorG1() {
        return colorG1;
    }

    @Override
    public ColorPicker getColorG2() {
        return colorG2;
    }

    @Override
    public String getSymbolG1() {
        return symbolG1.getText();
    }

    @Override
    public String getSymbolG2() {
        return symbolG2.getText();
    }

    @Override
    public void close(){
        fourthStage.close();
    }

}
