package ch.supsi.connectfour.frontend.controller;
import ch.supsi.connectfour.backend.application.ConnectFourBackendController;


import ch.supsi.connectfour.frontend.contracts.*;
import ch.supsi.connectfour.frontend.model.GameModel;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameController implements FileSelectionListenerInterface {
    Stage primaryStage;
    BoardViewInterface boardView;
    InfoBarViewInterface infoBarView;
    PreferencesViewInterface preferencesView;
    ConfirmNewGameViewInterface confirmNewGameView;
    WinViewInterface winView;
    ColumnsSelectorDispatcherInterface columnsSelectorDispatcher;
    MenuBarDispatcherInterface menuBarDispatcher;
    GameModelInterface gameModel = GameModel.getInstance();
    ConnectFourBackendController backendController = ConnectFourBackendController.getInstance();
    private String fileName;


    // PREFERENCES -> of the game

    public void showPreferences(){
        preferencesView.display();
    }

    public void closePreferencesWindow(){
        preferencesView.close();
    }

    public void changeColorSymbol(){
        // get the new information from the preferencesView
        Color color1 = preferencesView.getColorG1().getValue();
        Color color2 = preferencesView.getColorG2().getValue();
        String symbolG1 = preferencesView.getSymbolG1();
        String symbolG2 = preferencesView.getSymbolG2();

        // get old information to print them in the infobar
        String oldSymbolG1 = gameModel.getSymbols()[0];
        String oldSymbolG2 = gameModel.getSymbols()[1];

        // get next player info to display it after the changes
        String[] infoNextPlayer = gameModel.getNextPlayerInfo();

        String colorG1 = String.format("rgba(%d, %d, %d, %.2f)",
                (int) (color1.getRed() * 255),
                (int) (color1.getGreen() * 255),
                (int) (color1.getBlue() * 255),
                color1.getOpacity());
        String colorG2 = String.format("rgba(%d, %d, %d, %.2f)",
                (int) (color2.getRed() * 255),
                (int) (color2.getGreen() * 255),
                (int) (color2.getBlue() * 255),
                color2.getOpacity());

        boolean changed = gameModel.changeColorSymbols(symbolG1, colorG1, symbolG2, colorG2);
        if(changed){
            boardView.refreshColorsAndSymbols(oldSymbolG1, symbolG1, colorG1, oldSymbolG2, symbolG2, colorG2);
            // get next player and display new info
            infoNextPlayer = gameModel.getNextPlayerInfo();
            infoBarView.display(backendController.translate("label.changedSymbolsAndColors")+" G1: "+symbolG1+", G2: "+symbolG2 +" | "+infoNextPlayer[1]+" "+ backendController.translate("label.itsPlayerTurn"));
        } else{
            infoBarView.display(backendController.translate("label.preferencesNotChanged")+"\t |\t "+infoNextPlayer[1]+" "+ backendController.translate("label.itsPlayerTurn"));
        }
    }

    // SAVE AND OPEN games methods -> interacting mostly with InfoBarView, BoardView and GameModel

    public void saveGame(){
        int turn = gameModel.getTurn();
        String[] infoNextPlayer = gameModel.getNextPlayerInfo();
        String[] players = gameModel.getSymbolsAndColors();
        if(backendController.saveGameWithName(boardView.getGridStatus(), turn,fileName, players[0],players[1], players[2], players[3])){
            infoBarView.display(backendController.translate("label.gameSaved") +" | "+infoNextPlayer[1]+" "+ backendController.translate("label.itsPlayerTurn"));
        } else{
            infoBarView.display(backendController.translate("label.gameNotSaved") +" | "+infoNextPlayer[1]+" "+ backendController.translate("label.itsPlayerTurn"));
        }
    }

    public void saveGameWithName(String filenameParameter){
        int turn = gameModel.getTurn();
        fileName = filenameParameter;
        String[] infoNextPlayer = gameModel.getNextPlayerInfo();
        String[] giocatori = gameModel.getSymbolsAndColors();
        if(backendController.saveGameWithName(boardView.getGridStatus(), turn, fileName, giocatori[0],giocatori[1], giocatori[2], giocatori[3])){
            infoBarView.display(backendController.translate("label.gameSaved") +" | "+infoNextPlayer[1]+" "+ backendController.translate("label.itsPlayerTurn"));
            menuBarDispatcher.enableButtonSave();
        } else{
            infoBarView.display(backendController.translate("label.gameNotSaved") +" | "+infoNextPlayer[1]+" "+ backendController.translate("label.itsPlayerTurn"));
        }
    }

    public void openGame(String filenameParameter){
        fileName = filenameParameter;
        Map<String, ArrayList<Label>> gridAndInfo = backendController.getGame(fileName);
        String[] infoNextPlayer = gameModel.getNextPlayerInfo();
        if(gridAndInfo == null){
            infoBarView.display(backendController.translate("label.gameNotOpened")+" | "+infoNextPlayer[1]+" "+ backendController.translate("label.itsPlayerTurn"));
        } else{
            Map<String, ArrayList<Label>> grid = new HashMap<>();

            for(int i = 0; i < gridAndInfo.size()-1; i++){
                grid.put(i+"", gridAndInfo.get(i+""));
            }

            String turno = gridAndInfo.get("infoGame").get(0).getText();
            int turnInt = Integer.parseInt(turno);

            String g1 = gridAndInfo.get("infoGame").get(1).getText();
            String color1 = gridAndInfo.get("infoGame").get(2).getText();
            String g2 = gridAndInfo.get("infoGame").get(3).getText();
            String color2 = gridAndInfo.get("infoGame").get(4).getText();

            gameModel.updateStateGrid(grid);
            gameModel.changeColorSymbols(g1, color1, g2, color2);
            gameModel.resetCounter();
            gameModel.setTurn(turnInt);

            boardView.setGrid(grid);

            infoNextPlayer = gameModel.getNextPlayerInfo();
            gameModel.giveBackTurn();
            String[] infoCurrentPlayer = gameModel.getInfoAndIncrementTurn();
            infoBarView.display(backendController.translate("label.gameOpened") + " |  " + infoNextPlayer[1] + backendController.translate("label.itsPlayerTurn"));
            columnsSelectorDispatcher.toggleButtons(false);
            resetStateButton();
            verifyState(infoCurrentPlayer);
            verifyState(infoNextPlayer);
            menuBarDispatcher.enableButtonSave();

        }
    }

    // GAME LOGIC USING GAME MODEL and using views for displaying the correct information

    public void insertToken(String numColumn){
        String[] infoCurrentPlayer = gameModel.getInfoAndIncrementTurn();
        String[] infoNextPlayer = gameModel.getNextPlayerInfo();
        if(gameModel.verifySpaceColumn(numColumn)){
            boardView.updateLabel(numColumn, gameModel.verifyColumn(numColumn),infoCurrentPlayer);
            infoBarView.display(infoNextPlayer[1] + backendController.translate("label.itsPlayerTurn"));
            verifyState(infoCurrentPlayer);
        } else {
            gameModel.giveBackTurn();
            columnsSelectorDispatcher.disableButton(numColumn);
            infoBarView.display(backendController.translate("label.disabledButton") + " | " + infoNextPlayer[1] + backendController.translate("label.itsPlayerTurn"));
        }
        if(!gameModel.verifyTie()){
            infoBarView.display(backendController.translate("label.tie"));
            columnsSelectorDispatcher.toggleButtons(true);
        }
    }

    public void verifyState(String[] infoCurrentPlayer){
        gameModel.updateStateGrid(boardView.getGridStatus());
        if(gameModel.checkWin(infoCurrentPlayer[1])){
            infoBarView.display(infoCurrentPlayer[1]+": "+ backendController.translate("label.win"));
            winView.display(backendController.translate("label.player")+ ": " + infoCurrentPlayer[1] +" "+ backendController.translate("label.win"));
            columnsSelectorDispatcher.toggleButtons(true);
        }
    }

    public void resetStateButton(){
        for (int i = 0; i <7; i++) {
            if (!gameModel.verifySpaceColumn(i+"")){
                columnsSelectorDispatcher.disableButton(i+"");
            }
        }
    }

    // NEW GAME

    public void displayNewGamePossibility(){
        this.confirmNewGameView.display();
    }

    public void newGame(){
        boardView.emptyBoard();
        gameModel.emptyGame();
        fileName="";
        menuBarDispatcher.disableButtonSave();
        columnsSelectorDispatcher.toggleButtons(false);
        String[] infoNextPlayer = gameModel.getNextPlayerInfo();
        infoBarView.display(backendController.translate("label.newGame")+" | "+ infoNextPlayer[1] + backendController.translate("label.itsPlayerTurn"));
    }

    public void closeWindowNewGame(){
        confirmNewGameView.close();
    }

    // FILES -> observer pattern with MenuBarController
    @Override
    public void onFileToOpenSelected(String fileName) {
        openGame(fileName);
    }

    @Override
    public void onFileNameToSaveSelected(String fileName) {
        saveGameWithName(fileName);
    }

    // SETTERS used from MainFx

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setBoardView(BoardViewInterface boardView) {
        this.boardView = boardView;
    }

    public void setWinView(WinViewInterface winView) {
        this.winView= winView;
    }

    public void setInfoBarView(InfoBarViewInterface infoBarView) {
        this.infoBarView = infoBarView;
    }

    public void setPreferencesView(PreferencesViewInterface preferencesView) {
        this.preferencesView = preferencesView;
    }

    public void setColumnsSelectorDispatcher(ColumnsSelectorDispatcherInterface columnsSelectorDispatcher) {
        this.columnsSelectorDispatcher = columnsSelectorDispatcher;
    }

    public void setMenuBarDispatcher(MenuBarDispatcherInterface menuBarDispatcher) {
        this.menuBarDispatcher = menuBarDispatcher;
    }

    public void setConfirmNewGameView(ConfirmNewGameViewInterface confirmNewGameView) {
        this.confirmNewGameView = confirmNewGameView;
    }
    public void setGameStartedInfo(){
        String[] infoNextPlayer = gameModel.getNextPlayerInfo();
        infoBarView.display(backendController.translate("label.welcome") + " | " + infoNextPlayer[1] + backendController.translate("label.itsPlayerTurn"));
    }
}