package ch.supsi.connectfour.backend.business;

import java.util.Properties;

public interface PreferencesDataAccessInterface {

    Properties getPreferences();
    void setPreferences(String languageTag);
}
