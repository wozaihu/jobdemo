package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

public class StudioDemo extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studiotestlayout);
    }

    public void btnClick(View view) {
        InfoShow.start(this,this.getLocalClassName());
    }
}
