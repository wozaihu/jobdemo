package com.example.jobdemo.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.R;
import com.example.jobdemo.util.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Author LYX
 * @Date 2022/4/3 10:38
 */
public class RvTextViewAdapter extends RecyclerView.Adapter<RvTextViewAdapter.ViewHolder> {
    private Context context;
    private List<String> list;

    public RvTextViewAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
        holder.itemView.setOnClickListener(v -> ToastUtils.shortToast(context,
                String.format(context.getString(R.string.click_object), list.get(position))));
    }

    @Override
    public int getItemCount() {
        return list.size();
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
