package com.example.jobdemo.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageSizeBean implements Parcelable {
    private int width;
    private int height;
    private Bitmap bitmap;

    protected ImageSizeBean(Parcel in) {
        width = in.readInt();
        height = in.readInt();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<ImageSizeBean> CREATOR = new Creator<ImageSizeBean>() {
        @Override
        public ImageSizeBean createFromParcel(Parcel in) {
            return new ImageSizeBean(in);
        }

        @Override
        public ImageSizeBean[] newArray(int size) {
            return new ImageSizeBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeParcelable(bitmap, flags);
    }
}
