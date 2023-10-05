package com.example.jobdemo.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        String strMonth = monthNow < 10 ? "0" + monthNow : "" + monthNow;
        int dayOfMonthNow = calendar.get(Calendar.DAY_OF_MONTH);
        String strDay = dayOfMonthNow < 10 ? "0" + dayOfMonthNow : "" + dayOfMonthNow;
        return yearNow + strMonth + strDay;
    }

    /**
     * @return 获得日期和时间
     */
    public static String getDateAndTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return "Calendar获取当前日期" + year + "年" + month + "月" + day + "日" + hour + ":" + minute + ":" + second;
    }


    /**
     * @return 指定格式获得当前日期和时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDateAndTime(String timeFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }


    /**
     * @param time       时间搓
     * @param timeFormat 格式
     * @return 字符窜
     */
    public static String getDateAndTime(Long time, String timeFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat, Locale.CHINA);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
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
    try {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        return true;
    } catch (PackageManager.NameNotFoundException e) {
        return false;
    }
}

    /**
     * @param context        上下文
     * @param apkPackageName 包名
     *                       指定包名，跳转到应用市场
     */
    public static void startAppStore(Context context, String apkPackageName) {
        Uri uri = Uri.parse("market://details?id=" + apkPackageName);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            //不是activity context
        }
    }


    public static boolean getUserType(@Nullable Context context) {
        return false;
    }

    public static String getUserId(@Nullable Context context) {
        return "80000001";
    }


    public static void openGPS(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "无法打开位置设置页面", Toast.LENGTH_SHORT).show();
        }
    }
}
