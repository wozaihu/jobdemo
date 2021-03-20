package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.jobdemo.BR;
import com.example.jobdemo.R;
import com.example.jobdemo.adapter.LVAdapter;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.Book;
import com.example.jobdemo.bean.User;
import com.example.jobdemo.databinding.DatabindingdemoBinding;
import com.example.jobdemo.myInterface.MyLifeCycleObserver;
import com.example.jobdemo.util.RandomNameUtil;

import java.util.ArrayList;
import java.util.List;

public class DataBinDingDemo extends BaseActivity implements View.OnClickListener {

    private DatabindingdemoBinding databindingdemoBinding;
    private User user;
    private Book book;
    private List<Book> list = new ArrayList<>();
    private LVAdapter<Book> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databindingdemoBinding = DataBindingUtil.setContentView(this, R.layout.databindingdemo);
        user = new User("李白", null);
        databindingdemoBinding.setUser(user);

        book = new Book("Hollow world", null);
        databindingdemoBinding.setBook(book);
        databindingdemoBinding.lvRandomName.setEmptyView(databindingdemoBinding.tvEmpty);
        list.add(new Book(RandomNameUtil.randomName(false, 4), null));
        adapter = new LVAdapter<>(list, R.layout.item_lvadapter, BR.itemBook);
        databindingdemoBinding.setAdapter(adapter);
        getLifecycle().addObserver(new MyLifeCycleObserver());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_changeData:
                databindingdemoBinding.setUser(new User("白居易", "女"));
                break;
            case R.id.btn_BaseObservable:
                book.setBookAuthor("张伟");
                book.authorGender.set("男");
                break;
            case R.id.btn_addRandomName:
                book.obRandomList.add(RandomNameUtil.randomName(false, 4));
                break;
        }
    }
}
