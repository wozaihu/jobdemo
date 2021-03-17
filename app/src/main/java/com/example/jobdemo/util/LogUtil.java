package com.example.jobdemo.util;

import android.util.Log;

import com.example.jobdemo.BuildConfig;

//log工具类
public class LogUtil {

    public static boolean open = BuildConfig.DEBUG;
    public static String TAG = "logUtil";

    public static void showE(String s) {
        if (open) {
            Log.e(TAG, s);
        }
    }

    public static void showE(String TAG, String content) {
        if (open) {
            Log.e(TAG, content);
        }
    }

    public static void showI(String content) {
        if (open) {
            Log.i(TAG, content);
        }
    }

    public static void showI(String TAG, String content) {
        if (open) {
            Log.i(TAG, content);
        }
    }

    public static void showW(String content) {
        if (open) {
            Log.w(TAG, content);
        }
    }

    public static void showW(String TAG, String content) {
        if (open) {
            Log.w(TAG, content);
        }
    }

    public static void showD(String content) {
        if (open) {
            Log.d(TAG, content);
        }
    }

    public static void showD(String TAG, String content) {
        if (open) {
            Log.d(TAG, content);
        }
    }

    /**
     * 打印try catch出现的异常
     */
    public static void exception(Exception e) {
        if (open) {
            Log.e(TAG, "开始出现奔溃的位置: " + e.getMessage() + "-----------------", e.getCause());
        }
    }

    /**
     * 打印try catch出现的异常
     */
    public static void exception(String TAG, Exception e) {
        if (open) {
            Log.e(TAG, "开始出现奔溃的位置: " + e.getMessage() + "-----------------", e.getCause());
        }
    }
}
