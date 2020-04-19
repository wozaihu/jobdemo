package com.example.jobdemo.Notification;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jobdemo.R;
import com.example.testcreatearr.ArrUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendNotification extends AppCompatActivity {
    @BindView(R.id.iv_http)
    ImageView ivHttp;
    @BindView(R.id.btn_show_picture)
    Button btnShowPicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_http, R.id.btn_show_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_http:
                break;
            case R.id.btn_show_picture:
                Glide.with(this).load(ArrUtils.getInstance().getPictureUrl()).into(ivHttp);
                break;
        }
    }
}
