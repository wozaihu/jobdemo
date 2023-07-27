package com.example.jobdemo.activity;

import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.jobdemo.R;
import com.example.jobdemo.adapter.MaterialDesignAdapter;
import com.example.jobdemo.base.RetrofitInterface;
import com.example.jobdemo.bean.ImageShowBean;
import com.example.jobdemo.constants.Api;
import com.example.jobdemo.databinding.MaterialdesignBinding;
import com.example.jobdemo.util.ToastUtils;
import com.example.jobdemo.widget.GridSpaceItemDecoration;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
 *
 * @author Administrator
 */
public class MaterialDesignDemo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        , View.OnClickListener {

    private MaterialdesignBinding binding;
    private List<String> list = new ArrayList<>();
    private MaterialDesignAdapter adapter;
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MaterialdesignBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
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
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
//        binding.rvImg.addItemDecoration(new GridSpaceItemDecoration(10, true).setEndFromSize(0));
//        binding.rvImg.setLayoutManager(layoutManager);
        binding.rvImg.setLayoutManager(new LinearLayoutManager(this));
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

        addPicture(page);
    }

    /**
     * toolbar菜单点击
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START);
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
        int itemId = item.getItemId();
        if (itemId == R.id.email) {
            ToastUtils.shortToast(this, "点击了邮寄");
        } else if (itemId == R.id.phone) {
            ToastUtils.shortToast(this, "点击了电话");
        } else if (itemId == R.id.location) {
            ToastUtils.shortToast(this, "点击了位置");
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fb_showSnackBar) {
            Snackbar.make(binding.fbShowSnackBar, "点击了悬浮按钮", Snackbar.LENGTH_INDEFINITE)
                    .setAction("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.shortToast(MaterialDesignDemo.this, "点击了snackBar的取消");
                        }
                    }).show();
        } else if (id == R.id.fb_addPicture) {
            if (page < 14) {
                addPicture(++page);
            } else {
                ToastUtils.shortToast(this, "目前是第" + page + "页,没有更多了");
            }
        } else if (id == R.id.fb_getCachePicture) {

        }
    }

    /**
     * 获得一直新图片并保持起来
     */
    private void addPicture(int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.CARTOON_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Observable<ImageShowBean> observable = retrofit.create(RetrofitInterface.class).getCartoonPicture(String.valueOf(page));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageShowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageShowBean bean) {
//                        list.addAll(bean.getData().getPhotoList());
//                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
