package ch.supsi.connectfour.backend.application;

public interface PreferencesBusinessInterface {

    String getCurrentLanguage();

    Object getPreference(String key);
    void changeLanguage(String languageTag);
}
