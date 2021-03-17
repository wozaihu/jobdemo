package com.example.jobdemo.util;

import android.os.Handler;
import android.os.Looper;


public class MainHandlerUtil {
    public Handler handler;

    private MainHandlerUtil() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
    }

    public static MainHandlerUtil getMainHandler() {
        return singleOnHolder.mainHandlerUtil;
    }

    private static class singleOnHolder {
        private static MainHandlerUtil mainHandlerUtil = new MainHandlerUtil();
    }
}
