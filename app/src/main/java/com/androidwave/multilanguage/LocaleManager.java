package com.androidwave.multilanguage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import java.util.Locale;

public class LocaleManager {
  /**
   * For english locale
   */
  public static final String LANGUAGE_KEY_ENGLISH = "en";
  /**
   * for hindi locale
   */
  public static final String LANGUAGE_KEY_HINDI = "hi";
  /***
   * // for spanish locale
   */

  public static final String LANGUAGE_KEY_SPANISH = "es";
  /**
   * SharedPreferences Key
   */
  private static final String LANGUAGE_KEY = "language_key";

  /**
   * set current pref locale
   */
  public static Context setLocale(Context mContext) {
    return updateResources(mContext, getLanguagePref(mContext));
  }

  /**
   * Set new Locale with context
   */
  public static Context setNewLocale(Context mContext, String mLocaleKey) {
    setLanguagePref(mContext, mLocaleKey);
    return updateResources(mContext, mLocaleKey);
  }

  /**
   * Get saved Locale from SharedPreferences
   *
   * @param mContext current context
   * @return current locale key by default return english locale
   */
  public static String getLanguagePref(Context mContext) {
    SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return mPreferences.getString(LANGUAGE_KEY, LANGUAGE_KEY_ENGLISH);
  }

  /**
   * set pref key
   */
  private static void setLanguagePref(Context mContext, String localeKey) {
    SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    mPreferences.edit().putString(LANGUAGE_KEY, localeKey).commit();
  }

  /**
   * update resource
   */
  private static Context updateResources(Context context, String language) {
    Locale locale = new Locale(language);
    Locale.setDefault(locale);
    Resources res = context.getResources();
    Configuration config = new Configuration(res.getConfiguration());
    if (Build.VERSION.SDK_INT >= 17) {
      config.setLocale(locale);
      context = context.createConfigurationContext(config);
    } else {
      config.locale = locale;
      res.updateConfiguration(config, res.getDisplayMetrics());
    }
    return context;
  }

  /**
   * get current locale
   */
  public static Locale getLocale(Resources res) {
    Configuration config = res.getConfiguration();
    return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
  }
}
