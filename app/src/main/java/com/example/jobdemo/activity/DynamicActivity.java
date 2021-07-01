package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.DynamicactivityBinding;

public class DynamicActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicactivityBinding inflate = DynamicactivityBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
    }
}
