package com.example.jobdemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.jobdemo.base.AppDataBase;
import com.example.jobdemo.util.ProcessUtil;
import com.example.jobdemo.util.SPUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;

import java.util.HashMap;

import io.rong.imkit.RongIM;

/**
 * @author Administrator
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取当前包名
        String packageName = getPackageName();
        // 获取当前进程名
        String processName = ProcessUtil.getCurProcessName(getApplicationContext());
        Log.d("初始化", "进程名: " + processName);
        //主进程
        if (packageName.equals(processName)) {
            init();
        }
    }

    /**
     *
     */
    private void init() {
        SPUtil.init(this);
        MultiDex.install(this);
        AppDataBase.init(this);
        UMConfigure.setLogEnabled(true);
        //友盟冷启动，用户同意隐私协议后再正式初始化
        UMConfigure.preInit(this, "5fbcaf52690bda19c789ed16", "jobdemo");
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setUploadProcess(true);
        // 初始化BugLy
        CrashReport.initCrashReport(getApplicationContext(), "e1a59cdaba", true, strategy);
        RongIM.init(this, "vnroth0kvlsio");
        HashMap<String, Object> map = new HashMap<>(2);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {


            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }
}
