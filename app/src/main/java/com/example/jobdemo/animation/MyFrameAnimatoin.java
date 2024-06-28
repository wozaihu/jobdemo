package com.example.jobdemo.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jobdemo.R;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFrameAnimatoin extends BaseActivity {
    @BindView(R.id.iv_frame_animation)
    ImageView ivFrameAnimation;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.btn_start_java_add_drawable_animation)
    Button btnStartJavaAddDrawableAnimation;
    @BindView(R.id.iv_java)
    ImageView ivJava;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @OnClick({R.id.btn_start, R.id.btn_stop, R.id.btn_start_java_add_drawable_animation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                ivFrameAnimation.setImageResource(R.drawable.captain_americe);
                animationDrawable = (AnimationDrawable) ivFrameAnimation.getDrawable();
                animationDrawable.start();
                break;
            case R.id.btn_stop:
                if (animationDrawable != null && animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                break;
            case R.id.btn_start_java_add_drawable_animation:
                AnimationDrawable javaAddAnimationDrawable = new AnimationDrawable();
                int id;
                for (int i = 0; i <= 16; i++) {
                    if (i < 10) {
                        id = getResources().getIdentifier("ssl0000" + i, "drawable", getPackageName());
                    } else {
                        id = getResources().getIdentifier("ssl000" + i, "drawable", getPackageName());
                    }
                    Drawable drawable;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        drawable = getResources().getDrawable(id, this.getTheme());
                    } else {
                        drawable = getResources().getDrawable(id);
                    }
                    javaAddAnimationDrawable.addFrame(drawable, 300);
                }
//                javaAddAnimationDrawable.setOneShot(true);
                ivJava.setImageDrawable(javaAddAnimationDrawable);
//                javaAddAnimationDrawable.stop();
                javaAddAnimationDrawable.start();
                break;
        }
    }
}
