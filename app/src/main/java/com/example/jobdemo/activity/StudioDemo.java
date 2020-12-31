package com.example.jobdemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.PersonBean;
import com.example.jobdemo.util.NoDoubleClickUtils;

import java.util.ArrayList;
import java.util.List;

public class StudioDemo extends BaseActivity {
    private static final String TAG = "StudioDemo";
    private List<String> hobby;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studiotestlayout);
        hobby = new ArrayList<>();
        hobby.add("游泳");
        hobby.add("烘焙");
        hobby.add("旅游");
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 26 && !getPackageManager().canRequestPackageInstalls()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(StudioDemo.this);
                    builder.setTitle("提示");
                    builder.setMessage(R.string.update_tip);
                    builder.setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                            StudioDemo.this.startActivityForResult(intent, 6310);
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                } else {
                    Toast.makeText(StudioDemo.this, "小于26", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void btnClick(View view) {
//        if (NoDoubleClickUtils.isDoubleClick()) {
//            Log.d("点击测试", "快速点击了两次: ===StudioDemo");
//            return;
//        }
        switch (view.getId()) {
            case R.id.btn_1:
                InfoShow.start(this, this.getLocalClassName());
                break;
            case R.id.btn_privacy_intent:
                startActivity(new Intent("com.example.jobdemo.INFOSHOW").putExtra("parameter", "隐式"));
                break;
            case R.id.btn_start_parcelable:
                Intent intent = new Intent(this, ShowContent.class);
                intent.putExtra("personBean", new PersonBean("董旭", 21, hobby, BitmapFactory.decodeResource(getResources(), R.mipmap.a1)));
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: 请求码==" + requestCode + "----------返回码==" + resultCode);
        if (requestCode == 6310) {
            Log.d(TAG, "onActivityResult: 请求返回");
            if (getPackageManager().canRequestPackageInstalls()) {
                Log.d(TAG, "onActivityResult: 再次检查发现成功");
            }
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "onActivityResult: 请求权限成功");
            }
        }
    }
}
