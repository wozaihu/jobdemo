package com.example.jobdemo.testLeak;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
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
        binding.btnShowNetworkIsAvailable.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this, "网络是否可用----"));
        binding.btnNetworkIsWifi.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this, "是否WiFi上网----"));
        binding.btnNetworkIsCellular.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this, "是否流量上网----"));
        binding.btnShowNetworkType.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this, "网络type----"));
        binding.btnShowCellphoneIP.setOnClickListener(v -> ToastUtils.shortToast(AShowToast2.this, "手机网络IP----"));
        binding.btnOpenNetworkSetting.setOnClickListener(v -> {
            ToastUtils.shortToast(AShowToast2.this, "点击了打开自己");
            startActivity(new Intent(AShowToast2.this, AShowToast2.class));
        });
        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp09%2F210611094Q512b-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651462528&t=95f4382ca2b6c0ed780abf2259d8972f")
                .into(binding.img1);
        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0827%2Fc0c92bd51bb72e6d12d5b877dce338e8.jpg&refer=http%3A%2F%2Ffile02.16sucai.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651462800&t=c897d57999311683acdd045a16e2b46e")
                .circleCrop()
                .into(binding.img2);
        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0827%2Fc0c92bd51bb72e6d12d5b877dce338e8.jpg&refer=http%3A%2F%2Ffile02.16sucai.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651462800&t=c897d57999311683acdd045a16e2b46e")
                .into(binding.img3);
        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp09%2F210611094Q512b-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651462528&t=95f4382ca2b6c0ed780abf2259d8972f")
                .into(binding.img4);
    }
}
