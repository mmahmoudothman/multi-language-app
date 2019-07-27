package com.androidwave.multilanguage;

import android.app.Application;
import android.content.Context;

public class MultiLanguageApp extends Application {

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(LocaleManager.setLocale(base));
  }

  @Override
  public void onConfigurationChanged(android.content.res.Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    LocaleManager.setLocale(this);
  }
}
