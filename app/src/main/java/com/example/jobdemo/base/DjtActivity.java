package com.example.jobdemo.base;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;

/**
 * @Author LYX
 * @Date 2022/3/7 11:39
 * 继承此activity默认实现点金台渐变色状态栏 和actionbar
 */
public class DjtActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String toolbarTitle;
    private ViewGroup mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
        window.setBackgroundDrawable(ContextCompat.getDrawable(this, R.mipmap.title_bar));
        mContentView = findViewById(android.R.id.content);
        mContentView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(true);
        } else {
            window.addFlags(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    @Override
    public void setContentView(int layoutResId) {
        if (hasToolBar()) {
            //将activity_base.xml布局作为根布局
            super.setContentView(R.layout.layout_base_djt);
            toolbar = findViewById(R.id.toolbar);
            ((TextView) toolbar.findViewById(R.id.title)).setText(TextUtils.isEmpty(toolbarTitle) ? getClass().getSimpleName() : toolbarTitle);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                upArrow.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(this, R.color.white), BlendMode.SRC_ATOP));
            } else {
                upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            super.setContentView(R.layout.layout_base_djt);
            toolbar = findViewById(R.id.toolbar);
            ((TextView) toolbar.findViewById(R.id.title)).setText(TextUtils.isEmpty(toolbarTitle) ? getClass().getSimpleName() : toolbarTitle);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                upArrow.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(this, R.color.white), BlendMode.SRC_ATOP));
            } else {
                upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
     * @return toolbar标题
     */

    protected void setBarTitle(String title) {
        this.toolbarTitle = title;
    }

    /**
     * @return toolbar标题
     */


    protected void setBarTitle(int titleResourceId) {
        this.toolbarTitle = getString(titleResourceId);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
