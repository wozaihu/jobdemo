package com.example.jobdemo.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.FlowLayoutBinding;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.ToastUtils;

/**
 * @author Administrator
 */
public class FlowLayoutDemo extends BaseActivity {
    public static final int SIZE = 23;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlowLayoutBinding binding = FlowLayoutBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        for (int i = 0; i < SIZE; i++) {
            TextView view = new TextView(this);
            int finalI = i;
            view.setOnClickListener(v ->
                    ToastUtils.shortToast(this, String.format(getString(R.string.click_int), finalI)));
            view.setText(String.format(getString(R.string.whichRowInt), i));
            binding.flowLayout.addView(view);
        }
        CharSequence s = "123456789";
        LogUtil.showD("截取-2====" + s.subSequence(0, 7));

        SpannableString sp = new SpannableString(s);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.green))
                , s.length() - 2, s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        binding.tv1.setText(sp);
        binding.expandTv.setText("文言文是中国古代的一种汉语书面语言组成的文章，“五四运动”以前汉民族所使用的语言。主要包括以先秦时期的口语为基础而形成的书面语言。春秋战国时期，记载文字用的是竹简、丝绸等物。随着历史变迁，口语的演变，文言文和口语的差别逐渐扩大");
        binding.btnSetting.setOnClickListener(v -> binding.expandTv.setText(binding.et.getText().toString()));
    }
}
