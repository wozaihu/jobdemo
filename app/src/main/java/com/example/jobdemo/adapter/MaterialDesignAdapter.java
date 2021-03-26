package com.example.jobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jobdemo.bean.PictureBean;
import com.example.jobdemo.databinding.MaterialdesignadapterBinding;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.MeasureUtils;

import java.util.List;

public class MaterialDesignAdapter extends RecyclerView.Adapter<MaterialDesignAdapter.MyViewHolder> {
    private List<PictureBean> list;
    private Context context;
    private MaterialdesignadapterBinding binding;

    public MaterialDesignAdapter(Context context, List<PictureBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MaterialdesignadapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int width = MeasureUtils.getScreenWidth();
        int reallyWidth = (width - 30) / 2;
        PictureBean bean = list.get(position);
        int ratio = bean.getWidth() / reallyWidth;
        int reallyHeight = bean.getHeight() / ratio;
        LogUtil.showD("宽=="+reallyWidth);
        LogUtil.showD("高=="+reallyHeight);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.itemImg.getLayoutParams();
        params.width = reallyWidth;
        params.height = reallyHeight;
        binding.itemImg.setLayoutParams(params);
        Glide.with(context).load(bean.getImgurl()).into(binding.itemImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(MaterialdesignadapterBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
