package com.example.jobdemo.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.view.SpinnerView;
import com.example.jobdemo.widget.VerCodeInputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WidgetViewDemo extends BaseActivity {
    @BindView(R.id.vcd)
    VerCodeInputView vcd;
    @BindView(R.id.btn_showDialog)
    Button btnShowDialog;
    @BindView(R.id.spv_1)
    SpinnerView spv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgetviewdemo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_showDialog, R.id.btn_showSpinnerView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_showDialog:
                showDialog();
                break;
            case R.id.btn_showSpinnerView:

                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_widgetviewdemo, null);
        builder.setView(view);
        AlertDialog dialog = builder.show();
    }
}
