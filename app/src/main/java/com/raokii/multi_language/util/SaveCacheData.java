package com.raokii.multi_language.util;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rao
 * @date 2018/11/9
 * 保存缓存数据
 */

public class SaveCacheData {
    private static String TAG = "saveData";
    private static Context context;

    public static void register(Context appContext) {
        context = appContext.getApplicationContext();
    }

    //存入list
    public static <T> void setData(List<T> list, int type, String tag) {
        File file = context.getCacheDir();
        File Cache = null;
        // 不同帐号存入不同数据中
        String name = getName(tag);
        LogUtil.e("tag##"+name);
        Cache = new File(file, name);
        if (Cache.exists()) {
            Cache.delete();
        }
        try {
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(new FileOutputStream(Cache));
            outputStream.writeObject(list);
        } catch (IOException e) {
            LogUtil.e("writeObject##" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    //存入类
    public static <T> void setData(Activity activity, T data, String tag) {
//        PermissionUtil.checkStoragePermission(activity);
        File file = activity.getCacheDir();
        File Cache = null;
        String name = getName(tag);
        LogUtil.e("tag##"+name);
        Cache = new File(file, name);
        if (Cache.exists()) {
            Cache.delete();
        }
        try {
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(new FileOutputStream(Cache));
            outputStream.writeObject(data);
        } catch (IOException e) {
            LogUtil.e("writeObject##" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    public static <T> List<T> getData(String tag, int type) throws IllegalAccessException, InstantiationException {
        File file = context.getCacheDir();
        String name;
        File cache;
        List<T> list = new ArrayList<>();
        name = getName(tag);
        LogUtil.e("getCache:"+name);
        cache = new File(file, name);
        if (!cache.exists()) {
            return null;
        }
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(cache));
            list = (List<T>) inputStream.readObject();
            return list;
        } catch (Exception e) {
            LogUtil.e("getCache_error##"+e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getData(Context context, String tag) throws IllegalAccessException, InstantiationException {
        File file = context.getCacheDir();
        String name;
        File cache;
        T data = null;
        name = getName(tag);
        LogUtil.e("getCache"+name);
        cache = new File(file, name);
        if (!cache.exists()) {
            return null;
        }
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(cache));
            data = (T) inputStream.readObject();
            return data;
        } catch (Exception e) {
            LogUtil.e("getCache_error##"+e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static String getName(String name){
        return name;
//        if(AppKeyStorage.getIsVisiter()){
//            return name + "_visiter";
//        }else {
//            return name + "_" + Student.getInstance().accountXueHao;
//        }
    }
}
