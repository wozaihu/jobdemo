package com.example.jobdemo.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.jobdemo.R;
import com.example.jobdemo.R2;
import com.example.jobdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyRotate extends BaseActivity {
    @BindView(R2.id.tv_rotate)
    TextView tvRotate;
    @BindView(R2.id.iv_rotate)
    ImageView ivRotate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrotate);
        ButterKnife.bind(this);
        Glide.with(this).load(R.mipmap.a3).into(ivRotate);
    }

    @OnClick({R.id.tv_rotate, R.id.iv_rotate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rotate:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.test_rotate);
                tvRotate.startAnimation(animation);
                break;
            case R.id.iv_rotate:

                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(3000);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setStartOffset(0);
                rotateAnimation.setRepeatMode(Animation.RESTART);
                rotateAnimation.setRepeatCount(Animation.INFINITE);
                ivRotate.startAnimation(rotateAnimation);
                break;
        }
    }
}
