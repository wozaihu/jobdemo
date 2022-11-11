package com.example.jobdemo.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobdemo.databinding.MapSelectLayoutBinding;

/**
 * @Author LYX
 * @Date 2022/10/11 15:59
 */
public class MapDialog extends BottomDialog {
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {

        MapSelectLayoutBinding binding = MapSelectLayoutBinding.inflate(inflater);
        return binding.getRoot();
    }
}
