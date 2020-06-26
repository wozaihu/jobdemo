package com.example.jobdemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jobdemo.util.DensityUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewholder> {
    public MainActivityAdapter(String[] array, List<Class> activity) {
        this.array = array;
        this.activity = activity;
    }

    private String[] array;
    private List<Class> activity;

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainactivity_rycyclerview, parent, false);
        MyViewholder viewholder = new MyViewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.tv_demo_name.setText(array[position]);
        holder.tv_demo_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), activity.get(position)));
                EventBus.getDefault().post("适配器跳转前");
            }
        });

        if (position == array.length - 1) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.cv_root.getLayoutParams();
            layoutParams.setMargins( DensityUtil.INSTANCE.dip2px(20), DensityUtil.INSTANCE.dip2px(10),  DensityUtil.INSTANCE.dip2px(20), DensityUtil.INSTANCE.dip2px(10));
            holder.cv_root.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView tv_demo_name;
        CardView cv_root;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tv_demo_name = itemView.findViewById(R.id.tv_demo_name);
            cv_root = itemView.findViewById(R.id.cv_root);
        }
    }
}
