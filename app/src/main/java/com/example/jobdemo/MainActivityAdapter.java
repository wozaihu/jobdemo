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
        //设置每行文字数量
//        StringBuilder text = new StringBuilder(array[position]);
//        int length = text.length();
//        int temp = 0;
//        if (text.length() > 4) {
//            for (int i = 0; i < length; i += 4) {
//                if (i != 0 && i % 4 == 0) {
//                    text.insert(i + temp, "\n");
//                    temp++;
//                }
//            }
//        }
//        holder.tv_demo_name.setText(text.toString());
        holder.tv_demo_name.setText(array[position]);
        holder.tv_demo_name.setOnClickListener((v)-> {
                v.getContext().startActivity(new Intent(v.getContext(), activity.get(position)));
                EventBus.getDefault().post("适配器跳转前");

        });

        if (position == array.length - 1) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.cv_root.getLayoutParams();
            layoutParams.setMargins(DensityUtil.INSTANCE.dip2px(20), DensityUtil.INSTANCE.dip2px(10), DensityUtil.INSTANCE.dip2px(20), DensityUtil.INSTANCE.dip2px(10));
            holder.cv_root.setLayoutParams(layoutParams);
        } else {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.cv_root.getLayoutParams();
            layoutParams.setMargins(DensityUtil.INSTANCE.dip2px(20), DensityUtil.INSTANCE.dip2px(10), DensityUtil.INSTANCE.dip2px(20), DensityUtil.INSTANCE.dip2px(0));
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
