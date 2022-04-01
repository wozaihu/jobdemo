package com.example.jobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.R;
import com.example.jobdemo.bean.UserDbTest;

import java.util.List;

/**
 * @Author LYX
 * @Date 2022/3/12 9:38
 */
public class GreenUseAdapter extends RecyclerView.Adapter<GreenUseAdapter.ViewHolder> {
    private Context context;
    private List<UserDbTest> list;
    private ItemClickCell itemClickCell;

    public void setItemClickCell(ItemClickCell itemClickCell) {
        this.itemClickCell = itemClickCell;
    }


    public interface ItemClickCell {

        /**
         * Item点击回调
         *
         * @param position 位置
         * @param id       id
         */
        void itemClick(int position, Long id);
    }

    public GreenUseAdapter(Context context, List<UserDbTest> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_green_use, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRow.setText(String.valueOf(position + 1));
        holder.tvId.setText(String.valueOf(list.get(position).getUserId()));
        holder.tvName.setText(list.get(position).getUserName());
        holder.tvAge.setText(String.valueOf(list.get(position).getAge()));
        holder.itemView.setOnClickListener(v -> {
            itemClickCell.itemClick(position, list.get(position).getUserId());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvName;
        TextView tvAge;
        TextView tvRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvRow = itemView.findViewById(R.id.tv_row);
        }
    }
}
