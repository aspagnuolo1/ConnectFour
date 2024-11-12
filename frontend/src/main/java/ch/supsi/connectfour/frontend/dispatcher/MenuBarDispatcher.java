package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.contracts.MenuBarDispatcherInterface;
import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.controller.MenuBarController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class MenuBarDispatcher implements MenuBarDispatcherInterface {
    GameController gameController;
    MenuBarController menuBarController;

    @FXML
    Menu file;
    @FXML
    MenuItem newMenuItem;
    @FXML
    MenuItem openMenuItem;
    @FXML
    MenuItem saveMenuItem;
    @FXML
    MenuItem saveasMenuItem;
    @FXML
    MenuItem quitMenuItem;
    @FXML
    Menu edit;
    @FXML
    MenuItem preferencesMenuItem;
    @FXML
    MenuItem changelanguage;
    @FXML
    MenuItem enUS;
    @FXML
    MenuItem itCH;
    @FXML
    Menu help;
    @FXML
    MenuItem aboutMenuItem;
    @FXML
    MenuItem helpMenuItem;

    @Override
    public void newGame(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
        gameController.displayNewGamePossibility();
    }

    @Override
    public void openGame(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controllerà
        menuBarController.showFilesToSelect();
    }

    @Override
    public void saveGame(ActionEvent actionEvent) {
        gameController.saveGame();

        // decode this event
        // delegate it to a suitable controller
    }

    @Override
    public void saveGameAs(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
        menuBarController.showFilesView();
    }

    @Override
    public void quit(ActionEvent actionEvent) {
        System.exit(0);
        // decode this event
        // delegate it to a suitable controller
    }

    @Override
    public void preferenze(){
        gameController.showPreferences();
    }

    @Override
    public void showAbout(ActionEvent actionEvent) {
        menuBarController.showAbout();
    }

    @Override
    public void showHelp(ActionEvent actionEvent) {
        menuBarController.showHelp();

        // decode this event
        // delegate it to a suitable controller
    }

    @Override
    public void enableButtonSave(){
        saveMenuItem.setDisable(false);
    }

    @Override
    public void disableButtonSave(){
        saveMenuItem.setDisable(true);
    }

    @Override
    public void changeLanguageTo(ActionEvent actionEvent) {
        String cambiaLingua =actionEvent.getTarget().toString();
        cambiaLingua= cambiaLingua.substring(12,16);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cambiaLingua);
        stringBuilder.insert(2,"-");
        menuBarController.selectLanguage(stringBuilder.toString());
    }

    @Override
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void setMenuBarController(MenuBarController menuBarController) {
        this.menuBarController = menuBarController;
    }

    public void setFileText(String text){
        file.setText(text);
    }
    public void setNewText(String text){
        newMenuItem.setText(text);

    }
    public void setOpenText(String text){
        openMenuItem.setText(text);
    }
    public void setSaveText(String text){
        saveMenuItem.setText(text);
    }
    public void setSaveAsText(String text){
        saveasMenuItem.setText(text);
    }
    public void setQuitText(String text){
        quitMenuItem.setText(text);
    }
    public void setEditText(String text){
        edit.setText(text);
    }

    public void setChangeColorsAndSymbolsText(String text){
        preferencesMenuItem.setText(text);
    }

    public void setChangeLanguageText(String text){
        changelanguage.setText(text);
    }
    public void setChangeLanguageOneText(String text){
        enUS.setText(text);
    }

    public void setChangeLanguageTwoText(String text){
        itCH.setText(text);
    }

    public void setHelpText(String text){
        help.setText(text);
        helpMenuItem.setText(text);
    }

    public void setAboutText(String text){
        aboutMenuItem.setText(text);
    }
}
