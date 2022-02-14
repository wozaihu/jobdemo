package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.ActivityRecycleViewDemo2Binding;
import com.example.jobdemo.util.LogUtil;

import java.util.Random;

/**
 * @author Administrator
 */
public class RecycleViewDemo2 extends BaseActivity {

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecycleViewDemo2Binding binding = ActivityRecycleViewDemo2Binding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        random = new Random();
        for (int i = 0; i < 10; i++) {
            LogUtil.showD("random1随机获得的数---------" + random.nextInt(5000));
        }
    }
}