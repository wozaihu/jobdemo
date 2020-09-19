package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.view.ListViewForScrollView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LayoutCreatePicture extends BaseActivity {
    @BindView(R.id.img_picture)
    ImageView imgPicture;
    @BindView(R.id.lv)
    ListViewForScrollView lv;
    @BindView(R.id.img_layout_create_picture)
    FloatingActionButton imgLayoutCreatePicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutcretepicture);
        ButterKnife.bind(this);
        List<String> list = new ArrayList();
        for (int i = 0; i < 50; i++) {
            list.add("第" + i + "行");
        }
        Glide.with(this).applyDefaultRequestOptions(RequestOptions.bitmapTransform(new CircleCrop())).load(R.mipmap.a1).into(imgPicture);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list));
    }

    @OnClick({R.id.img_picture, R.id.img_layout_create_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_picture:
                break;
            case R.id.img_layout_create_picture:

                break;
        }
    }
}
