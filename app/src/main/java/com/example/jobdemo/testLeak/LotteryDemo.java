package com.example.jobdemo.testLeak;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.AshowtoastBinding;
import com.example.jobdemo.util.ToastUtils;
import com.turntableview.ITurntableListener;

/**
 * @Author LYX
 * @Date 2021/12/27 9:42
 */
public class LotteryDemo extends BaseActivity {


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AshowtoastBinding binding = AshowtoastBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.ivNode.setOnClickListener(v->{
            binding.turntable.startRotate(new ITurntableListener() {
                @Override
                public void onStart() {
                    Toast.makeText(LotteryDemo.this, "开始抽奖", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onEnd(int position, String name) {
                    ToastUtils.shortToast(LotteryDemo.this,"抽奖结束抽中第" + (position + 1) + "位置的奖品:" + name);
                }
            });
        });
    }

}
