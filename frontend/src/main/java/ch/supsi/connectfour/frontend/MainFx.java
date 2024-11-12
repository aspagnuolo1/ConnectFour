package ch.supsi.connectfour.frontend;


import ch.supsi.connectfour.backend.application.ConnectFourBackendController;
import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.controller.MenuBarController;
import ch.supsi.connectfour.frontend.dispatcher.*;
import ch.supsi.connectfour.frontend.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;

public class MainFx extends Application {

    public static final String APP_TITLE = "Connect4";

    private MenuBarDispatcher menuBarDispatcher;
    private ColumnsSelectorDispatcher columnsSelectorDispatcher;
    private PreferencesDispatcher preferencesDispatcher;
    private FilesDispatcher filesDispatcher;
    private ConfirmNewGameDispatcher confirmNewGameDispatcher;
    private BoardView boardView;
    private InfoBarView infoBarView;
    private GameController gameController = new GameController();
    private MenuBarController menuBarController = new MenuBarController();
    private ConnectFourBackendController connectFourBackendController;
    private AboutView aboutView;
    private HelpView helpView;
    private PreferencesView preferencesView;
    private WinView winView;
    private FilesView filesView;
    private ConfirmNewGameView confirmNewGameView;
    private Stage secondaryStage = new Stage();
    private Stage thirdStage = new Stage();
    private Stage fourthStage = new Stage();
    private Stage fifthStage = new Stage();
    private Stage sixthStage = new Stage();
    private Stage seventhStage = new Stage();



    public MainFx() {
    }

