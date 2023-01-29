package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.ActivityLayoutcretepictureBinding;
import com.example.jobdemo.widget.GlideCircleWithBorder;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * @author Administrator
 */
public class LayoutCreatePicture extends BaseActivity {
    public static final int FIFTY = 50;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLayoutcretepictureBinding binding = ActivityLayoutcretepictureBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        ButterKnife.bind(this);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < FIFTY; i++) {
            list.add("第" + i + "行");
        }
        Glide.with(this)
                .load(R.mipmap.a1).
                diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop().transform(new GlideCircleWithBorder(this, 3, R.color.green))
                .into(binding.imgPicture);
        binding.lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list));
    }
}
