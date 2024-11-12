package ch.supsi.connectfour.backend.business;

import ch.supsi.connectfour.backend.application.PreferencesBusinessInterface;
import ch.supsi.connectfour.backend.dataaccess.PreferencesPropertiesDataAccess;

import java.util.Properties;

public class PreferencesModel implements PreferencesBusinessInterface {

    private static PreferencesModel myself;

    private final PreferencesDataAccessInterface preferencesDao;

    private final Properties userPreferences;

    protected PreferencesModel() {
        this.preferencesDao = PreferencesPropertiesDataAccess.getInstance();
        this.userPreferences = preferencesDao.getPreferences();
    }

    public static PreferencesModel getInstance() {
        if (myself == null) {
            myself = new PreferencesModel();
        }

        return myself;
    }

    @Override
    public String getCurrentLanguage() {
        return userPreferences.getProperty("language-tag");
    }

    @Override
    public Object getPreference(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }

        if (userPreferences == null) {
            return null;
        }

        return userPreferences.get(key);
    }
    public void changeLanguage(String languageTag) {
        preferencesDao.setPreferences(languageTag);
    }

}
