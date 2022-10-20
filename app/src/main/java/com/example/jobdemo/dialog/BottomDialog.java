package com.example.jobdemo.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.jobdemo.R;

/**
 * @Author LYX
 * @Date 2022/10/11 15:30
 */
public abstract class BottomDialog extends AppCompatDialogFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置style
        setStyle(AppCompatDialogFragment.STYLE_NORMAL, R.style.BottomDialogStyle);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置 dialog 的宽高
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置 dialog 的背景为 null
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //去除标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        return createView(inflater, container);
    }


    /**
     * 重写此方法，设置布局文件
     *
     * @param inflater  布局
     * @param container 父控件
     * @return view
     */
    protected abstract View createView(LayoutInflater inflater, ViewGroup container);


}