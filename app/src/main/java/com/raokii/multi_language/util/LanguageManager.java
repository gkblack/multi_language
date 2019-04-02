package com.raokii.multi_language.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import java.util.Locale;

/**
 * @author Rao
 * @date 2019/1/13
 * 多语言管理
 */
public class LanguageManager {

    private static final String LANGUAGE_DEFAUT = "0";
    public static Context context;

    public static void register(Context appContext){
        context = appContext.getApplicationContext();
        Log.e("lang","系统语言##");
    }

    //获取当前语言
    public static String getSystemLanguage(){
        Locale locale;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            locale = context.getResources().getConfiguration().getLocales().get(0);
        }else {
            locale = context.getResources().getConfiguration().locale;
        }
        Log.e("lang","语言"+locale.getLanguage() + "-"+ locale.getCountry());
        return locale.getLanguage() + "-" + locale.getCountry();
    }


    /**
     * 判断语言
     * @param language
     * @return
     */
    public static boolean decideLanguage(String language){
        boolean bool;
        String currentLanguage = getCurrentLanguage();
        if(currentLanguage.equals(language)){
            bool = true;
        }else {
            bool = false;
        }
        return bool;
    }

    public final static String CURRENT_LANGUAGE = "currentLanguage";

    public static void saveLanguage(String currentLanguage){
        SharedPreferences sp = context.getSharedPreferences(CURRENT_LANGUAGE, Context.MODE_PRIVATE);

        sp.edit()
                .putString("currentLanguage",currentLanguage)
                .apply();
    }

    public static String getCurrentLanguage(){
        return context.getSharedPreferences(CURRENT_LANGUAGE,Context.MODE_PRIVATE).getString("currentLanguage","zh-CN");
    }

}