    @Override
    public void start(Stage primaryStage) {
        // handle the main window close request
        // in real life, this event should not be dealt with here!
        // it should actually be delegated to a suitable ExitController!
        primaryStage.setOnCloseRequest(
                windowEvent -> {
                    // consume the window event (the main window would be closed otherwise no matter what)
                    windowEvent.consume();

                    // hard close the primary stage
                    // javafx guarantees the clean exit of the javafx platform, when the last application stage is closed
                    primaryStage.close();
                }
        );
        this.connectFourBackendController = ConnectFourBackendController.getInstance();

        // Stages width and heights
        primaryStage.setMaxHeight(669);
        primaryStage.setMaxWidth(630);
        primaryStage.setMinHeight(669);
        primaryStage.setMinWidth(630);
        seventhStage.setMaxHeight(120);
        seventhStage.setMinHeight(120);
        seventhStage.setMaxWidth(180);
        seventhStage.setMinWidth(180);

        // Stages icons settings
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/F4.png"))));
        secondaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/about.png"))));
        thirdStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/help.png"))));
        fourthStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/settings.png"))));
        fifthStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/win.png"))));
        sixthStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/save.png"))));
        seventhStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/attention.png"))));

        // MENU BAR
        MenuBar menuBar;
        try {
            URL fxmlUrl = getClass().getResource("/menubar.fxml");
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader menuBarLoader = new FXMLLoader(fxmlUrl);
            menuBar = menuBarLoader.load();
            this.menuBarDispatcher = menuBarLoader.getController();

            // Set the texts of the menubar
            menuBarDispatcher.setFileText(connectFourBackendController.translate("label.file"));
            menuBarDispatcher.setNewText(connectFourBackendController.translate("label.new"));
            menuBarDispatcher.setOpenText(connectFourBackendController.translate("label.open"));
            menuBarDispatcher.setSaveText(connectFourBackendController.translate("label.save"));
            menuBarDispatcher.setSaveAsText(connectFourBackendController.translate("label.saveas"));
            menuBarDispatcher.setQuitText(connectFourBackendController.translate("label.quit"));
            menuBarDispatcher.setEditText(connectFourBackendController.translate("label.edit"));
            menuBarDispatcher.setChangeColorsAndSymbolsText(connectFourBackendController.translate("label.preferences"));
            menuBarDispatcher.setChangeLanguageText(connectFourBackendController.translate("label.changeLanguage"));
            menuBarDispatcher.setChangeLanguageOneText(connectFourBackendController.translate("label.changeLanguageTo1"));
            menuBarDispatcher.setChangeLanguageTwoText(connectFourBackendController.translate("label.changeLanguageTo2"));
            menuBarDispatcher.setHelpText(connectFourBackendController.translate("label.help"));
            menuBarDispatcher.setAboutText(connectFourBackendController.translate("label.about"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // CONNECT-FOUR COLUMN SELECTORS
        Parent columnSelectors;
        try {
            URL fxmlUrl = getClass().getResource("/columnselectors.fxml");
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader columnSelectorsLoader = new FXMLLoader(fxmlUrl);
            columnSelectors = columnSelectorsLoader.load();
            this.columnsSelectorDispatcher = columnSelectorsLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // PREFERENCES DISPATCHER
        Parent preferencesDisp;
        try {
            URL fxmlUrl = getClass().getResource("/salvaPreferenze.fxml");
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader preferencesDispLoader = new FXMLLoader(fxmlUrl);
            preferencesDisp = preferencesDispLoader.load();
            this.preferencesDispatcher = preferencesDispLoader.getController();
            preferencesDispatcher.setSavePref(connectFourBackendController.translate("label.save"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // FILES DISPATCHER
        Parent filesDisp;
        try {
            URL fxmlUrl = getClass().getResource("/saveFile.fxml");
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader filesDispLoader = new FXMLLoader(fxmlUrl);
            filesDisp = filesDispLoader.load();
            this.filesDispatcher = filesDispLoader.getController();
            filesDispatcher.setSaveFile(connectFourBackendController.translate("label.save"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ConfirmNewGameDispatcher
        Parent confirmDisp;
        try {
            URL fxmlUrl = getClass().getResource("/confirmNewGameButtons.fxml");
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader confirmDispLoader = new FXMLLoader(fxmlUrl);
            confirmDisp = confirmDispLoader.load();
            this.confirmNewGameDispatcher = confirmDispLoader.getController();
            confirmNewGameDispatcher.setButtons(connectFourBackendController.translate("label.confirm"), connectFourBackendController.translate("label.refuse"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // CONNECT-FOUR BOARD
        Parent board;
        try {
            URL fxmlUrl = getClass().getResource("/gameboard.fxml");
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader boardLoader = new FXMLLoader(fxmlUrl);
            board = boardLoader.load();
            this.boardView = boardLoader.getController();
            this.boardView.initializeElement();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // INFO BAR
        Parent infoBar;
        try {
            URL fxmlUrl = getClass().getResource("/infobar.fxml");
            if (fxmlUrl == null) {
                // resource file not found
                return;
            }

            FXMLLoader infoBarLoader = new FXMLLoader(fxmlUrl);
            infoBar = infoBarLoader.load();
            this.infoBarView = infoBarLoader.getController();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ABOUT
        Parent aboutPanel;
        try {
            URL fxmlUrl = getClass().getResource("/about.fxml");
            if (fxmlUrl == null) {
                // resource file not found
                return;
            }

            FXMLLoader aboutPanelLoader = new FXMLLoader(fxmlUrl);
            aboutPanel = aboutPanelLoader.load();
            aboutView = aboutPanelLoader.getController();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BorderPane secondBorderPane = new BorderPane();
        secondBorderPane.setCenter(aboutPanel);
        Scene sceneAbout = new Scene(secondBorderPane);
        secondaryStage.setScene(sceneAbout);
        aboutView.setSecondStage(secondaryStage);
        aboutView.setVers(connectFourBackendController.translate("label.vers"));
        aboutView.setDevs(connectFourBackendController.translate("label.devs"));
        aboutView.setRelDate(connectFourBackendController.translate("label.relDate"));

        //WIN SCENE
        Parent winPanel;
        try {
            URL fxmlUrl = getClass().getResource("/win.fxml");
            if (fxmlUrl == null) {
                // resource file not found
                return;
            }

            FXMLLoader aboutPanelLoader = new FXMLLoader(fxmlUrl);
            winPanel = aboutPanelLoader.load();
            winView = aboutPanelLoader.getController();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BorderPane fifthBorderPane = new BorderPane();
        fifthBorderPane.setCenter(winPanel);
        Scene winScene = new Scene(fifthBorderPane);
        fifthStage.setScene(winScene);
        winView.setFifthStage(fifthStage);

        // HELP
        Parent helpPanel;
        try {
            URL fxmlUrl = getClass().getResource("/help.fxml");
            if (fxmlUrl == null) {
                // resource file not found
                return;
            }

            FXMLLoader helpPanelLoader = new FXMLLoader(fxmlUrl);
            helpPanel = helpPanelLoader.load();
            helpView = helpPanelLoader.getController();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BorderPane thirdBorderPane = new BorderPane();
        thirdBorderPane.setCenter(helpPanel);
        Scene sceneHelp = new Scene(thirdBorderPane);
        thirdStage.setScene(sceneHelp);
        helpView.setThirdStage(thirdStage);
        helpView.setRules1(connectFourBackendController.translate("label.rules1"));
        helpView.setRules2(connectFourBackendController.translate("label.rules2"));


        // PREFERENCES VIEW
        Parent preferencesPanel;
        try {
            URL fxmlUrl = getClass().getResource("/preferences.fxml");
            if (fxmlUrl == null) {
                // resource file not found
                return;
            }

            FXMLLoader preferencesPanelLoader = new FXMLLoader(fxmlUrl);
            preferencesPanel = preferencesPanelLoader.load();
            preferencesView = preferencesPanelLoader.getController();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BorderPane fourthBorderPane = new BorderPane();
        fourthBorderPane.setCenter(preferencesPanel);
        fourthBorderPane.setBottom(preferencesDisp);
        Scene scenePreferences = new Scene(fourthBorderPane);
        fourthStage.setScene(scenePreferences);
        preferencesView.setFourthStage(fourthStage);
        preferencesView.setText1(connectFourBackendController.translate("label.player")+1);
        preferencesView.setText2(connectFourBackendController.translate("label.player")+2);
        preferencesView.setTextGap1(connectFourBackendController.translate("label.text"));
        preferencesView.setTextGap2(connectFourBackendController.translate("label.text"));


        // FILES VIEW
        Parent filesPanel;
        try {
            URL fxmlUrl = getClass().getResource("/files.fxml");
            if (fxmlUrl == null) {
                // resource file not found
                return;
            }

            FXMLLoader filesPanelLoader = new FXMLLoader(fxmlUrl);
            filesPanel = filesPanelLoader.load();
            filesView = filesPanelLoader.getController();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BorderPane sixthBorderPane = new BorderPane();
        sixthBorderPane.setCenter(filesPanel);
        sixthBorderPane.setBottom(filesDisp);
        Scene sceneFiles = new Scene(sixthBorderPane);
        sixthStage.setScene(sceneFiles);
        filesView.setSixthStage(sixthStage);
        filesView.setChooseFileName(connectFourBackendController.translate("label.chooseFileName"));
        filesView.setTextFileName(connectFourBackendController.translate("label.textFile"));

        // Confirm View
        Parent confirmPanel;
        try {
            URL fxmlUrl = getClass().getResource("/confirmNewGame.fxml");
            if (fxmlUrl == null) {
                // resource file not found
                return;
            }

            FXMLLoader confirmPanelLoader = new FXMLLoader(fxmlUrl);
            confirmPanel = confirmPanelLoader.load();
            confirmNewGameView = confirmPanelLoader.getController();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BorderPane seventhBorderPane = new BorderPane();
        seventhBorderPane.setCenter(confirmPanel);
        seventhBorderPane.setBottom(confirmDisp);
        Scene sceneConfirm = new Scene(seventhBorderPane);
        seventhStage.setScene(sceneConfirm);
        confirmNewGameView.setSeventhStage(seventhStage);
        confirmNewGameView.setSureText(connectFourBackendController.translate("label.sure"));


        // BORDER PANE
        BorderPane mainBorderPane = new BorderPane();

        mainBorderPane.setTop(menuBar);

        BorderPane gameBoardBorderPane = new BorderPane();
        gameBoardBorderPane.setTop(columnSelectors);
        gameBoardBorderPane.setCenter(board);
        mainBorderPane.setCenter(gameBoardBorderPane);

        mainBorderPane.setBottom(infoBar);

        // SCENE
        Scene scene = new Scene(mainBorderPane);

        // USING SETTERS to set up the environment and set the related instances

        gameController.setPrimaryStage(primaryStage);
        gameController.setBoardView(boardView);
        gameController.setInfoBarView(infoBarView);
        gameController.setWinView(winView);
        gameController.setConfirmNewGameView(confirmNewGameView);
        gameController.setPreferencesView(preferencesView);
        gameController.setColumnsSelectorDispatcher(columnsSelectorDispatcher);
        gameController.setMenuBarDispatcher(menuBarDispatcher);

        menuBarController.setAboutView(aboutView);
        menuBarController.setHelpView(helpView);
        menuBarController.setFilesView(filesView);
        menuBarController.setInfoBarView(infoBarView);

        menuBarController.addFileSelectionListener(gameController);

        confirmNewGameDispatcher.setGameController(gameController);
        columnsSelectorDispatcher.setPrimaryStage(primaryStage,scene);
        columnsSelectorDispatcher.setGameController(gameController);
        menuBarDispatcher.setMenuBarController(menuBarController);
        menuBarDispatcher.setGameController(gameController);
        preferencesDispatcher.setGameController(gameController);
        filesDispatcher.setMenuBarController(menuBarController);


        gameController.setGameStartedInfo();
        Locale.setDefault(Locale.forLanguageTag(connectFourBackendController.getCurrentLanguage()));
        // PRIMARY STAGE - application starting
        primaryStage.setTitle(MainFx.APP_TITLE);
        primaryStage.setScene(scene);

        primaryStage.show();
        // have fun :)
    }

    public static void main(String[] args) {
        launch(args);
    }
}
