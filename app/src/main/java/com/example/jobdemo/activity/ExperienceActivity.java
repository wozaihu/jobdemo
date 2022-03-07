package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobdemo.databinding.ExperienceactivityBinding;

/**
 * @Author LYX
 * @Date 2022/3/1 9:53
 */
public class ExperienceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExperienceactivityBinding inflate = ExperienceactivityBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
    }
}
