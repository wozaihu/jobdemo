package com.example.jobdemo.bean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.jobdemo.BR;

public class AnimalBean extends BaseObservable {
    private String saveName;
    private String queryName;
    private String savePicturePath;
    private String queryPicturePath;

    public AnimalBean() {
    }

    public AnimalBean(@Nullable String saveName, @NonNull String savePicturePath) {
        this.saveName = saveName;
        this.savePicturePath = savePicturePath;
    }

    public AnimalBean(@NonNull String queryPicturePath) {
        this.queryPicturePath = queryPicturePath;
    }

    @Bindable
    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
        notifyPropertyChanged(BR.saveName);
    }

    @Bindable
    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
        notifyPropertyChanged(BR.queryName);
    }

    @Bindable
    public String getSavePicturePath() {
        return savePicturePath;
    }

    public void setSavePicturePath(String savePicturePath) {
        this.savePicturePath = savePicturePath;
        notifyPropertyChanged(BR.savePicturePath);
    }

    @Bindable
    public String getQueryPicturePath() {
        return queryPicturePath;
    }

    public void setQueryPicturePath(String queryPicturePath) {
        this.queryPicturePath = queryPicturePath;
        notifyPropertyChanged(BR.queryPicturePath);
    }

}
