package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.contracts.AboutViewInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AboutView implements AboutViewInterface {
    Stage secondStage;
    @FXML
    Label versionConnect4;
    @FXML
    Label developersLabel;
    @FXML
    Label releaseDateLabel;
    @FXML
    Label relDate;
    @FXML
    Label vers;
    @FXML
    Label devs;

    @Override
    public void display(){
        String version = "", developers ="", releaseDate="";
        try{
            Properties props = new Properties();
            InputStream is = getClass().getResourceAsStream("/properties/frontend.properties");
            props.load(is);
            version = props.getProperty("version");
            developers = props.getProperty("developers");
            releaseDate = props.getProperty("releaseDate");
        } catch (IOException e){
            version = "";
            developers = "";
            releaseDate ="";
        }
        versionConnect4.setText(version);
        developersLabel.setText(developers);
        releaseDateLabel.setText(releaseDate);
        secondStage.show();
    }

    @Override
    public void setSecondStage(Stage stage){
        secondStage = stage;
    }

    public void setDevs(String devs) {
        this.devs.setText(devs);
    }

    public void setRelDate(String relDate) {
        this.relDate.setText(relDate);
    }

    public void setVers(String vers) {
        this.vers.setText(vers);
    }
}
