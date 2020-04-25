package com.example.jobdemo.Animation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jobdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyValueAnimation extends AppCompatActivity {
    @BindView(R.id.iv_value_animation)
    ImageView ivValueAnimation;
    @BindView(R.id.tv_change_width)
    TextView tvChangeWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animation);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_value_animation, R.id.tv_change_width})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_value_animation:

                break;
            case R.id.tv_change_width:

                break;
        }
    }
}
