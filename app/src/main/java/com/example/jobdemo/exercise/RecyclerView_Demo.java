package com.example.jobdemo.exercise;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.jobdemo.R;
import com.example.jobdemo.adapter.RecyclerView_Demo_Adapter;
import com.example.jobdemo.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerView_Demo extends BaseActivity {
    @BindView(R.id.rv_picture_show_style)
    RecyclerView rvPictureShowStyle;
    @BindView(R.id.img_show)
    ImageView imgShow;
    private StaggeredGridLayoutManager layoutManager;
    private RecyclerView_Demo_Adapter recyclerViewDemoAdapter;
    private static final String TAG = "RecyclerView_Demo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_demo);
        ButterKnife.bind(this);
        //设置layoutManager
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvPictureShowStyle.setLayoutManager(layoutManager);

        //禁止交换位置
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        //去掉动画
        ((DefaultItemAnimator) rvPictureShowStyle.getItemAnimator()).setSupportsChangeAnimations(false);
        ((SimpleItemAnimator) rvPictureShowStyle.getItemAnimator()).setSupportsChangeAnimations(false);
        rvPictureShowStyle.getItemAnimator().setChangeDuration(0);

        rvPictureShowStyle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                layoutManager.invalidateSpanAssignments();//防止第一行到顶部有空白
            }
        });
        recyclerViewDemoAdapter = new RecyclerView_Demo_Adapter();
        rvPictureShowStyle.setAdapter(recyclerViewDemoAdapter);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        GlideUtils.cleanImageSize();
        if (recyclerViewDemoAdapter != null) {
            recyclerViewDemoAdapter.cleanImageSizeBean();
        }
    }
}
