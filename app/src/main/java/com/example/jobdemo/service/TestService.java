package com.example.jobdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.jobdemo.util.LogUtil;

/**
 * @author Administrator
 * Service 中开启线程 服务stop时会内存泄漏
 */
public class TestService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.showD("版本检测", "TestService----onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyThread thread = new MyThread();
        thread.start();

        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.showD("版本检测", "TestService----onDestroy");
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
//                sleep(10000);
                LogUtil.showD("版本检测", "TestService----发送消息到handler");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}