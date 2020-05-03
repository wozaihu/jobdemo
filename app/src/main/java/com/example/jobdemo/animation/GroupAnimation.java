package com.example.jobdemo.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.jobdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupAnimation extends AppCompatActivity {
    @BindView(R.id.tv_group_animation)
    TextView tvGroupAnimation;
    @BindView(R.id.iv_group_animation)
    ImageView ivGroupAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_animation);
        ButterKnife.bind(this);
        Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions.bitmapTransform(new CircleCrop()))
                .load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1198093723,2182898449&fm=11&gp=0.jpg")
                .into(ivGroupAnimation);
    }

    @OnClick({R.id.tv_group_animation, R.id.iv_group_animation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_group_animation:
                ivGroupAnimation.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.group_animation);
                ivGroupAnimation.startAnimation(animation);
                break;
            case R.id.iv_group_animation:

                break;
        }
    }
}
