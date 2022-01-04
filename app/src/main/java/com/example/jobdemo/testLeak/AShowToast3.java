package com.example.jobdemo.testLeak;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

/**
 * @Author LYX
 * @Date 2021/12/27 9:42
 */
public class AShowToast3 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ashowtoast);
        findViewById(R.id.btnShowToast).setOnClickListener(v -> {
            TestToast2.showToast(this);
        });
    }
}
