package com.example.jobdemo.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PersonBean implements Parcelable {
    private String name;
    private int age;
    private List<String> hobby;
    private Bitmap headPortrait;

    public PersonBean(String name, int age, List<String> hobby, Bitmap headPortrait) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.headPortrait = headPortrait;
    }

    protected PersonBean(Parcel in) {
        name = in.readString();
        age = in.readInt();
        hobby = in.createStringArrayList();
        headPortrait = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<PersonBean> CREATOR = new Creator<PersonBean>() {
        @Override
        public PersonBean createFromParcel(Parcel in) {
            return new PersonBean(in);
        }

        @Override
        public PersonBean[] newArray(int size) {
            return new PersonBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public Bitmap getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(Bitmap headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeStringList(hobby);
        dest.writeParcelable(headPortrait, flags);
    }
}
