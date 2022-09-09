package com.example.jobdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.BulgeNavigationBinding;

/**
 * @author Administrator
 */
public class DraftActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, DraftActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BulgeNavigationBinding binding = BulgeNavigationBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
    }
}
