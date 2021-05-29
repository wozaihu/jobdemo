package com.example.jobdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

public class DraftActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, DraftActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulge_navigation);
    }
}
