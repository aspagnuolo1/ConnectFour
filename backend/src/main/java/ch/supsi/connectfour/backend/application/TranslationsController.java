package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.business.TranslationsModel;
import ch.supsi.connectfour.backend.business.PreferencesModel;

public class TranslationsController {

    private static TranslationsController myself;

    private final TranslationsBusinessInterface translationsModel;

    private final PreferencesBusinessInterface preferencesModel;

    protected TranslationsController() {
        this.preferencesModel = PreferencesModel.getInstance();
        this.translationsModel = TranslationsModel.getInstance();

        String currentLanguage = preferencesModel.getCurrentLanguage();
        this.translationsModel.changeLanguage(currentLanguage);
    }

    public static TranslationsController getInstance() {
        if (myself == null) {
            myself = new TranslationsController();
        }

        return myself;
    }

    /**
     * Translate the given key
     *
     * @param key
     *
     * @return String
     */
    public String translate(String key) {

        return this.translationsModel.translate(key);
    }
    public boolean changeLanguage(String languageTag) {
        return this.translationsModel.changeLanguage(languageTag);
    }

}
