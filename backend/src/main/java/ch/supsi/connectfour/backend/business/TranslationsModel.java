package ch.supsi.connectfour.backend.business;

import ch.supsi.connectfour.backend.application.TranslationsBusinessInterface;
import ch.supsi.connectfour.backend.dataaccess.TranslationsPropertiesDataAccess;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class TranslationsModel implements TranslationsBusinessInterface {

    private static TranslationsModel myself;

    private final TranslationsDataAccessInterface translationsDao;

    private final List<String> supportedLanguageTags;

    private Properties translations;

    protected TranslationsModel() {
        this.translationsDao = TranslationsPropertiesDataAccess.getInstance();
        this.supportedLanguageTags = translationsDao.getSupportedLanguageTags();
    }

    public static TranslationsModel getInstance() {
        if (myself == null) {
            myself = new TranslationsModel();
        }

        return myself;
    }

    @Override
    public boolean isSupportedLanguageTag(String languageTag) {
        if (!this.supportedLanguageTags.contains(languageTag)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean changeLanguage(String languageTag) {
        this.translations = translationsDao.getTranslations(Locale.forLanguageTag(languageTag));
        return this.translations != null;
    }

    @Override
    public String translate(String key) {
        return this.translations.getProperty(key);
    }

}
