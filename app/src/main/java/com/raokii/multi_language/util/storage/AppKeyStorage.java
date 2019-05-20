package com.raokii.multi_language.util.storage;


import com.raokii.multi_language.entity.AllView;

/**
 * Created by ruiaa on 2017/8/8.
 */

public class AppKeyStorage {

    public static void saveLanguage(String language, Object o) {
        KeyStorage.put(language, o);
    }

    public static Object getLanguage(String language) {
        return KeyStorage.get(language);
    }

    public static String getLanguageStr(String lang) {
        return KeyStorage.get(lang, "zh-CN");
    }

    public static void saveLanguageStr(String lang) {
        KeyStorage.put(lang, lang);
    }

    public static void saveView(String className, AllView allView){
        KeyStorage.put(className, allView);
    }

    public static AllView getView(String className){
        return KeyStorage.get(className);
    }


}