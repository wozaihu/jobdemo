package com.example.jobdemo.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;

import java.util.List;

public enum Utils {
    INSTANCE;

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground(Context context) {
        // Returns a list of application processes that are running on the device
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getPackageName();
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) return false;
        for (RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}
