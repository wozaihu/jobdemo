package com.example.jobdemo.animation;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyValueAnimation extends BaseActivity {
    @BindView(R.id.iv_value_animation)
    ImageView ivValueAnimation;
    @BindView(R.id.tv_change_width)
    TextView tvChangeWidth;
    private static final String TAG = "MyValueAnimation";
    @BindView(R.id.tv_equals)
    TextView tvEquals;
    @BindView(R.id.tv_xml_change_width)
    TextView tvXmlChangeWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animation);
        ButterKnife.bind(this);
        Glide.with(this)

                .load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1198093723,2182898449&fm=11&gp=0.jpg")
                .into(ivValueAnimation);
    }

    @OnClick({R.id.iv_value_animation, R.id.tv_change_width, R.id.tv_xml_change_width})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_value_animation:
                ValueAnimator valueAnimator = ValueAnimator.ofInt(tvChangeWidth.getWidth(), 300);
                valueAnimator.setDuration(3000);
                valueAnimator.setStartDelay(500);
                valueAnimator.setRepeatCount(0);
                valueAnimator.setRepeatMode(ValueAnimator.RESTART);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        Log.d(TAG, "实时数值: " + animatedValue);
                        tvChangeWidth.getLayoutParams().width = animatedValue;
                        tvChangeWidth.requestLayout();
                        if (animatedValue == 300) {
                            Log.d(TAG, "------------: 到300了动画结束了:-------------");
                            Log.d(TAG, "tvChangeWidth的具体宽度: " + tvChangeWidth.getWidth());
                        }
                    }

                });
                valueAnimator.start();

                break;
            case R.id.tv_change_width:

                break;
            case R.id.tv_xml_change_width:
                Log.d(TAG, "onViewClicked: 点击了了啊");
                ValueAnimator loadAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);
                loadAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        tvXmlChangeWidth.getLayoutParams().width = animatedValue;
                        tvXmlChangeWidth.requestLayout();
                    }
                });
//                loadAnimator.setTarget(tvXmlChangeWidth);
                loadAnimator.start();
                break;
        }
    }
}
