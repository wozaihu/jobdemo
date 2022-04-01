package com.example.jobdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.ResultapiuseBinding;
import com.example.jobdemo.util.ToastUtils;

/**
 * @Author Administrator
 * @Date 2021/11/27 15:14
 */
public class ResultApiUse extends BaseActivity {

    private ResultapiuseBinding inflate;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflate = ResultapiuseBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
        ActivityResultLauncher<Intent> launcherInfo = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result != null && result.getData() != null) {
                ToastUtils.shortToast(ResultApiUse.this,result.getData().getStringExtra("extra"));
            }
        });
        ActivityResultLauncher<Void> launcherPhotograph = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), result -> inflate.imageView.setImageBitmap(result));
        inflate.startInfoShow.setOnClickListener(v -> launcherInfo.launch(new Intent(ResultApiUse.this, InfoShow.class)));
        inflate.photograph.setOnClickListener(v -> launcherPhotograph.launch(null));
    }
}
