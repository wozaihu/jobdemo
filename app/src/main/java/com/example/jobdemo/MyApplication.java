package com.example.jobdemo;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.jobdemo.service.InitializeService;
import com.example.jobdemo.util.AppInfoUtils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyApplication extends Application {
    private static Context application;
    public static String TAG = "全局打印";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        InitializeService.start(this);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

//        CrashReport.initCrashReport(getApplicationContext(), "e1a59cdaba", true);

        Context context = getApplicationContext();
// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
        CrashReport.initCrashReport(context, "e1a59cdaba", true, strategy);


        //debug模式下 初始化内存泄漏测试
        if (BuildConfig.DEBUG && !LeakCanary.isInAnalyzerProcess(this)
                && AppInfoUtils.sHA1(this).equals("D2:50:F6:48:44:67:AA:4A:91:55:4B:FE:6C:AC:39:4F:4C:37:D2:7F")) {
            LeakCanary.install(this);
            Log.d(TAG, "sha1值对上了，内存泄漏框架初始化了");
        }
        Log.d(TAG, "app是否在前台: ");
    }

    public static Context getApplication() {
        return application;
    }

    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
