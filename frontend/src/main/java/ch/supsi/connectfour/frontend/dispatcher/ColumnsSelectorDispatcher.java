package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.contracts.ColumnsSelectorDispatcherInterface;
import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.view.BoardView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Objects;


public class ColumnsSelectorDispatcher implements ColumnsSelectorDispatcherInterface {
    GameController gameController;
    Stage stage;
    @FXML
    private Button col0;
    @FXML
    private Button col1;
    @FXML
    private Button col2;
    @FXML
    private Button col3;
    @FXML
    private Button col4;
    @FXML
    private Button col5;
    @FXML
    private Button col6;

    @Override
    public void playerMove(ActionEvent actionEvent) {
        // get the column number corresponding to this event
        StringBuilder s = new StringBuilder();
        s.append(actionEvent.getSource().toString());
        String numeroColonna =  s.substring(actionEvent.getSource().toString().length() -2,actionEvent.getSource().toString().length() -1);
        gameController.insertToken(numeroColonna);
    }

    @Override
    public void toggleButtons(boolean onOff){
        col0.setDisable(onOff);
        col1.setDisable(onOff);
        col2.setDisable(onOff);
        col3.setDisable(onOff);
        col4.setDisable(onOff);
        col5.setDisable(onOff);
        col6.setDisable(onOff);
    }

    @Override
    public void setPrimaryStage(Stage primaryStage, Scene scene) {
        stage = primaryStage;
        setIcons();
    }

    @Override
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void disableButton(String numeroColonna) {
        switch (numeroColonna) {
            case "0": col0.setDisable(true); break;
            case "1": col1.setDisable(true); break;
            case "2": col2.setDisable(true); break;
            case "3": col3.setDisable(true); break;
            case "4": col4.setDisable(true); break;
            case "5": col5.setDisable(true); break;
            default: col6.setDisable(true); break;
        }
    }
    private void setIcons(){
        Image iconImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/arrow.png")));
        ImageView iconView0 = new ImageView(iconImage);
        ImageView iconView1 = new ImageView(iconImage);
        ImageView iconView2 = new ImageView(iconImage);
        ImageView iconView3 = new ImageView(iconImage);
        ImageView iconView4 = new ImageView(iconImage);
        ImageView iconView5 = new ImageView(iconImage);
        ImageView iconView6 = new ImageView(iconImage);
        iconView0.setFitWidth(16);
        iconView0.setFitHeight(16);
        iconView1.setFitWidth(16);
        iconView1.setFitHeight(16);
        iconView2.setFitWidth(16);
        iconView2.setFitHeight(16);
        iconView3.setFitWidth(16);
        iconView3.setFitHeight(16);
        iconView4.setFitWidth(16);
        iconView4.setFitHeight(16);
        iconView5.setFitWidth(16);
        iconView5.setFitHeight(16);
        iconView6.setFitWidth(16);
        iconView6.setFitHeight(16);
        col0.setGraphic(iconView0);
        col1.setGraphic(iconView1);
        col2.setGraphic(iconView2);
        col3.setGraphic(iconView3);
        col4.setGraphic(iconView4);
        col5.setGraphic(iconView5);
        col6.setGraphic(iconView6);
    }
}
