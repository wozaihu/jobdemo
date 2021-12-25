package com.example.jobdemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

/**
 * SharedPreferences的一个工具类，调用setParam就能保存String, Integer, Boolean, Float, Long类型的参数
 * 同样调用getParam就能获取到保存在手机里面的数据
 *
 * @author xiaanming
 */
public class SPUtil {

    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "share_date";
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sp;
    private static Gson gson;

    private void SPUtil() {
    }

    public static SPUtil getInstance() {
        return SingleOnHolder.spUtil;
    }

    private static class SingleOnHolder {
        private static final SPUtil spUtil = new SPUtil();
    }

    public static void init(Context context) {
        sp = context.getApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
        gson = new Gson();
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public void setParam(String key, Object object) {
        String type = object.getClass().getSimpleName();
        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }
        editor.commit();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public Object getParam(String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 此方法必须用到Gson框架（implementation 'com.google.code.gson:gson:2.8.4'）
     *
     * @param key    键
     * @param object 要保存的对象
     */
    public void setObjectToString(@NonNull String key, Object object) {
        editor.putString(key, gson.toJson(object));
        editor.commit();
    }


    /**
     * 此方法必须用到Gson框架（implementation 'com.google.code.gson:gson:2.8.4'）
     * 取到对象要自己转换一下
     *
     * @param key 要去的保存的对象
     * @return
     * @hide
     */
    @Deprecated
    public Class getObject(@NonNull String key, Class c) {
        //得到保存对象的字符串
        String param = (String) getParam(key, "");
        //Gson解析
        Class aClass = gson.fromJson(param, c.getClass());
        return aClass;
    }
}
