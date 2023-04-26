package com.example.jobdemo.bean;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.example.jobdemo.BR;


/**
 * @author Administrator
 */
public class Book extends BaseObservable {
    private String bookName;
    private String bookAuthor;
    public ObservableField<String> authorGender = new ObservableField<>("女");
    public ObservableArrayList<String> obRandomList = new ObservableArrayList<>();

    public Book(String bookName, @Nullable String bookAuthor) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    @Bindable
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        notifyPropertyChanged(BR.bookName);
    }

    @Bindable
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
        notifyPropertyChanged(BR.bookAuthor);
    }
}
