package com.example.jobdemo.util;

import android.content.Context;
import android.widget.Toast;


/**
 * @author Administrator toast工具类
 */
public class ToastUtils {
    public static Toast toast;

    private ToastUtils() {
        throw new UnsupportedOperationException("不能被实例化");
    }

    /**
     * 一定要在application中初始化,感觉还是不要这样写
     */

    public static void init(Context context) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
    }

    public static void shortToast(String s) {
        cancel();
        toast.setText(s);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


    public static void timerToast(String s, int duration) {
        cancel();
        toast.setText(s);
        toast.setDuration(duration);
        toast.show();
    }

    public static void longToast(String s) {
        cancel();
        toast.setText(s);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public static void cancel() {
        toast.cancel();
    }
}
