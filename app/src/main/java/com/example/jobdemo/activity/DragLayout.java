package com.example.jobdemo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

/**
 * @Author Administrator
 * @Date 2021/5/29 11:16
 */
public class DragLayout extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_layout);
    }
}
