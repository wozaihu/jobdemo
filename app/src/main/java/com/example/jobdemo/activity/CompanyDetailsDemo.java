package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.jobdemo.databinding.CompanyDetailsDemoBinding;
import com.example.jobdemo.fragment.Vp2Fragment;

import java.util.ArrayList;

/**
 * @Author LYX
 * @Date 2022/4/6 14:07
 */
public class CompanyDetailsDemo extends AppCompatActivity {

    private CompanyDetailsDemoBinding binding;
    private String[] mTitles_2 = {"基本信息", "联系方式", "招聘职位"};
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CompanyDetailsDemoBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        fragments = new ArrayList<>();
        fragments.add(Vp2Fragment.newInstance(0));
        fragments.add(Vp2Fragment.newInstance(1));
        fragments.add(Vp2Fragment.newInstance(2));
        binding.tlTabLayout.setViewPager(binding.vp, mTitles_2, this, fragments);
    }
}
