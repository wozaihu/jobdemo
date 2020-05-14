package com.example.jobdemo;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getApplication() {
        return application;
    }
}
