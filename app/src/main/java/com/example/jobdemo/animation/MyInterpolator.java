package com.example.jobdemo.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.util.MeasureUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyInterpolator extends BaseActivity {
    @BindView(R.id.tv_accelerate)
    TextView tvAccelerate;
    @BindView(R.id.tv_overshoot)
    TextView tvOvershoot;
    @BindView(R.id.tv_accelerate_decelerate)
    TextView tvAccelerateDecelerate;
    @BindView(R.id.tv_anticipate)
    TextView tvAnticipate;
    @BindView(R.id.tv_anticipate_overshoot)
    TextView tvAnticipateOvershoot;
    @BindView(R.id.tv_bounce)
    TextView tvBounce;
    @BindView(R.id.tv_cycle)
    TextView tvCycle;
    @BindView(R.id.tv_decelerate)
    TextView tvDecelerate;
    @BindView(R.id.tv_linear)
    TextView tvLinear;
    private int screenWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        ButterKnife.bind(this);
        screenWidth = MeasureUtils.getScreenWidth();
    }

    @OnClick({R.id.tv_accelerate, R.id.tv_overshoot, R.id.tv_accelerate_decelerate, R.id.tv_anticipate, R.id.tv_anticipate_overshoot, R.id.tv_bounce, R.id.tv_cycle, R.id.tv_decelerate, R.id.tv_linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_accelerate:
                int real = screenWidth - tvAccelerate.getWidth();
                TranslateAnimation accelerateAnimatoin = new TranslateAnimation(0, real, 0, 0);
                accelerateAnimatoin.setDuration(5000);
                accelerateAnimatoin.setFillAfter(true);
                accelerateAnimatoin.setInterpolator(new AccelerateInterpolator());
                tvAccelerate.startAnimation(accelerateAnimatoin);
                break;
            case R.id.tv_overshoot:
                int tvOvershootReal = screenWidth - tvOvershoot.getWidth();
                TranslateAnimation overshootAnimatoin = new TranslateAnimation(0, tvOvershootReal, 0, 0);
                overshootAnimatoin.setDuration(5000);
                overshootAnimatoin.setFillAfter(true);
                overshootAnimatoin.setInterpolator(new OvershootInterpolator());
                tvOvershoot.startAnimation(overshootAnimatoin);
                break;
            case R.id.tv_accelerate_decelerate:
                int tvAccelerateDecelerateReal = screenWidth - tvAccelerateDecelerate.getWidth();
                TranslateAnimation tvAccelerateDecelerateAnimatoin = new TranslateAnimation(0, tvAccelerateDecelerateReal, 0, 0);
                tvAccelerateDecelerateAnimatoin.setDuration(5000);
                tvAccelerateDecelerateAnimatoin.setFillAfter(true);
                tvAccelerateDecelerateAnimatoin.setInterpolator(new AccelerateDecelerateInterpolator());
                tvAccelerateDecelerate.startAnimation(tvAccelerateDecelerateAnimatoin);
                break;
            case R.id.tv_anticipate:
                int tvAnticipateReal = screenWidth - tvAnticipate.getWidth();
                TranslateAnimation tvAnticipateAnimatoin = new TranslateAnimation(0, tvAnticipateReal, 0, 0);
                tvAnticipateAnimatoin.setDuration(5000);
                tvAnticipateAnimatoin.setFillAfter(true);
                tvAnticipateAnimatoin.setInterpolator(new AnticipateInterpolator());
                tvAnticipate.startAnimation(tvAnticipateAnimatoin);
                break;
            case R.id.tv_anticipate_overshoot:
                int tvAnticipateOvershootReal = screenWidth - tvAnticipateOvershoot.getWidth();
                TranslateAnimation tvAnticipateOvershootAnimatoin = new TranslateAnimation(0, tvAnticipateOvershootReal, 0, 0);
                tvAnticipateOvershootAnimatoin.setDuration(5000);
                tvAnticipateOvershootAnimatoin.setFillAfter(true);
                tvAnticipateOvershootAnimatoin.setInterpolator(new AnticipateOvershootInterpolator());
                tvAnticipateOvershoot.startAnimation(tvAnticipateOvershootAnimatoin);
                break;
            case R.id.tv_bounce:
                int tvBounceReal = screenWidth - tvBounce.getWidth();
                TranslateAnimation tvBounceAnimatoin = new TranslateAnimation(0, tvBounceReal, 0, 0);
                tvBounceAnimatoin.setDuration(5000);
                tvBounceAnimatoin.setFillAfter(true);
                tvBounceAnimatoin.setInterpolator(new BounceInterpolator());
                tvBounce.startAnimation(tvBounceAnimatoin);
                break;
            case R.id.tv_cycle:
                int tvCycleReal = screenWidth - tvCycle.getWidth();
                TranslateAnimation tvCycleAnimatoin = new TranslateAnimation(0, tvCycleReal, 0, 0);
                tvCycleAnimatoin.setDuration(5000);
                tvCycleAnimatoin.setFillAfter(true);
                tvCycleAnimatoin.setInterpolator(new CycleInterpolator(1));
                tvCycle.startAnimation(tvCycleAnimatoin);
                break;
            case R.id.tv_decelerate:
                int tvDecelerateReal = screenWidth - tvDecelerate.getWidth();
                TranslateAnimation tvDecelerateAnimatoin = new TranslateAnimation(0, tvDecelerateReal, 0, 0);
                tvDecelerateAnimatoin.setDuration(5000);
                tvDecelerateAnimatoin.setFillAfter(true);
                tvDecelerateAnimatoin.setInterpolator(new DecelerateInterpolator());
                tvDecelerate.startAnimation(tvDecelerateAnimatoin);
                break;
            case R.id.tv_linear:
                int tvLinearReal = screenWidth - tvLinear.getWidth();
                TranslateAnimation tvLinearAnimatoin = new TranslateAnimation(0, tvLinearReal, 0, 0);
                tvLinearAnimatoin.setDuration(5000);
                tvLinearAnimatoin.setFillAfter(true);
                tvLinearAnimatoin.setInterpolator(new LinearInterpolator());
                tvLinear.startAnimation(tvLinearAnimatoin);
                break;
        }
    }
}
