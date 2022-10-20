package com.example.jobdemo.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.view.WindowManager;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * @author Administrator
 */
public class Utils {

    /**
     * 程序是否在前台运行
     *
     * @return 是否在前台运行
     */
    public static boolean isAppOnForeground(Context context) {
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

    public static void setImmersion(WeakReference<Activity> contextWeakReference) {
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

    /**
     * @return 获得手机信息，返回字符串
     */
    public static String getPhoneInfo(Context context) {
        StringBuilder builder = new StringBuilder();
        builder.append("手机定制商：").append(Build.BRAND);
        builder.append("\n手机型号：").append(Build.MODEL);
        builder.append("\nAndroid版本：").append(Build.VERSION.RELEASE);
        builder.append("\nAndroid版本数字号：").append(Build.VERSION.SDK_INT);
        builder.append("\n主板：").append(Build.BOARD);
        builder.append("\ncpu指令集：").append(Arrays.toString(Build.SUPPORTED_ABIS));
        builder.append("\n设备参数：").append(Build.DEVICE);
        builder.append("\n显示屏参数：").append(Build.DISPLAY);
        builder.append("\n硬件名称：").append(Build.FINGERPRINT);
        builder.append("\n硬件制造商：").append(Build.MANUFACTURER);
        builder.append("\n手机制造商：").append(Build.PRODUCT);
        builder.append("\nbuilder类型：").append(Build.TYPE);
        String packageName = context.getApplicationContext().getPackageName();
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(packageName, 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                builder.append("\n项目版本：").append(packageInfo.getLongVersionCode());
            } else {
                builder.append("\n项目版本：").append(packageInfo.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    /**
     * 检测应用是否安装，Android11开始需要在清单文件中添加包名，不然需要android.permission.QUERY_ALL_PACKAGES权限
     *
     * @return
     */
    public static boolean checkAppsIsExist(Context context, String packageName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }
}
