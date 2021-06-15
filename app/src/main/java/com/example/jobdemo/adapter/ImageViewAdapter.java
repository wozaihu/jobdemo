package com.example.jobdemo.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.jobdemo.R;
import com.example.jobdemo.util.LogUtil;

import java.net.URL;

public class ImageViewAdapter {
    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }


    @BindingAdapter("imageUrl")
    public static void setSrc(ImageView imageView, String url) {
        LogUtil.showD("SQLiteDemo", "收到了图片地址==" + url);
        Glide.with(imageView.getContext()).load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }

    @BindingAdapter("imageUrl")
    public static void setSrc(ImageView imageView, URL url) {
        LogUtil.showD("SQLiteDemo", "收到了图片地址==" + url);
        Glide.with(imageView.getContext()).load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
