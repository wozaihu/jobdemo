package com.example.jobdemo.bean;

import android.graphics.Bitmap;

public class ImageSizeBean {
    private int width;
    private int height;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ImageSizeBean{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
