package com.example.jobdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author LYX
 * @Date 2022/4/3 17:09
 */
public class RecyclerviewAtViewPager2 extends RecyclerView {
    public RecyclerviewAtViewPager2(@NonNull Context context) {
        super(context);
    }

    public RecyclerviewAtViewPager2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerviewAtViewPager2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int startX, startY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            startX = (int) ev.getX();
            startY = (int) ev.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == MotionEvent.ACTION_MOVE) {
            int endX = (int) ev.getX();
            int endY = (int) ev.getY();
            int disX = Math.abs(endX - startX);
            int disY = Math.abs(endY - startY);
            if (disX > disY) {
                getParent().requestDisallowInterceptTouchEvent(canScrollHorizontally(startX - endX));
            } else {
                getParent().requestDisallowInterceptTouchEvent(canScrollVertically(startY - endY));
            }
        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(ev);
    }
}

