package com.example.jobdemo.util;

import android.content.Context;
import android.widget.Toast;


/**
 * @author Administrator toast工具类
 */
public class ToastUtils {
    public static Toast toast;

    public static void shortToast(Context context, String s) {
        cancel(context);
        toast.setText(s);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


    public static void timerToast(Context context,String s, int duration) {
        cancel(context);
        toast.setText(s);
        toast.setDuration(duration);
        toast.show();
    }

    public static void longToast(Context context,String s) {
        cancel(context);
        toast.setText(s);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public static void cancel(Context context) {
        if (toast!=null) {
            toast.cancel();
        }
        toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
    }
}
