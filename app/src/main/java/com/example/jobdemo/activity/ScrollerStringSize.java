package com.example.jobdemo.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.ScrollerstringsizeBinding;
import com.example.jobdemo.util.DensityUtil;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.RandomNameUtil;

public class ScrollerStringSize extends BaseActivity {
    private static final String TAG = "移动设置字体大小";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollerstringsizeBinding inflate = ScrollerstringsizeBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
        for (int i = 0; i < 100; i++) {
            TextView textView = new TextView(this);
            textView.setText(RandomNameUtil.randomName(false, 4));
            textView.setPadding(10, 10, 10, 10);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(layoutParams);
            inflate.lay.addView(textView);
        }

        inflate.sv.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            LogUtil.showD(TAG, "移动的距离---" + scrollY);
            LogUtil.showD(TAG, "之前的距离---" + oldScrollY);
            LogUtil.showD(TAG, "之前的字体大小px---" + inflate.tvContent.getTextSize());
            LogUtil.showD(TAG, "之前的字体大小sp---" + DensityUtil.px2sp(this
                    ,inflate.tvContent.getTextSize()));
            int i = new Double(Math.floor(scrollY / 100)).intValue();
            inflate.tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14 + i);
            LogUtil.showD(TAG, "设置后的的字体大小sp---" + DensityUtil.px2sp(this
                    ,inflate.tvContent.getTextSize()));
        });
    }


}
