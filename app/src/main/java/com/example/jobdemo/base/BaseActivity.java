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

/**
 * @author Administrator
 */
public class BaseActivity extends AppCompatActivity {
    protected static String TAG = "";
    private Toolbar toolbar;
    private ViewGroup mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName() + "打印";
        mContentView = findViewById(android.R.id.content);
    }

    @Override
    public void setContentView(int layoutResId) {
        if (hasToolBar()) {
            //将activity_base.xml布局作为根布局
            super.setContentView(R.layout.layout_base);
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            //拿到activity_base.xml的base_content子布局
            FrameLayout frameLayout = findViewById(R.id.fl_root);
            //将子类的布局文件加载到base_content布局中
            mContentView.setId(View.NO_ID);
            frameLayout.setId(android.R.id.content);
            LayoutInflater.from(this).inflate(layoutResId, frameLayout, true);
        } else {
            super.setContentView(layoutResId);
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
            FrameLayout frameLayout = findViewById(R.id.fl_root);
            frameLayout.addView(view);
        } else {
            super.setContentView(view);
        }
    }

    /**
     * @return 不需要toolbar就重写该方法返回false
     */

    protected boolean hasToolBar() {
        return true;
    }

    /**
     * @return toolbar
     */
    protected Toolbar getParentToolbar() {
        if (hasToolBar()) {
            return toolbar;
        } else {
            throw new NullPointerException("toolbar of BaseActivity is NULL");
        }
    }

}
