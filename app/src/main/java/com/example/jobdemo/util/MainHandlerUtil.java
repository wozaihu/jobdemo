package com.example.jobdemo.util;

import android.os.Handler;
import android.os.Looper;


/**
 * @author Administrator
 */
public class MainHandlerUtil {
    public Handler handler;

    private MainHandlerUtil() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
    }

    public static MainHandlerUtil getMainHandler() {
        return SingleOnHolder.MAIN_HANDLER_UTIL;
    }

    private static class SingleOnHolder {
        private static final MainHandlerUtil MAIN_HANDLER_UTIL = new MainHandlerUtil();
    }
}
