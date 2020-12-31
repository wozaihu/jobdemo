package com.example.jobdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobdemo.R;
import com.example.jobdemo.util.AppInfoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoShow extends AppCompatActivity {
    private static final String TAG = "InfoShow";
    @BindView(R.id.tv_sha1)
    TextView tvSha1;
    @BindView(R.id.tv_md5)
    TextView tvMd5;
    @BindView(R.id.tv_sha265)
    TextView tvSha265;
    @BindView(R.id.tv_child)
    TextView tvChild;

    public static void start(Context context, String parameter) {
        Intent starter = new Intent(context, InfoShow.class);
        starter.putExtra("parameter", parameter);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        Log.d("点击测试", "onCreate: ===InfoShow");
        if (getIntent().getStringExtra("parameter") != null) {
            String extra = getIntent().getStringExtra("parameter");
            tvSha265.setText("从" + extra + "跳转过来");
        }
        tvSha1.setText(AppInfoUtils.sHA1(this));
        tvMd5.setText("包名: " + getPackageName());
        Log.d(TAG, "包名: " + getPackageName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("点击测试", "onRestart: ===InfoShow");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("点击测试", "onResume: ===InfoShow");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("点击测试", "onPause: ===InfoShow");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("点击测试", "onStop: ===InfoShow");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("点击测试", "onDestroy: ===InfoShow");
    }

    @OnClick({R.id.tv_sha1, R.id.tv_md5, R.id.tv_sha265, R.id.tv_child})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sha1:
                break;
            case R.id.tv_md5:
                break;
            case R.id.tv_sha265:
                break;
            case R.id.tv_child:
                Toast.makeText(this, "点击了子view", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
