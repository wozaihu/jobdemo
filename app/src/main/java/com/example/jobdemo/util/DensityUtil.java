package com.example.jobdemo.util;

import com.example.jobdemo.MyApplication;

public enum DensityUtil {
    INSTANCE;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public  int dip2px(float dpValue) {
        final float scale = MyApplication.getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public  int px2dip(float pxValue) {
        final float scale = MyApplication.getApplication().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
