package com.example.jobdemo.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.WindowManager;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;

public enum Utils {
    /**
     * 工具类
     */
    INSTANCE;

    /**
     * 程序是否在前台运行
     *
     * @return 是否在前台运行
     */
    public boolean isAppOnForeground(Context context) {
        // Returns a list of application processes that are running on the device
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    public void setImmersion(WeakReference<Activity> contextWeakReference) {
        Activity activity = contextWeakReference.get();
        //沉浸式效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * @return 获得年月日：20210509
     */
    public static String getYearMonthDay() {
        Calendar calendar = Calendar.getInstance();
        //取出系统当前时间的年、月、日部分
        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH) + 1;
        String str_month = monthNow < 10 ? "0" + monthNow : "" + monthNow;
        int dayOfMonthNow = calendar.get(Calendar.DAY_OF_MONTH);
        String str_day = dayOfMonthNow < 10 ? "0" + dayOfMonthNow : "" + dayOfMonthNow;
        return yearNow + str_month + str_day;
    }

    public static boolean isUpdate() {
        return false;
    }
}
