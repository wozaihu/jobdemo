package com.example.jobdemo.exercise;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.R;
import com.example.jobdemo.base.DjtActivity;
import com.example.jobdemo.databinding.ActivityRecyclerviewDemoBinding;
import com.example.jobdemo.util.ToastUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 */
public class RecyclerViewDemo extends DjtActivity {
    private final List<String> list = new ArrayList<>();
    private final int listSize = 20;
    private int listPage = 1;
    private ActivityRecyclerviewDemoBinding inflate;
    private MyAdapter adapter;
    private static final int PAGE = 5;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint({"StringFormatMatches", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflate = ActivityRecyclerviewDemoBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
        inflate.refreshLayout.setOnRefreshListener(refreshlayout -> {
            listPage = 1;
            getData();
        });
        inflate.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                //传入false表示加载失败
                listPage++;
                getData();
            }
        });
        inflate.refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        //设置layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        inflate.rvPictureShowStyle.setLayoutManager(layoutManager);
        //默认分割线只能设置垂直还是水平
        DividerItemDecoration decoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        decoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.gradient_item_decoration)));
        inflate.rvPictureShowStyle.addItemDecoration(decoration);
        adapter = new MyAdapter();
        inflate.rvPictureShowStyle.setAdapter(adapter);
        inflate.refreshLayout.autoRefresh();
    }

    @SuppressLint("StringFormatMatches")
    private void getData() {
        if (listPage == 1) {
            list.clear();
        }
        for (int i = 0; i < listSize; i++) {
            list.add(String.format(getString(R.string.whichRow), (list.size() + 1)));
        }

        if (listPage != 1) {
            adapter.notifyItemRangeInserted(adapter.getItemCount(), listSize);
        } else {
            adapter.notifyDataSetChanged();
        }
        inflate.refreshLayout.finishLoadMore();
        inflate.refreshLayout.finishRefresh();
        if (listPage == PAGE) {
            inflate.refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        @NonNull
        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecyclerViewDemo.this).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
            holder.textView.setText(list.get(position));
            holder.itemView.setOnClickListener(v -> {
                ToastUtils.shortToast(holder.textView.getContext(), "点击了-------" + list.get(position));
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            textView.setGravity(Gravity.CENTER);
        }
    }
}
