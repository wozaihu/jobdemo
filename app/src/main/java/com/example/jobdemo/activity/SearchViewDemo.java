package com.example.jobdemo.activity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.example.jobdemo.R;
import com.example.jobdemo.adapter.FilterAdapter;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.SearchviewdemoBinding;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.RandomNameUtil;
import com.example.jobdemo.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Administrator
 * @Date 2021/6/8 13:22
 */
public class SearchViewDemo extends BaseActivity implements SearchView.OnQueryTextListener {

    private List<String> list;
    private FilterAdapter adapter;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchviewdemoBinding inflate = SearchviewdemoBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
        LogUtil.showD("调用了onCreate");
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String name = RandomNameUtil.randomName(true, 3);
            list.add(name);
            Log.d(TAG, "名字====" + name);
        }
    }


    @Override
    protected void onPostCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        try {
            Toolbar toolbar = getParentToolbar();
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
//            toolbar.setLogo(R.mipmap.forwarding_wechat); //APP图标
//            toolbar.setNavigationIcon(R.mipmap.a1); //这个在前面 取代返回图标
            toolbar.setTitle(R.string.toolbar_title);
            toolbar.setSubtitle(R.string.toolbar_subtitle);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.exception(e);
        }
        LogUtil.showD("调用了onPostCreate");
    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView mSearchView = (SearchView) item.getActionView();
        mSearchView.onActionViewExpanded();
        mSearchView.setQueryHint(getString(R.string.inputContentPlease));
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(this);
        SearchView.SearchAutoComplete searchAutoComplete = mSearchView.findViewById(R.id.search_src_text);
        SearchManager searchManager =
                (SearchManager) getSystemService(this.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchAutoComplete.setThreshold(1);
        adapter = new FilterAdapter(this, list);
        searchAutoComplete.setAdapter(adapter);
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.shortToast(SearchViewDemo.this,"点击了" +((TextView)view).getText().toString());
                Log.d(TAG, "onItemClick: "+adapter.getItem(position));
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.app_bar_search) {
            ToastUtils.longToast(this,String.format(getString(R.string.click_object), getString(R.string.search)));
            Log.d(TAG, "onOptionsItemSelected: ------------------------------搜索");
            LogUtil.showD(TAG, String.format(getString(R.string.click_object), getString(R.string.search)));

        } else if (item.getItemId() == R.id.app_bar_save) {
            ToastUtils.longToast(this,String.format(getString(R.string.click_object), getString(R.string.save)));
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * @param query 提交时调用，值
     * @return false
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * @param newText 变化时调用
     * @return false
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        LogUtil.showD(TAG, "搜索值====" + newText);
        return false;
    }
}
