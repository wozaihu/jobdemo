package com.example.jobdemo.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class ChannelUtil {
    /**
     * 获取app包内的渠道标识
     */
    public static String getChannel(Context context) {
        try {
            PackageManager manager = context.getApplicationContext().getPackageManager();
            ApplicationInfo appInfo = manager.getApplicationInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}

