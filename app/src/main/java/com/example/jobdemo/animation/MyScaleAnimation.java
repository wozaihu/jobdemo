package com.example.jobdemo.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.jobdemo.R;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyScaleAnimation extends BaseActivity {
    @BindView(R.id.tv_test_center)
    TextView tvTestCenter;
    @BindView(R.id.iv_infinite)
    ImageView ivInfinite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myscaleanimation);
        ButterKnife.bind(this);
        Glide.with(this).load(R.mipmap.a1).into(ivInfinite);
    }

    @OnClick({R.id.tv_test_center, R.id.iv_infinite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_test_center:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.test_scale);
                tvTestCenter.startAnimation(animation);
                break;
            case R.id.iv_infinite:
                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(5000);
                scaleAnimation.setRepeatCount(Animation.INFINITE);
                scaleAnimation.setRepeatMode(Animation.REVERSE);
                ivInfinite.startAnimation(scaleAnimation);
                break;
        }
    }
}
