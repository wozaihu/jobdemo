package com.example.jobdemo.testLeak;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.Ashowtoast2Binding;
import com.example.jobdemo.util.ToastUtils;

/**
 * @Author LYX
 * @Date 2021/12/27 9:42
 */
public class AShowToast2 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ashowtoast2Binding binding = Ashowtoast2Binding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnShowNetworkIsAvailable.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this,"网络是否可用----"));
        binding.btnNetworkIsWifi.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this,"是否WiFi上网----"));
        binding.btnNetworkIsCellular.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this,"是否流量上网----"));
        binding.btnShowNetworkType.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this,"网络type----"));
        binding.btnShowCellphoneIP.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this,"手机网络IP----"));
        binding.btnOpenNetworkSetting.setOnClickListener(v -> {
            ToastUtils.shortToast(AShowToast2.this,"点击了打开自己");
            startActivity(new Intent(AShowToast2.this, AShowToast2.class));
        });
    }
}
