package com.example.jobdemo.baidu_map;

import android.os.Bundle;

import com.baidu.mapapi.map.MapView;
import com.example.jobdemo.base.BaseActivity;

import androidx.annotation.Nullable;

public class BaiduMapDemo extends BaseActivity {

    private MapView mapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = new MapView(this);
        setContentView(mapView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
