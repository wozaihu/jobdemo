package com.example.jobdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.example.jobdemo.base.AppDataBase;
import com.example.jobdemo.database.DaoMaster;
import com.example.jobdemo.database.DaoSession;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.ProcessUtil;
import com.example.jobdemo.util.SpUtil;
import com.example.jobdemo.util.Utils;
import com.lzy.okgo.OkGo;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;

import java.util.HashMap;

import dagger.hilt.android.HiltAndroidApp;
import io.openim.android.sdk.OpenIMClient;
import io.openim.android.sdk.listener.OnAdvanceMsgListener;
import io.openim.android.sdk.listener.OnConnListener;
import io.openim.android.sdk.listener.OnConversationListener;
import io.openim.android.sdk.listener.OnFriendshipListener;
import io.openim.android.sdk.listener.OnGroupListener;
import io.openim.android.sdk.listener.OnSignalingListener;
import io.openim.android.sdk.listener.OnUserListener;
import io.openim.android.sdk.models.InitConfig;
import io.rong.imkit.RongIM;

/**
 * @author Administrator
 */
@HiltAndroidApp
public class MyApplication extends Application {
    private DaoMaster daoMaster;
    private static DaoSession daoSession;
    private DaoMaster.DevOpenHelper devOpenHelper = null;
    /**
     * 这样去取applicationContext
     */
    public static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // 获取当前包名
        String packageName = getPackageName();
        // 获取当前进程名
        String processName = ProcessUtil.getCurProcessName(getApplicationContext());
        Log.d("初始化", "进程名: " + processName);
        // 初始化Okgo
        OkGo.getInstance().init(this);
        //主进程
        if (packageName.equals(processName)) {
            init();
            initOpenIm();
        }
    }

    private void initOpenIm() {
        String openImTag = "openIm打印信息";
        InitConfig initConfig = new InitConfig(
                "http://192.168.8.215:10002",//SDK api地址
                "ws://192.168.8.215:10001",//SDK WebSocket地址
                getFilesDir().getPath()//SDK数据库存储目录
        );
        LogUtil.showD(openImTag, "开始初始化openIm");
        boolean initSDK = OpenIMClient.getInstance().initSDK(
                application, //Application
                initConfig,//InitConfig
                new OnConnListener() {
                    @Override
                    public void onConnectFailed(long code, String error) {
                        //连接服务器失败
                        LogUtil.showD(openImTag, "连接失败");
                    }

                    @Override
                    public void onConnectSuccess() {
                        //连接服务器成功
                        LogUtil.showD(openImTag, "连接成功");
                    }

                    @Override
                    public void onConnecting() {
                        //连接服务器中...
                        LogUtil.showD(openImTag, "连接中...");
                    }

                    @Override
                    public void onKickedOffline() {
                        //当前用户被踢下线
                        LogUtil.showD(openImTag, "当前用户被踢下线");
                    }

                    @Override
                    public void onUserTokenExpired() {
                        //登录票据已经过期
                        LogUtil.showD(openImTag, "登录票据已经过期");
                    }
                });

        LogUtil.showD(openImTag, "初始化openIm结果：" + initSDK);
// Set listener，sdk采用的set方式，多次set会替换上次set,建议使用一个中间件使用add方式分发执行回调,参考demo IMEvent这个类

        // 当前登录用户资料变更回调
        OpenIMClient.getInstance().userInfoManager.setOnUserListener(new OnUserListener() {

        });
        // 收到新消息，已读回执，消息撤回监听。
        OpenIMClient.getInstance().messageManager.setAdvancedMsgListener(new OnAdvanceMsgListener() {
        });
        // 好关系发生变化监听
        OpenIMClient.getInstance().friendshipManager.setOnFriendshipListener(new OnFriendshipListener() {
        });
        // 会话新增或改变监听
        OpenIMClient.getInstance().conversationManager.setOnConversationListener(new OnConversationListener() {
        });
        // 群组关系发生改变监听
        OpenIMClient.getInstance().groupManager.setOnGroupListener(new OnGroupListener() {
        });
        // 信令监听

//        OpenIMClient.getInstance().signalingManager.setSignalingListener(new OnSignalingListener() {});
    }

    public static Context getAppContext() {
        return application.getApplicationContext();
    }

    /**
     *
     */
    private void init() {
        SpUtil.init(this);
        MultiDex.install(this);
        AppDataBase.init(this);
        UMConfigure.setLogEnabled(true);
        //友盟冷启动，用户同意隐私协议后再正式初始化
        UMConfigure.preInit(this, "5fbcaf52690bda19c789ed16", "jobdemo");
        // 是否同意隐私政策，默认为false
        SDKInitializer.setAgreePrivacy(this, true);
        try {
            // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
            SDKInitializer.initialize(this);
        } catch (BaiduMapSDKException e) {

        }
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

        devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "User_Info.db", null);
        //实例化DaoMaster对象
        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        //实例化DaoSession对象
        daoSession = daoMaster.newSession();
    }

    /**
     * @return 通过此方法, 进行增删改查
     */
    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
