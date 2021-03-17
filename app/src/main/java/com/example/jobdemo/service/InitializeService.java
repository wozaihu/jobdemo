package com.example.jobdemo.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.jobdemo.MyApplication;
import com.tencent.smtt.sdk.QbSdk;

/**
 * @author Administrator
 * @date 2020/8/21 11:25
 * @desc
 */
public class InitializeService extends IntentService {
    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Log.d("初始化", "8.0+系统初始化x5");
//            context.startForegroundService(intent);
//        } else {
//            Log.d("初始化", "8.0以下初始化x5");
//            context.startService(intent);
//        }
        context.startService(intent);
        Log.d("初始化", "系统版本" + Build.VERSION.SDK_INT + "启动了服务");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("初始化", "intent==" + intent);
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
                Log.d("初始化", "开始初始化X5");
            }
        }
    }


    /**
     * 子线程进行初始化SDK操作
     **/
    private void initApplication() {
        MyApplication.getAppContent();
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("初始化", " 初始化x5 " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //初始化X5
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }


}
