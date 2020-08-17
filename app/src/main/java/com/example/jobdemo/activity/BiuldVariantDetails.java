package com.example.jobdemo.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.WebApi;
import com.example.jobdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BiuldVariantDetails extends BaseActivity {
    @BindView(R.id.tv_build_details)
    TextView tvBuildDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biuldvariantdetails);
        ButterKnife.bind(this);
        tvBuildDetails.setText(WebApi.buildinfo);
    }
}
