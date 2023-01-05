package com.example.jobdemo.kotlin_code.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.jobdemo.R;
import com.example.jobdemo.databinding.ItemImageTextBinding;

import java.util.List;

/**
 * @Author LYX
 * @Date 2022/11/8 10:50
 */
public class TestJavaAdapter extends BaseAdapter {
    private List<String> list;

    public TestJavaAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemImageTextBinding binding;
        if (view == null) {
            binding = ItemImageTextBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            view = binding.getRoot();
            view.setTag(binding);
        } else {
            binding = (ItemImageTextBinding) view.getTag();
        }
        if (i % 2 == 0) {
            binding.img.setImageResource(R.mipmap.a2);
        } else {
            binding.img.setImageResource(R.mipmap.a3);
        }
        binding.tvName.setText(list.get(i));
        return view;
    }
}
