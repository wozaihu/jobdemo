package com.example.jobdemo.util;

import android.os.Environment;

/**
 * @Author Administrator
 * @Date 2021/11/17 16:51
 */
public class EnvironmentUtil {

    /**
     * @return 是否可以读写
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * @return 是否可以读
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
