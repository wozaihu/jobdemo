package com.example.jobdemo.exercise;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.ActivityRecyclerviewDemoBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class RecyclerView_Demo extends BaseActivity {
    private List<String> list;
    @SuppressLint({"UseCompatLoadingForDrawables", "StringFormatMatches"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityRecyclerviewDemoBinding inflate = ActivityRecyclerviewDemoBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
        ButterKnife.bind(this);
        //设置layoutManager
        LinearLayoutManager  layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format(getString(R.string.whichRow), (i + 1)));
        }
        inflate.rvPictureShowStyle.setLayoutManager(layoutManager);
        //默认分割线只能设置垂直还是水平
        DividerItemDecoration decoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.gradient_item_decoration));
        inflate.rvPictureShowStyle.addItemDecoration(decoration);
        inflate.rvPictureShowStyle.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        @NonNull
        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecyclerView_Demo.this).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
            holder.textView.setText(list.get(position));
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
