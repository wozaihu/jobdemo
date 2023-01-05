package com.example.jobdemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.bean.ActivityBean;
import com.example.jobdemo.util.LogUtil;

import java.util.List;

/**
 * @author Administrator
 */
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {
    private final List<ActivityBean> activityS;

    public MainActivityAdapter(List<ActivityBean> activityS) {
        this.activityS = activityS;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainactivity_rycyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_demo_name.setText(activityS.get(position).getActivityChinesName());
        holder.itemView.setOnClickListener((v) -> {
            LogUtil.showD("activity名："+activityS.get(position).getClassName());
            v.getContext().startActivity(new Intent(v.getContext(), activityS.get(position).getClassName()));
        });
    }

    @Override
    public int getItemCount() {
        return activityS.size();
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
