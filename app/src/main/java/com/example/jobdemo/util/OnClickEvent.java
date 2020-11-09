package com.example.jobdemo.util;

import android.view.View;

/**
 * 防止重复点击多次访问网络的点击事件
 *
 * @author DJT
 */
public abstract class OnClickEvent implements View.OnClickListener {

    private static long lastTime;

    public abstract void singleClick(View v);

    private long delay;

    public OnClickEvent(long delay) {
        this.delay = delay;
    }

    @Override
    public void onClick(View v) {
        if (onMoreClick(v)) {
            return;
        }
        singleClick(v);
    }

    public boolean onMoreClick(View v) {
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;
        if (time < delay) {
            flag = true;
        }
        lastTime = System.currentTimeMillis();
        return flag;
    }
}
