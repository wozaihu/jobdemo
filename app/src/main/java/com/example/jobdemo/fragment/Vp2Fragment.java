package com.example.jobdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jobdemo.R;
import com.example.jobdemo.adapter.RvTextViewAdapter;
import com.example.jobdemo.databinding.Vp2FragmentBinding;
import com.example.jobdemo.util.IntToChineseNumUtil;
import com.example.jobdemo.util.RandomNameUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LYX
 * @Date 2022/4/3 10:32
 */
public class Vp2Fragment extends Fragment {
    public static final String DATATYPE = "dataType";
    public static final int DATA_SIZE = 100;
    public final List<String> LIST = new ArrayList<>();

    public static Vp2Fragment newInstance(int dataType) {
        Bundle args = new Bundle();
        args.putInt(DATATYPE, dataType);
        Vp2Fragment fragment = new Vp2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Vp2FragmentBinding binding = Vp2FragmentBinding.inflate(inflater, container, false);
        binding.refreshLayout.setEnableRefresh(false);
        binding.refreshLayout.setEnableLoadMore(false);
        binding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        RvTextViewAdapter adapter = new RvTextViewAdapter(getActivity());
        if (getArguments() != null) {
            if (0 == getArguments().getInt(DATATYPE)) {
                for (int i = 0; i < DATA_SIZE; i++) {
                    LIST.add(String.format(getString(R.string.whichRow), i + 1));
                }
            } else if (1 == getArguments().getInt(DATATYPE)) {
                for (int i = 0; i < DATA_SIZE; i++) {
                    LIST.add(RandomNameUtil.randomName(false, 4));
                }
            } else if (2 == getArguments().getInt(DATATYPE)) {
                for (int i = 0; i < DATA_SIZE; i++) {
                    LIST.add(RandomNameUtil.randomName(true, 2));
                }
            } else if (3 == getArguments().getInt(DATATYPE)) {
                for (int i = 0; i < DATA_SIZE; i++) {
                    LIST.add(String.format(getString(R.string.whichRowStr), IntToChineseNumUtil.int2chineseNum(i + 1)));
                }
            }
        }
        adapter.setList(LIST);
        binding.rv.setAdapter(adapter);
        return binding.getRoot();
    }
}
