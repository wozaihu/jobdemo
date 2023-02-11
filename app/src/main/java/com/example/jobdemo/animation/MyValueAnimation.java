package com.example.jobdemo.animation;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 */
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
    }

    @OnClick({R.id.iv_value_animation, R.id.tv_change_width, R.id.tv_xml_change_width})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_value_animation) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(tvChangeWidth.getWidth(), 300);
            valueAnimator.setDuration(3000);
            valueAnimator.setStartDelay(500);
            valueAnimator.setRepeatCount(0);
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                Log.d(TAG, "实时数值: " + animatedValue);
                tvChangeWidth.getLayoutParams().width = animatedValue;
                tvChangeWidth.requestLayout();
                if (animatedValue == 300) {
                    Log.d(TAG, "------------: 到300了动画结束了:-------------");
                    Log.d(TAG, "tvChangeWidth的具体宽度: " + tvChangeWidth.getWidth());
                }
            });
            valueAnimator.start();
        } else if (id == R.id.tv_change_width) {

        } else if (id == R.id.tv_xml_change_width) {
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
            loadAnimator.start();
        }
    }
}
