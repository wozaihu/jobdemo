package com.example.jobdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobdemo.R;
import com.example.jobdemo.util.AppInfoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoShow extends AppCompatActivity {
    private static final String TAG = "InfoShow";
    @BindView(R.id.tv_sha1)
    TextView tvSha1;
    @BindView(R.id.tv_md5)
    TextView tvMd5;
    @BindView(R.id.tv_sha265)
    TextView tvSha265;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        tvSha1.setText(AppInfoUtils.sHA1(this));
        tvMd5.setText("包名: " + getPackageName());
        Log.d(TAG, "包名: " + getPackageName());
    }
}
