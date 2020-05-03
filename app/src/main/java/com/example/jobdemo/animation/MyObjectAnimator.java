package com.example.jobdemo.animation;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jobdemo.R;
import com.example.jobdemo.evaluator.GradualCircleColorEvaluator;
import com.example.jobdemo.view.GradualCircleColor;
import com.lyx.utilslibrary.MeasureUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyObjectAnimator extends AppCompatActivity {
    @BindView(R.id.tv_rotate)
    TextView tvRotate;
    @BindView(R.id.tv_alpha)
    TextView tvAlpha;
    @BindView(R.id.tv_translate)
    TextView tvTranslate;
    @BindView(R.id.tv_scale)
    TextView tvScale;
    private static final String TAG = "MyObjectAnimator";
    @BindView(R.id.tv_test_property)
    TextView tvTestProperty;
    @BindView(R.id.gradual_circle_color)
    GradualCircleColor gradualCircleColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_object_animator);
        ButterKnife.bind(this);
        ObjectAnimator circleColerAnimator = ObjectAnimator.ofObject(gradualCircleColor, "backGroundColer"
                , new GradualCircleColorEvaluator(), "#0000FF", "#FF0000");
        circleColerAnimator.setDuration(5000);
        circleColerAnimator.start();
        //旋转
        ObjectAnimator rotation = ObjectAnimator.ofFloat(tvRotate, "rotation", 0f, 360f);
        rotation.setDuration(2000);
        rotation.setRepeatCount(ObjectAnimator.INFINITE);
        rotation.setRepeatMode(ObjectAnimator.RESTART);
        rotation.start();

        //透明
        tvAlpha.setBackgroundColor(Color.RED);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(tvAlpha, "alpha", 1f, 0f);
        alpha.setDuration(5000);
        alpha.setRepeatCount(ObjectAnimator.INFINITE);
        alpha.setRepeatMode(ObjectAnimator.REVERSE);
        alpha.start();

        //平移

        tvTranslate.post(new Runnable() {
            @Override
            public void run() {
                int screenWidth = MeasureUtils.getScreenWidth(getApplicationContext());
                int width = tvTranslate.getWidth();
                float real = Float.valueOf(screenWidth - width);
                Log.d(TAG, "view的宽度: " + width + "--------转换后的屏幕宽度：" + real);
                ObjectAnimator translate = ObjectAnimator.ofFloat(tvTranslate, "translationX", 0f, real, -real);
                translate.setDuration(5000);
                translate.setRepeatCount(ObjectAnimator.INFINITE);
                translate.setRepeatMode(ObjectAnimator.REVERSE);
                translate.start();
            }
        });

        //缩放
        ObjectAnimator scale = ObjectAnimator.ofFloat(tvScale, "scaleX", 1f, 3f);
        scale.setDuration(5000);
        scale.setRepeatCount(ObjectAnimator.INFINITE);
        scale.setRepeatMode(ObjectAnimator.REVERSE);
        scale.start();
    }

    @OnClick({R.id.tv_rotate, R.id.tv_alpha, R.id.tv_translate, R.id.tv_scale})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rotate:
                break;
            case R.id.tv_alpha:
                break;
            case R.id.tv_translate:
                break;
            case R.id.tv_scale:
                ViewWraper wraper = new ViewWraper(tvTestProperty);
                ObjectAnimator animator = ObjectAnimator.ofInt(wraper, "width", tvTestProperty.getWidth(), 500);
                animator.setDuration(3000);
                animator.start();
                break;
        }
    }

    //因为view的setwidth 是设置最大和最小宽度，所以要真的改变view的宽度要先包装一下
    private static class ViewWraper {
        View viewWraper;

        public int getWidth() {
            return viewWraper.getLayoutParams().width;
        }

        public void setWidth(int width) {
            viewWraper.getLayoutParams().width = width;
            viewWraper.requestLayout();
        }


        public ViewWraper(View view) {
            this.viewWraper = view;
        }
    }
}


