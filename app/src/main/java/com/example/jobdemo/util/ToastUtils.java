package com.example.jobdemo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * @author Administrator
 */
public class ToastUtils {
    private static final String TAG = "ToastUtils";
    public static Toast toast;
    /**
     * static 修饰的context会造成内存泄漏，但这里使用的是applicationContext
     */
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static void init(Context context) {
        ToastUtils.context = context.getApplicationContext();
    }

    public static void shortToast(String s) {
        showToast(s, Toast.LENGTH_SHORT, -99, -99, -99, -99, -99);
    }

    public static void shortToast(int s) {
        showToast(s, Toast.LENGTH_SHORT, -99, -99, -99, -99, -99);
    }

    public static void shortToast(String s, int duration) {
        showToast(s, duration, -99, -99, -99, -99, -99);
    }

    public static void shortToast(int s, int duration) {
        showToast(s, duration, -99, -99, -99, -99, -99);
    }


    public static void LongToast(String s) {
        showToast(s, Toast.LENGTH_LONG, -99, -99, -99, -99, -99);
    }

    public static void LongToast(int s) {
        showToast(s, Toast.LENGTH_LONG, -99, -99, -99, -99, -99);
    }

    public static void LongToast(String s, int duration) {
        showToast(s, duration, -99, -99, -99, -99, -99);
    }

    public static void LongToast(int s, int duration) {
        showToast(s, duration, -99, -99, -99, -99, -99);
    }

    /**
     * @param s                文字
     * @param duration         显示时间
     * @param gravity          toast位置
     * @param xOffset          X偏移
     * @param yOffset          Y偏移
     * @param horizontalMargin 水平margin
     * @param verticalMargin   垂直margin
     */
    public static void showToast(String s, int duration, int gravity, int xOffset, int yOffset, float horizontalMargin, float verticalMargin) {
        if (toast == null) {
            toast = Toast.makeText(context, "", duration);
        }
        toast.setText(s);
        toast.setDuration(duration);
        if (gravity != -99) {
            toast.setGravity(Gravity.CENTER, xOffset, yOffset);
        }
        if (horizontalMargin != -99) {
            toast.setMargin(horizontalMargin, verticalMargin);
        }
        toast.show();
    }

    /**
     * @param s                文字资源文件
     * @param duration         显示时间
     * @param gravity          toast位置
     * @param xOffset          X偏移
     * @param yOffset          Y偏移
     * @param horizontalMargin 水平margin
     * @param verticalMargin   垂直margin
     */
    public static void showToast(int s, int duration, int gravity, int xOffset, int yOffset, float horizontalMargin, float verticalMargin) {
        if (toast == null) {
            toast = Toast.makeText(context, "", duration);
        }
        toast.setText(context.getResources().getString(s));
        toast.setDuration(duration);
        if (gravity != -99) {
            toast.setGravity(Gravity.CENTER, xOffset, yOffset);
        }
        if (horizontalMargin != -99) {
            toast.setMargin(horizontalMargin, verticalMargin);
        }
        toast.show();
    }
}
