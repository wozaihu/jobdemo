package com.example.jobdemo.util;

/**
 * 防止快速点击
 */
public class NoDoubleClickUtils {
    private final static int SPACE_TIME = 500;
    private static long lastClickTime = 0;

    public static void initLastClickTime() {
        lastClickTime = 0;
    }

    /**
     * 是否是双击？
     *
     * @return true：是双击； false：不是双击；
     */
    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > SPACE_TIME) {
            lastClickTime = currentTime;
            return false;
        }
        lastClickTime = currentTime;
        return true;
    }

    /**
     * 是否是双击？
     *
     * @return true：是双击； false：不是双击；
     */
    public synchronized static boolean isDoubleClick(int stopTime) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > stopTime) {
            lastClickTime = currentTime;
            return false;
        }
        lastClickTime = currentTime;
        return true;
    }
}