package com.example.jobdemo.baidu_map;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

public class MyLocationListener extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {
        //mapView 销毁后不在处理新接收的位置
    }
}
