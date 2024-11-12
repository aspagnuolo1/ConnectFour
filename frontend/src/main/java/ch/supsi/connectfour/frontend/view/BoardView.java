package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.contracts.BoardViewInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardView  implements BoardViewInterface {
    Map<String,ArrayList<Label>> labels= new HashMap<>();
    ArrayList<Label> colonna0 = new ArrayList<>();
    ArrayList<Label> colonna1 = new ArrayList<>();
    ArrayList<Label> colonna2 = new ArrayList<>();
    ArrayList<Label> colonna3 = new ArrayList<>();
    ArrayList<Label> colonna4 = new ArrayList<>();
    ArrayList<Label> colonna5 = new ArrayList<>();
    ArrayList<Label> colonna6 = new ArrayList<>();

    //Celle0
    @FXML
    private Label cell00;
    @FXML
    private Label cell01;
    @FXML
    private Label cell02;
    @FXML
    private Label cell03;
    @FXML
    private Label cell04;
    @FXML
    private Label cell05;
    @FXML
    private Label cell06;
    //Celle1
    @FXML
    private Label cell10;
    @FXML
    private Label cell11;
    @FXML
    private Label cell12;
    @FXML
    private Label cell13;
    @FXML
    private Label cell14;
    @FXML
    private Label cell15;
    @FXML
    private Label cell16;
    //Celle2
    @FXML
    private Label cell20;
    @FXML
    private Label cell21;
    @FXML
    private Label cell22;
    @FXML
    private Label cell23;
    @FXML
    private Label cell24;
    @FXML
    private Label cell25;
    @FXML
    private Label cell26;
    //Celle3
    @FXML
    private Label cell30;
    @FXML
    private Label cell31;
    @FXML
    private Label cell32;
    @FXML
    private Label cell33;
    @FXML
    private Label cell34;
    @FXML
    private Label cell35;
    @FXML
    private Label cell36;
    //Celle4
    @FXML
    private Label cell40;
    @FXML
    private Label cell41;
    @FXML
    private Label cell42;
    @FXML
    private Label cell43;
    @FXML
    private Label cell44;
    @FXML
    private Label cell45;
    @FXML
    private Label cell46;
    //Celle5
    @FXML
    private Label cell50;
    @FXML
    private Label cell51;
    @FXML
    private Label cell52;
    @FXML
    private Label cell53;
    @FXML
    private Label cell54;
    @FXML
    private Label cell55;
    @FXML
    private Label cell56;


    @Override
    public void updateLabel(String numColumn, int position,String[] infoPlayer){
        labels.get(numColumn).get(position).setText(infoPlayer[1]);
        labels.get(numColumn).get(position).setStyle("-fx-background-color:"+infoPlayer[0]+"; -fx-background-radius: 200");

    }

    @Override
    public void refreshColorsAndSymbols(String oldSymbolG1, String symbolG1, String colorG1, String oldSymbolG2, String symbolG2, String colorG2){
        for(int i = 0; i < this.labels.size(); i++){
            for(int j = 0; j < this.labels.get(i+"").size(); j++){
                if(this.labels.get(i+"").get(j).getText().equals(oldSymbolG1)){
                    this.labels.get(i+"").get(j).setText(symbolG1);
                    this.labels.get(i+"").get(j).setStyle("-fx-background-color:"+colorG1+"; -fx-background-radius: 200");
                } else if(this.labels.get(i+"").get(j).getText().equals(oldSymbolG2)){
                    this.labels.get(i+"").get(j).setText(symbolG2);
                    this.labels.get(i+"").get(j).setStyle("-fx-background-color:"+colorG2+"; -fx-background-radius: 200");
                }
            }

        }
    }

    @Override
    public void initializeElement(){
        //COLONNA0
        colonna0.add(cell00);
        colonna0.add(cell10);
        colonna0.add(cell20);
        colonna0.add(cell30);
        colonna0.add(cell40);
        colonna0.add(cell50);
        //COLONNA1
        colonna1.add(cell01);
        colonna1.add(cell11);
        colonna1.add(cell21);
        colonna1.add(cell31);
        colonna1.add(cell41);
        colonna1.add(cell51);
        //COLONNA2
        colonna2.add(cell02);
        colonna2.add(cell12);
        colonna2.add(cell22);
        colonna2.add(cell32);
        colonna2.add(cell42);
        colonna2.add(cell52);
        //COLONNA3
        colonna3.add(cell03);
        colonna3.add(cell13);
        colonna3.add(cell23);
        colonna3.add(cell33);
        colonna3.add(cell43);
        colonna3.add(cell53);
        //COLONNA4
        colonna4.add(cell04);
        colonna4.add(cell14);
        colonna4.add(cell24);
        colonna4.add(cell34);
        colonna4.add(cell44);
        colonna4.add(cell54);
        //COLONNA5
        colonna5.add(cell05);
        colonna5.add(cell15);
        colonna5.add(cell25);
        colonna5.add(cell35);
        colonna5.add(cell45);
        colonna5.add(cell55);
        //COLONNA6
        colonna6.add(cell06);
        colonna6.add(cell16);
        colonna6.add(cell26);
        colonna6.add(cell36);
        colonna6.add(cell46);
        colonna6.add(cell56);

        labels.put("0",colonna0);
        labels.put("1",colonna1);
        labels.put("2",colonna2);
        labels.put("3",colonna3);
        labels.put("4",colonna4);
        labels.put("5",colonna5);
        labels.put("6",colonna6);
    }

    @Override
    public void setGrid(Map<String, ArrayList<Label>> grid) {
        for (Map.Entry<String, ArrayList<Label>> entry : grid.entrySet()) {
            String key = entry.getKey();
            ArrayList<Label> lebelsGrid = entry.getValue();
            ArrayList<Label> myLabels = labels.get(key);

            if (myLabels != null) {
                for (int i = 0; i < lebelsGrid.size(); i++) {
                    Label grigliaLabel = lebelsGrid.get(i);
                    Label myLabel = myLabels.get(i);

                    myLabel.setText(grigliaLabel.getText());
                    myLabel.setStyle(grigliaLabel.getStyle());
                }
            }
        }

    }

    @Override
    public Map<String, ArrayList<Label>> getGridStatus() {
        return Collections.unmodifiableMap(labels);
    }

    @Override
    public void emptyBoard() {
        for (int i = 0; i < labels.size(); i++) {
            ArrayList<Label> labelTmp = labels.get(i+"");
            for (Label label : labelTmp){
                label.setText("");
                label.setStyle("");
            }
        }
    }
}
