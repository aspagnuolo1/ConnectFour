package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.contracts.HelpViewInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelpView  implements HelpViewInterface {
    private Stage thirdStage;
    @FXML
    private Label rules1;
    @FXML
    private Label rules2;

    @Override
    public void display(){
        thirdStage.show();
    }

    @Override
    public void setThirdStage(Stage stage){
        thirdStage = stage;
    }

    @Override
    public void setRules1(String rules1) {
        this.rules1.setText(rules1);
    }

    @Override
    public void setRules2(String rules2) {
        this.rules2.setText(rules2);
    }
}
