package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeasureView extends BaseActivity {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_set_lv_adapter)
    Button btnSetLvAdapter;
    @BindView(R.id.btn_really_size)
    Button btnReallySize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_set_lv_adapter, R.id.btn_really_size})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_set_lv_adapter:
                break;
            case R.id.btn_really_size:
                break;
        }
    }
}
