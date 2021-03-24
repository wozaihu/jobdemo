package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.NavigationdemoBinding;

public class NavigationDemo extends BaseActivity {

    private NavigationdemoBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NavigationdemoBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
    }
}
