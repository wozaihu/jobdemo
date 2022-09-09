package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.ShowCellPhoneInfoBinding;
import com.example.jobdemo.util.DensityUtil;
import com.example.jobdemo.util.Utils;

/**
 * @Author LYX
 * @Date 2022/9/8 14:55
 */
public class ShowCellPhoneInfo extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowCellPhoneInfoBinding binding = ShowCellPhoneInfoBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        TextView textView = new TextView(this);
        textView.setText(Utils.getPhoneInfo(this));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setPadding(DensityUtil.dip2px(this, 10)
                , DensityUtil.dip2px(this, 3)
                , DensityUtil.dip2px(this, 10)
                , DensityUtil.dip2px(this, 3));

        binding.rootView.addView(textView);
    }
}
