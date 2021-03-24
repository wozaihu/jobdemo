package com.example.jobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.R;
import com.example.jobdemo.bean.PersonStateBean;

import java.util.List;

public class RoomDemoAdapter extends RecyclerView.Adapter<RoomDemoAdapter.RoomViewHolder> {
    private Context context;
    private List<PersonStateBean> list;

    public RoomDemoAdapter(Context context, List<PersonStateBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        PersonStateBean bean = list.get(position);
        holder.tv.setText(bean.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_DBInfo);
        }
    }
}
