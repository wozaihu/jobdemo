package com.example.jobdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewholder> {
    public MainActivityAdapter(String[] array) {
        this.array = array;
    }

    private String[] array;

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
