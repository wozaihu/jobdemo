package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopupWindowDemo extends BaseActivity {
    @BindView(R.id.tv_show_popupwindow)
    TextView tvShowPopupwindow;
    @BindView(R.id.tv_view_bottom_show_pop)
    TextView tvViewBottomShowPop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindowdemo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_show_popupwindow, R.id.tv_view_bottom_show_pop})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_show_popupwindow) {
            showpopupwindow();
        }
    }

    private void showpopupwindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_default_popupwindow, null);
        PopupWindow window = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        window.showAtLocation(tvShowPopupwindow.getRootView(), Gravity.BOTTOM, 0, 0);
    }
}
