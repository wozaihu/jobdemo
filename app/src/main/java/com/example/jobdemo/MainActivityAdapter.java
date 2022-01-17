package com.example.jobdemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.util.DensityUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author Administrator
 */
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {
    private final List<Class> activitys;

    public MainActivityAdapter(List<Class> activitys) {
        this.activitys = activitys;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainactivity_rycyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_demo_name.setText(activitys.get(position).getSimpleName());
        holder.tv_demo_name.setOnClickListener((v) -> {
            v.getContext().startActivity(new Intent(v.getContext(), activitys.get(position)));
        });

        if (position == activitys.size() - 1) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.cv_root.getLayoutParams();
            layoutParams.setMargins(DensityUtil.dip2px(holder.cv_root.getContext(), 20)
                    , DensityUtil.dip2px(holder.cv_root.getContext(),10)
                    , DensityUtil.dip2px(holder.cv_root.getContext(),20)
                    , DensityUtil.dip2px(holder.cv_root.getContext(),10));
            holder.cv_root.setLayoutParams(layoutParams);
        } else {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.cv_root.getLayoutParams();
            layoutParams.setMargins(DensityUtil.dip2px(holder.cv_root.getContext(),20)
                    , DensityUtil.dip2px(holder.cv_root.getContext(),10)
                    , DensityUtil.dip2px(holder.cv_root.getContext(),20)
                    , DensityUtil.dip2px(holder.cv_root.getContext(),0));
            holder.cv_root.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return activitys.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_demo_name;
        CardView cv_root;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_demo_name = itemView.findViewById(R.id.tv_demo_name);
            cv_root = itemView.findViewById(R.id.cv_root);
        }
    }
}
