package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.application.ConnectFourBackendController;
import ch.supsi.connectfour.frontend.contracts.*;

import java.util.ArrayList;
import java.util.List;

public class MenuBarController {

    AboutViewInterface aboutView;
    HelpViewInterface helpView;
    FilesViewInterface filesView;
    InfoBarViewInterface infoBarView;
    ConnectFourBackendController backendController = ConnectFourBackendController.getInstance();
    private List<FileSelectionListenerInterface> listeners = new ArrayList<>();

    public void addFileSelectionListener(FileSelectionListenerInterface listener) {
        listeners.add(listener);
    }

    // NOTIFIERS

    public void notifyFileSelected(String fileName) {
        for (FileSelectionListenerInterface listener : listeners) {
            listener.onFileToOpenSelected(fileName);
        }
    }

    public void notifyFileToSaveSelected(String fileName) {
        for (FileSelectionListenerInterface listener : listeners) {
            listener.onFileNameToSaveSelected(fileName);
        }
    }

    // FILE VIEW

    public void showFilesView(){
        filesView.display();
    }

    public void showFilesToSelect(){
        String fileName = filesView.display(backendController.getGamesPath(), backendController.translate("label.selectFile"));
        notifyFileSelected(fileName);
    }

    public void closeFilesWindow(){
        filesView.close();
        String fileName = filesView.getFileName();
        notifyFileToSaveSelected(fileName);
    }

    // other views

    public void showAbout(){
        aboutView.display();
    }

    public void showHelp(){
        helpView.display();
    }

    // select language to change language in backend

    public void selectLanguage(String language) {
        infoBarView.display(backendController.translate("label.languageChanged"));
        backendController.changeLanguage(language);
    }

    // SETTERS used from MainFx

    public void setFilesView(FilesViewInterface filesView) {
        this.filesView = filesView;
    }

    public void setAboutView(AboutViewInterface aboutView) {
        this.aboutView = aboutView;
    }

    public void setHelpView(HelpViewInterface helpView) {
        this.helpView = helpView;
    }

    public void setInfoBarView(InfoBarViewInterface infoBarView) {
        this.infoBarView = infoBarView;
    }
}
