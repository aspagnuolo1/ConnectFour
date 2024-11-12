package ch.supsi.connectfour.frontend.view;


import ch.supsi.connectfour.frontend.contracts.InfoBarViewInterface;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InfoBarView implements InfoBarViewInterface {
    @FXML
    private Text infobar;

    @Override
    public void display(String string) {
        infobar.setText(string);
    }
}
