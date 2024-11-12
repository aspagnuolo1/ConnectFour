package ch.supsi.connectfour.frontend.model;


import ch.supsi.connectfour.frontend.contracts.GameModelInterface;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameModel implements GameModelInterface {
    private final String defaultX = "X";
    private final String defaultY = "O";
    private final String defaultXColor = "yellow";
    private final String defaultYColor = "red";
    private final int tokenColonna = 6;
    private int cntCol0=0;
    private int cntCol1=0;
    private int cntCol2=0;
    private int cntCol3=0;
    private int cntCol4=0;
    private int cntCol5=0;
    private int cntCol6=0;
    private int turn = 0;
    private Player player1 = new Player(defaultX,defaultXColor);
    private Player player2 = new Player(defaultY,defaultYColor);
    Map<String, ArrayList<Label>> gridStatus;
    private static GameModel myself;

    private class Player {
        private String symbol;
        private String color;

        protected Player(String symbol, String color){
            this.symbol = symbol;
            this.color = color;
        }

        protected void setColor(String color) {
            this.color = color;
        }

        protected void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getColor() {
            return color;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public static GameModel getInstance(){
        if(myself == null){
            myself = new GameModel();
        }
        return myself;
    }

    protected GameModel(){
        turn = new Random().nextInt(0,2);
    }

    @Override
    public void resetCounter(){
        cntCol0 = 0;
        cntCol1 = 0;
        cntCol2 = 0;
        cntCol3 = 0;
        cntCol4 = 0;
        cntCol5 = 0;
        cntCol6 = 0;
        int i;
        for(i = 0; i < gridStatus.size(); i++){
            for(int j = 0; j < gridStatus.get(i+"").size(); j++){
                if(gridStatus.get(i+"").get(j).getText().equals(this.player1.getSymbol()) || gridStatus.get(i+"").get(j).getText().equals(this.player2.getSymbol())){
                    switch(i){
                        case 0: cntCol0++; break;
                        case 1: cntCol1++; break;
                        case 2: cntCol2++; break;
                        case 3: cntCol3++; break;
                        case 4: cntCol4++; break;
                        case 5: cntCol5++; break;
                        case 6: cntCol6++; break;
                        default: break;
                    }
                }
            }
        }
    }

    @Override
    public int getTurn() {
        return turn;
    }
    @Override
    public void updateStateGrid(Map<String, ArrayList<Label>> grudStatus) {
        this.gridStatus = grudStatus;
    }

    private boolean checkVertical(String player) {
        int row, col, count;
        for (col = 0; col < this.gridStatus.size(); col++) {
            count = 0;
            for (row = 0; row < this.gridStatus.get(col+"").size(); row++) {
                if (this.gridStatus.get(col+"").get(row).getText().equals(player)) {
                    count++;
                    if (count == 4)
                        return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontal(String player) {
        int row, col, count;
        for (row = 0; row < this.gridStatus.get("0").size(); row++) {
            count = 0;
            for (col = 0; col < this.gridStatus.size(); col++) {
                if (this.gridStatus.get(col+"").get(row).getText().equals(player)) {
                    count++;
                    if (count == 4)
                        return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(String player) {
        int row, col, count;
        // Controllo diagonali da sinistra a destra
        for (row = 0; row < this.gridStatus.get("0").size() - 3; row++) {
            for (col = 0; col < this.gridStatus.size() - 3; col++) {
                count = 0;
                for (int i = 0; i < 4; i++) {
                    if (this.gridStatus.get((col + i) + "").get(row + i).getText().equals(player)) {
                        count++;
                        if (count == 4)
                            return true;
                    }
                }
            }
        }
        // Controllo diagonali da destra a sinistra
        for (row = this.gridStatus.get("0").size() - 1; row >= 3; row--) {
            for (col = 0; col < this.gridStatus.size() - 3; col++) {
                count = 0;
                for (int i = 0; i < 4; i++) {
                    if (this.gridStatus.get((col + i) + "").get(row - i).getText().equals(player)) {
                        count++;
                        if (count == 4)
                            return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public boolean checkWin(String player) {
        return checkVertical(player) || checkHorizontal(player) || checkDiagonal(player);
    }
    @Override
    public void giveBackTurn(){
        turn--;
    }
    @Override
    public String[] getInfoAndIncrementTurn(){
        if(turn % 2 == 0){
            turn++;
            return new String[]{player1.getColor(), player1.getSymbol()};
        }
        turn++;
        return new String[]{player2.getColor(), player2.getSymbol()};
    }

    @Override
    public String[] getNextPlayerInfo(){
        if(turn % 2 == 0){
            return new String[]{player1.getColor(), player1.getSymbol()};
        }
        return new String[]{player2.getColor(), player2.getSymbol()};
    }

    @Override
    public boolean verifySpaceColumn(String posizione){
        switch (posizione){
            case "0":return cntCol0<tokenColonna;
            case "1":return cntCol1<tokenColonna;
            case "2":return cntCol2<tokenColonna;
            case "3":return cntCol3<tokenColonna;
            case "4":return cntCol4<tokenColonna;
            case "5":return cntCol5<tokenColonna;
            default: return cntCol6<tokenColonna;
        }
    }
    @Override
    public boolean verifyTie(){
        if(cntCol0 == tokenColonna && cntCol1 == tokenColonna &&
                cntCol2 == tokenColonna && cntCol3 == tokenColonna &&
                cntCol4 == tokenColonna && cntCol5 == tokenColonna &&
                cntCol6 == tokenColonna){
            return false;
        }
        return true;
    }
    @Override
    public int verifyColumn(String posizione){
        switch (posizione){
            case "0":return cntCol0++;
            case "1":return cntCol1++;
            case "2":return cntCol2++;
            case "3":return cntCol3++;
            case "4":return cntCol4++;
            case "5":return cntCol5++;
            case "6":return cntCol6++;
            default: return cntCol6;
      }
    }
    @Override
    public boolean changeColorSymbols(String symbolG1, String colorG1, String symbolG2, String colorG2){
        if (colorG2.equals(colorG1) || symbolG2.equals(symbolG1) || symbolG1.isEmpty() || symbolG2.isEmpty()){
            return false;
        }
        this.player1.setSymbol(symbolG1);
        this.player2.setSymbol(symbolG2);
        this.player1.setColor(colorG1);
        this.player2.setColor(colorG2);
        return true;
    }

    @Override
    public String[] getSymbols(){
        return new String[]{this.player1.getSymbol(), this.player2.getSymbol()};
    }
    @Override
    public String[] getSymbolsAndColors(){
        return new String[]{this.player1.getSymbol(), this.player1.getColor(), this.player2.getSymbol(), this.player2.getColor()};
    }
    @Override
    public void emptyGame(){
        this.player1.setSymbol(defaultX);
        this.player1.setColor(defaultXColor);
        this.player2.setSymbol(defaultY);
        this.player2.setColor(defaultYColor);
        cntCol0 = 0;
        cntCol1 = 0;
        cntCol2 = 0;
        cntCol3 = 0;
        cntCol4 = 0;
        cntCol5 = 0;
        cntCol6 = 0;
        turn = new Random().nextInt(0,2);
        gridStatus = new HashMap<>();
    }
    @Override
    public void setTurn(int turno) {
        this.turn = turno;
    }

}

