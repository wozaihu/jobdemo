package com.example.jobdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.jobdemo.R;
import com.example.jobdemo.adapter.MaterialDesignAdapter;
import com.example.jobdemo.bean.PictureBean;
import com.example.jobdemo.databinding.MaterialdesignBinding;
import com.example.jobdemo.util.MyRetrofitUtil;
import com.example.jobdemo.util.SPUtil;
import com.example.jobdemo.util.ToastUtils;
import com.example.jobdemo.widget.GridSpaceItemDecoration;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * drawerLayout<抽屉布局><只能有两个子类，第一个是主屏幕，第二个是抽屉布局，
 * 要设置从那边出来，不然会覆盖主布局>
 * coordinatorLayout <协调布局>
 * APPBarLayout<appbar>
 * collapsingToolBarLayout<折叠布局>
 * <p>
 * NavigationView<用于抽屉布局做抽屉>
 * <p>
 * bottomNavigationView<底部导航布局，不好做凸出效果和红点未读提示消息效果，慎用>
 */
public class MaterialDesignDemo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        , View.OnClickListener {

    private MaterialdesignBinding binding;
    private List<PictureBean> list = new ArrayList<>();
    private MaterialDesignAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MaterialdesignBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        binding.rvImg.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && binding.fbGetCachePicture.getVisibility() == View.VISIBLE) {
                    binding.fbGetCachePicture.hide();
                    binding.fbAddPicture.hide();
                    binding.fbShowSnackBar.hide();
                } else if (dy < 0 && binding.fbGetCachePicture.getVisibility() == View.GONE) {
                    binding.fbGetCachePicture.show();
                    binding.fbAddPicture.show();
                    binding.fbShowSnackBar.show();
                }
            }
        });
        binding.rvImg.setLayoutManager(layoutManager);
        binding.rvImg.addItemDecoration(new GridSpaceItemDecoration(10, true).setEndFromSize(0));
        adapter = new MaterialDesignAdapter(this, list);
        binding.rvImg.setAdapter(adapter);
        setSupportActionBar(binding.toolbar);
        try {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.mipmap.menu));
        } catch (Exception e) {
            e.printStackTrace();
        }
        binding.nvLeftMenu.setCheckedItem(R.id.email);
        binding.nvLeftMenu.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
    }

    /**
     * toolbar菜单点击
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                break;
        }

        return true;
    }

    /**
     * drawer菜单点击
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.email:
                ToastUtils.shortToast("点击了邮寄");
                break;
            case R.id.phone:
                ToastUtils.shortToast("点击了电话");
                break;
            case R.id.location:
                ToastUtils.shortToast("点击了位置");
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb_showSnackBar:
                Snackbar.make(binding.fbShowSnackBar, "点击了悬浮按钮", Snackbar.LENGTH_INDEFINITE)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.shortToast("点击了snackBar的取消");
                            }
                        }).show();
                break;
            case R.id.fb_addPicture:
                addPicture();
                break;
            case R.id.fb_getCachePicture:
                List<PictureBean> beans = getListPictureBean("pictureList");
                if (list.size() != 0) {
                    list.clear();
                }
                list.addAll(beans);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    /**
     * 获得一直新图片并保持起来
     */
    private void addPicture() {
        Observable<PictureBean> observable = MyRetrofitUtil.INSTANCE.getRetrofitInterface().ObservableGetPicture();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PictureBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PictureBean pictureBean) {
                        savePictureBean(pictureBean, "pictureList");
                        list.add(pictureBean);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * @return 获取保存的图片集合
     */
    private List<PictureBean> getListPictureBean(String paramName) {
        String pictureStr = (String) SPUtil.getInstance().getParam(paramName, "");
        if (TextUtils.isEmpty(pictureStr)) {
            return null;
        }
        Type type = new TypeToken<List<PictureBean>>() {
        }.getType();
        List<PictureBean> pictureBeanList = new Gson().fromJson(pictureStr, type);
        return pictureBeanList;
    }

    /**
     * @param bean 保存对象集合
     */
    private void savePictureBean(PictureBean bean, String paramName) {
        List<PictureBean> pictureBeanList = getListPictureBean(paramName);
        if (pictureBeanList == null) {
            pictureBeanList = new ArrayList<>();
        }
        pictureBeanList.add(bean);
        SPUtil.getInstance().setObjectToString(paramName, pictureBeanList);
    }
}
