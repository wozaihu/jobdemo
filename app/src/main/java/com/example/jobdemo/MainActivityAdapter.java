package com.example.jobdemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jobdemo.Notification.SendNotification;

import java.util.List;

import androidx.annotation.NonNull;
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
                v.getContext().startActivity(new Intent(v.getContext(), SendNotification.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView tv_demo_name;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tv_demo_name = itemView.findViewById(R.id.tv_demo_name);
        }
    }
}
