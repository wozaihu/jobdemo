package com.example.jobdemo.testLeak;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.AshowtoastBinding;
import com.example.jobdemo.util.ToastUtils;

/**
 * @Author LYX
 * @Date 2021/12/27 9:42
 */
public class AShowToast3 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AshowtoastBinding binding = AshowtoastBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnShowToast.setOnClickListener(v -> {
            ToastUtils.longToast(this,"耍起有味道得很");
        });
        binding.btnSaveData.setOnClickListener(v -> {
            ToastUtils.shortToast(this,"你自己玩吧");
        });
    }
}
