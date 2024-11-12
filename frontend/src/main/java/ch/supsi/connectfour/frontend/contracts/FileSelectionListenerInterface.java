package ch.supsi.connectfour.frontend.contracts;

public interface FileSelectionListenerInterface {
    void onFileToOpenSelected(String fileName);
    void onFileNameToSaveSelected(String fileName);
}
