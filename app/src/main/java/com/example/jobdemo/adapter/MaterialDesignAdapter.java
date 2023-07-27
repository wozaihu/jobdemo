package com.example.jobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jobdemo.R;
import com.example.jobdemo.databinding.MaterialdesignadapterBinding;

import java.util.List;

public class MaterialDesignAdapter extends RecyclerView.Adapter<MaterialDesignAdapter.MyViewHolder> {
    private List<String> list;
    private Context context;

    public MaterialDesignAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialdesignadapterBinding binding = MaterialdesignadapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        int width = MeasureUtils.getScreenWidth(context);
//        int reallyWidth = (width - 30) / 2;
//        PictureBean bean = list.get(position);
//        int ratio = bean.getWidth() / reallyWidth;
//        int reallyHeight = bean.getHeight() / ratio;
//        LogUtil.showD("宽=="+reallyWidth);
//        LogUtil.showD("高=="+reallyHeight);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.itemImg.getLayoutParams();
//        params.width = reallyWidth;
//        params.height = reallyHeight;
//        binding.itemImg.setLayoutParams(params);
        Glide.with(context).load(list.get(position)).into(holder.binding.itemImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialdesignadapterBinding binding;

        public MyViewHolder(MaterialdesignadapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
