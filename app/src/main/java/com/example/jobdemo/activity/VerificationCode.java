package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.VerificationcodeBinding;
import com.example.jobdemo.util.DensityUtil;
import com.example.jobdemo.widget.VerCodeInputView;

import org.jetbrains.annotations.NotNull;

/**
 * @Author Administrator
 * @Date 2021/8/9 17:24
 */
public class VerificationCode extends BaseActivity {
    private static final String TAG = "VerificationCode";
    private VerCodeInputView inputView;
    private int style = 1;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VerificationcodeBinding inflate = VerificationcodeBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
        inputView = new VerCodeInputView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = DensityUtil.INSTANCE.dip2px(20);
        params.rightMargin = DensityUtil.INSTANCE.dip2px(20);
        inputView.setLayoutParams(params);
        inflate.fl.addView(inputView);
    }

    public void viewClick(@NotNull View view) {
        if (view.getId() == R.id.btn_upDateWidth) {
            inputView.setInputWidth(DensityUtil.INSTANCE.dip2px(10));
        } else if (view.getId() == R.id.btn_upDateNumber) {
            inputView.setInputNum(4);
        } else if (view.getId() == R.id.btn_upDateStyle) {
            style = style == 1 ? 2 : 1;
            inputView.setStyleType(style);
        } else if (view.getId() == R.id.btn_upDateTextColor) {
            inputView.setTextColor(ContextCompat.getColor(this, R.color.orange));
        }
    }
}
