package ch.supsi.connectfour.frontend.contracts;

import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;

public interface PreferencesViewInterface {
     void display();
     void setFourthStage(Stage stage);
     void setText1(String text);
     void setText2(String text);
     void setTextGap1(String text);
     void setTextGap2(String text);
     ColorPicker getColorG1();
     ColorPicker getColorG2();
     String getSymbolG1();
     String getSymbolG2();
     void close();
}
