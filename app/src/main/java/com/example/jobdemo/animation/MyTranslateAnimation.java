package com.example.jobdemo.animation;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.jobdemo.R;
import com.example.jobdemo.R2;
import com.example.jobdemo.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTranslateAnimation extends BaseActivity {
    @BindView(R2.id.tv_test_translate_animation)
    TextView tvTestTranslateAnimation;
    @BindView(R2.id.btn_start_translate)
    TextView btnStartTranslate;
    @BindView(R2.id.btn_move)
    TextView btnMove;
    private TranslateAnimation translateAnimation;
    private static final String TAG = "MyTranslateAnimation";
    private Animation translate_animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate中--btnx左上角相对父控件左侧的距离: " + btnStartTranslate.getLeft());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged中--btnx左上角相对父控件左侧的距离: " + btnStartTranslate.getLeft());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart中---btnx左上角相对父控件左侧的距离: " + btnStartTranslate.getLeft());
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume中---btnx左上角相对父控件左侧的距离: " + btnStartTranslate.getLeft());
    }

    @OnClick({R.id.tv_test_translate_animation, R.id.btn_start_translate, R.id.btn_move})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_test_translate_animation:          //给button设置左右移动动画,但它一开始是居中的所以要先从中间移动到右边在移动，在移动到左边

                Display defaultDisplay = getWindowManager().getDefaultDisplay();
                int width = defaultDisplay.getWidth();
                int startTranslateWidth = btnStartTranslate.getWidth();
                int left = btnStartTranslate.getLeft();
                int right = btnStartTranslate.getRight();
                int viewWhidth = right - left;
                Log.d(TAG, "view 的宽度为: " + startTranslateWidth + "--------算出来为：" + viewWhidth);
                int firstDistance = width - right;
                int i = width - startTranslateWidth;
                Log.d(TAG, "onViewClicked: left==" + left + "------------屏幕宽度-view的宽度==" + i);
                //整个屏幕移动设为5秒，计算出移动距离时间比
                int time_m = width / 5;
                if (left > 0 && btnStartTranslate.getAnimation() == null) {
                    TranslateAnimation firstAnimation = new TranslateAnimation(0, firstDistance, 0, 0);
                    firstAnimation.setRepeatCount(0);
                    firstAnimation.setDuration(2500);
                    firstAnimation.setFillAfter(true);
                    btnStartTranslate.startAnimation(firstAnimation);
                    firstAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            translateAnimation = new TranslateAnimation(firstDistance, 0 - left, 0, 0);
                            translateAnimation.setRepeatCount(Animation.INFINITE);
                            translateAnimation.setDuration(5000);
                            translateAnimation.setRepeatMode(Animation.REVERSE);
                            btnStartTranslate.startAnimation(translateAnimation);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else {
                    TranslateAnimation endAnimation = new TranslateAnimation(btnStartTranslate.getTranslationX(), 0, 0, 0);
                    Log.d(TAG, "停止时要移动的距离: " + btnStartTranslate.getTranslationX());
                    endAnimation.setRepeatCount(0);
                    float translationX = btnStartTranslate.getTranslationX();
                    int endtime = (int) ((translationX / time_m) * 1000);
                    endAnimation.setDuration(endtime);
                    endAnimation.setFillAfter(true);
                    btnStartTranslate.startAnimation(endAnimation);
                    btnStartTranslate.clearAnimation();
                }


                break;
            case R.id.btn_start_translate:
                if (tvTestTranslateAnimation.getAnimation() == null) {
                    if (translate_animation == null) {
                        translate_animation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
                    }
                    tvTestTranslateAnimation.startAnimation(translate_animation);
                }

                if (translate_animation.hasStarted()) {
                    tvTestTranslateAnimation.clearAnimation();
                }
                break;
            case R.id.btn_move:
                Display display = getWindowManager().getDefaultDisplay();
                int i1 = display.getWidth();
                int i2 = display.getHeight();
                int btnMoveWidth = btnMove.getWidth();
                int btnMoveHeight = btnMove.getHeight();
                //y的最终距离等于应用栏高度-toolbar高度，减View自身高度
                TranslateAnimation btnMoveAnimation = new TranslateAnimation(0, 0 - ((i1 - btnMoveWidth) / 2), 0, (getAppViewHeight() - btnMoveHeight) / 2);
                btnMoveAnimation.setFillAfter(true);
                btnMoveAnimation.setDuration(5000);
                btnMoveAnimation.setRepeatCount(0);
                btnMove.startAnimation(btnMoveAnimation);
                break;
        }


    }


    public int getAppViewHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int state = metrics.heightPixels - rect.height();
        return rect.height() - getToolbarHeight();
    }

    public int getToolbarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }
}
