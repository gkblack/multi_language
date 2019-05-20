package com.raokii.multi_language.base;

import android.app.Application;

import com.raokii.multi_language.util.LanguageManager;
import com.raokii.multi_language.util.SaveCacheData;

/**
 * @author Rao
 * @date 2019/4/2
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LanguageManager.register(this);
        LanguageManager.saveLanguage( LanguageManager.getSystemLanguage());
        SaveCacheData.register(this);
    }
}
