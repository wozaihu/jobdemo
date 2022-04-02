package com.example.jobdemo.animation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.jobdemo.MyApplication;
import com.example.jobdemo.R;
import com.example.jobdemo.activity.CameraDemo;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.MainOnDestroy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAlpha extends BaseActivity {
    @BindView(R.id.tv_alpha)
    TextView tvAlpha;
    @BindView(R.id.iv_alpha)
    ImageView ivAlpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myalpha);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Glide.with(this)

                .load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1974547380,3759934635&fm=26&gp=0.jpg")
                .into(ivAlpha);
    }

    @OnClick({R.id.tv_alpha, R.id.iv_alpha, R.id.btn_Start_CameraDemo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_alpha:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.test_alpha);
                tvAlpha.startAnimation(animation);
                break;
            case R.id.iv_alpha:
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.0f);
                alphaAnimation.setDuration(3000);
                alphaAnimation.setRepeatCount(Animation.INFINITE);
                alphaAnimation.setRepeatMode(Animation.REVERSE);
                ivAlpha.startAnimation(alphaAnimation);
                break;
            case R.id.btn_Start_CameraDemo:
                startActivity(new Intent(this, CameraDemo.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MainOnDestroy mainOnDestroy) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
