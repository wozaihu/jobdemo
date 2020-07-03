package com.example.jobdemo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.widget.VerCodeInputView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WidgetViewDemo extends BaseActivity {
    @BindView(R.id.vcd)
    VerCodeInputView vcd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgetviewdemo);
        ButterKnife.bind(this);
        vcd.setAutoWidth();
    }
}
