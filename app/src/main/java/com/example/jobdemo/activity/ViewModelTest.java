package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.ViewModelTestBinding;
import com.example.jobdemo.view_model.TimerViewModel;

/**
 * @author Administrator
 */
public class ViewModelTest extends BaseActivity implements View.OnClickListener {

    private ViewModelTestBinding binding;
    private MutableLiveData<Integer> liveData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ViewModelTestBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        TimerViewModel model = new ViewModelProvider(this).get(TimerViewModel.class);
        liveData = model.getCurrentSecond();
        liveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvShowTime.setText("倒计时：" + integer);
            }
        });
        model.startTiming();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_resetDownTimer) {
            liveData.setValue(50);
        }
    }
}
