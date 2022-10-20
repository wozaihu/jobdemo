package com.example.jobdemo.util;

import android.util.Log;

import com.example.jobdemo.BuildConfig;

import java.util.List;
import java.util.Map;

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

    public static void


    showD(String content) {
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

    /**
     * 打印数组或集合
     *
     * @param TAG
     * @param obj
     */
    public static void showList(String TAG, Object obj) {
        if (open) {
            if (obj instanceof Map) {
                Map<Object, Object> map = (Map) obj;
                for (Map.Entry<Object, Object> entry : map.entrySet()) {
                    Log.d(TAG, "key = " + entry.getKey() + "----value = " + entry.getValue());
                }
            } else if (obj instanceof List) {
                List list = (List) obj;
                for (int i = 0; i < list.size(); i++) {
                    Log.d(TAG, "第<" + i + ">个元素====" + list.get(i));
                }
            }
        }
    }

    /**
     * 打印数组或集合
     *
     * @param TAG
     * @param array
     */
    public static void showArray(String TAG, int[] array) {
        if (open) {
            for (int i = 0; i < array.length; i++) {
                Log.d(TAG, "第<" + i + ">个元素====" + array[i]);
            }
        }
    }

    /**
     * 打印数组或集合
     *
     * @param TAG
     * @param array
     */
    public static void showArray(String TAG, double[] array) {
        if (open) {
            for (int i = 0; i < array.length; i++) {
                Log.d(TAG, "第<" + i + ">个元素====" + array[i]);
            }
        }
    }

    /**
     * 打印数组或集合
     *
     * @param TAG
     * @param array
     */
    public static void showArray(String TAG, String[] array) {
        if (open) {
            for (int i = 0; i < array.length; i++) {
                Log.d(TAG, "第<" + i + ">个元素====" + array[i]);
            }
        }
    }
}
