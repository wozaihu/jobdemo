package com.example.jobdemo.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jobdemo.databinding.LayoutRefreshHeadBinding;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

/**
 * @Author LYX
 * @Date 2022/3/8 10:25
 */
public class SmartRefreshLayoutHead extends LinearLayout implements RefreshHeader {
    private AnimationDrawable mProgressDrawable;
    private LayoutRefreshHeadBinding binding;
    private TextView mHeaderText;

    public SmartRefreshLayoutHead(Context context) {
        super(context);
        this.initView(context);
    }

    public SmartRefreshLayoutHead(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public SmartRefreshLayoutHead(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }

    public SmartRefreshLayoutHead(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initView(context);
    }

    private void initView(Context context) {
        binding = LayoutRefreshHeadBinding.inflate(LayoutInflater.from(context), this, false);
        mHeaderText = binding.tvHint;
        mProgressDrawable = (AnimationDrawable) binding.imgIcon.getDrawable();
        addView(binding.getRoot());
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (success) {
            mHeaderText.setText("刷新成功");
        } else {
            mHeaderText.setText("刷新失败");
        }
        mProgressDrawable.stop();
        //延迟500毫秒之后再弹回
        return 500;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        mProgressDrawable.start();
        if (newState == RefreshState.None || newState == RefreshState.PullDownToRefresh) {
            mHeaderText.setText("下拉开始刷新");
        } else if (newState == RefreshState.Refreshing) {
            mHeaderText.setText("正在刷新");
        } else if (newState == RefreshState.ReleaseToRefresh) {
            mHeaderText.setText("释放立即刷新");
        }
    }
}
