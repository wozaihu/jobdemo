package com.example.jobdemo.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.FlowLayoutBinding;
import com.example.jobdemo.util.DensityUtil;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.ToastUtils;
import com.example.jobdemo.widget.ExpandableTextview;

/**
 * @author Administrator
 */
public class FlowLayoutDemo extends BaseActivity {
    public static final int SIZE = 23;
    private ExpandableTextview expandableTextview;

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
        binding.btnSetting.setOnClickListener(v -> {
                    binding.expandTv.setText(binding.et.getText().toString());
                    expandableTextview.setText(binding.et.getText().toString());
                }
        );
        binding.btnSetting2.setOnClickListener(v -> expandableTextview.setText(null));
        expandableTextview = new ExpandableTextview(this, 7);
        expandableTextview.setText("你好啊，你来啦");
        expandableTextview.setUnfoldWord("全文");
        expandableTextview.setShowFold(true);
        expandableTextview.setPadding(DensityUtil.dip2px(this, 3), 0
                , DensityUtil.dip2px(this, 3), DensityUtil.dip2px(this, 3));
        expandableTextview.setUnfoldWordColor(ContextCompat.getColor(this, R.color.picture_color_blue));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DensityUtil.dip2px(this, 10), 0, 0);
        expandableTextview.setLayoutParams(params);
        binding.getRoot().addView(expandableTextview);

        TextView view = new TextView(this);
        view.setText("无法说明");
        binding.getRoot().addView(view);
    }

}
