package com.example.jobdemo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jobdemo.R;

public class BaseActivity extends AppCompatActivity {
    protected static String TAG = "";
    private Toolbar toolbar;
    private ViewGroup mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName() + "打印";
        mContentView = (ViewGroup) findViewById(android.R.id.content);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (hasToolBar()) {
            //将activity_base.xml布局作为根布局
            super.setContentView(R.layout.layout_base);
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            //拿到activity_base.xml的base_content子布局
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_root);
            //将子类的布局文件加载到base_content布局中
            mContentView.setId(View.NO_ID);
            frameLayout.setId(android.R.id.content);
            LayoutInflater.from(this).inflate(layoutResID, frameLayout, true);

            // TODO: 2021/11/16 有功能代码待编写

        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        if (hasToolBar()) {
            //将activity_base.xml布局作为根布局
            super.setContentView(R.layout.layout_base);
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            //拿到activity_base.xml的base_content子布局
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_root);
            //将子类的布局文件加载到base_content布局中
//            LayoutInflater.from(this).inflate(layoutResID, frameLayout, true);
            frameLayout.addView(view);
        } else {
            super.setContentView(view);
        }
    }

    //不需要toolbar就重写该方法返回false
    protected boolean hasToolBar() {
        return true;
    }

    //子类通过该方法获取ToolBar的引用进行相应的操作,若没有ToolBar则抛出异常
    protected Toolbar getParentToolbar() throws Exception {
        if (hasToolBar()) {
            return toolbar;
        } else {
            throw new NullPointerException("toolbar of BaseActivity is NULL");
        }
    }

}
