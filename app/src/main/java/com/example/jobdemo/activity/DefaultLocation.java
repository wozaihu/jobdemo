package com.example.jobdemo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.jobdemo.bean.AddressCodeBean;
import com.example.jobdemo.bean.ApiGetAddressCodeBean;
import com.example.jobdemo.constants.Api;
import com.example.jobdemo.databinding.ActivityDefaultLocationBinding;
import com.example.jobdemo.dialog.MapDialog;
import com.example.jobdemo.util.CoordinateTransformUtil;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.ToastUtils;
import com.example.jobdemo.util.Utils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author Administrator
 */
public class DefaultLocation extends AppCompatActivity {
    private LocationClient locationClient;
    private com.example.jobdemo.databinding.ActivityDefaultLocationBinding binding;
    private double[] bdLocation = {114.11339948732575, 22.623232072124196};
    private double[] gdLocation = {114.106949, 22.616966};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDefaultLocationBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LogUtil.showD("定位的---没有权限");
            return;
        }

        initLocationOption();

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        // 可以指定优先GPS，再次网络定位
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            String providerGps = LocationManager.GPS_PROVIDER;
            Location location = locationManager.getLastKnownLocation(providerGps);
            LogUtil.showD("gps定位的location---" + location);
            if (location != null) {
                binding.tvGpsLocation.setText("gps定位经度" + location.getLongitude() + "---纬度" + location.getLatitude());
                double[] doubles = CoordinateTransformUtil.wgs84tobd09(location.getLongitude(), location.getLatitude());
                LogUtil.showArray("gps定位的location---", doubles);
                Address address = convertAddress(this, location.getLongitude(), location.getLatitude());
                LogUtil.showD("gps定位的原生解析---" + address.toString());
                StringBuilder mStringBuilder = new StringBuilder();
                mStringBuilder.append(address.getAdminArea()).append(", ").append(address.getLocality()).append(", ").append(address.getCountryName());
                LogUtil.showD("gps定位的城市---" + mStringBuilder.toString());
                binding.tvGpsAddressList.setText("gps定位的列表：" + address.toString());
                binding.tvGpsCity.setText("gps定位的城市：" + mStringBuilder.toString());
            } else {
                binding.tvGpsLocation.setText("gps定位经度为null");
            }
        }

        if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            String providerNetwork = LocationManager.NETWORK_PROVIDER;
            Location location = locationManager.getLastKnownLocation(providerNetwork);
            LogUtil.showD("network定位的location---" + location);
            if (location != null) {
                binding.tvNetworkLocation.setText("network定位经度" + location.getLongitude() + "---纬度" + location.getLatitude());
                double[] doubles = CoordinateTransformUtil.wgs84tobd09(location.getLongitude(), location.getLatitude());
                LogUtil.showArray("network定位的location---", doubles);
                Address address = convertAddress(this, location.getLongitude(), location.getLatitude());
                LogUtil.showD("network定位附近地址数---" + address.getMaxAddressLineIndex());
                LogUtil.showD("network定位附近地址0---" + address.getAddressLine(0));
                LogUtil.showD("network定位的原生解析---" + address.toString());
                StringBuilder mStringBuilder = new StringBuilder();
                mStringBuilder.append(address.getAdminArea()).append(", ").append(address.getLocality()).append(", ").append(address.getCountryName());
                LogUtil.showD("network定位的城市---" + mStringBuilder.toString());
                binding.tvNetworkAddressList.setText("network定位的列表：" + address.toString());
                binding.tvNetworkCity.setText("network定位的城市：" + mStringBuilder.toString());
            } else {
                binding.tvNetworkLocation.setText("network定位经度null");
            }
        }

        LogUtil.showD("百度地图是否安装：" + Utils.checkAppsIsExist(this, Api.BAIDU_MAP));
        LogUtil.showD("高德地图是否安装：" + Utils.checkAppsIsExist(this, Api.GAODE_MAP));
        LogUtil.showD("腾讯地图是否安装：" + Utils.checkAppsIsExist(this, Api.TENCENT_MAP));

        //通过经纬度导航
        binding.btnStartBaiduMap.setOnClickListener(v -> {
            Intent i1 = new Intent();
            i1.setData((Uri.parse("baidumap://map/marker?location=" + bdLocation[1] + "," + bdLocation[0] + "&title=尚水天成&content=&traffic=on&src=andr.baidu.openAPIdemo")));
            startActivity(i1);
        });

        binding.btnStartGaodeMap.setOnClickListener(v -> {
            try {
                Intent i1 = Intent.getIntentOld("androidamap://viewMap?sourceApplication=com.example.jobdemo&poiname=尚水天成&lat=" + gdLocation[1] + "&lon=" + gdLocation[0] + "&dev=0");
                startActivity(i1);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        });

        binding.btnStartTencentMap.setOnClickListener(v -> {
            Intent i3 = new Intent();
            i3.setData(Uri.parse("qqmap://map/marker?marker=coord:" + gdLocation[1] + "," + gdLocation[0] + ";title:尚水天成;addr:&referer=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77"));
            startActivity(i3);
        });


        //调起地图，并搜索地址
        binding.btnByAddressStartBaiduMap.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(binding.editAddress.getText().toString())) {
                Intent i1 = new Intent();
                i1.setData(Uri.parse("baidumap://map/geocoder?src=andr.baidu.openAPIdemo&address=" + binding.editAddress.getText().toString()));
                startActivity(i1);
            } else {
                ToastUtils.shortToast(this, "请输入地址名");
            }
        });

        binding.btnByAddressStartGaodeMap.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(binding.editAddress.getText().toString())) {
                try {
                    Intent i1 = Intent.getIntentOld("androidamap://poi?sourceApplication=softname&keywords=" + binding.editAddress.getText().toString() + "&lat1=&amp;lon1=&lat2=&lon2=&dev=0");
                    startActivity(i1);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            } else {
                ToastUtils.shortToast(this, "请输入地址名");
            }
        });

        binding.btnByAddressStartTencentMap.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(binding.editAddress.getText().toString())) {
                Intent i3 = new Intent();
                i3.setData(Uri.parse("qqmap://map/search?keyword=" + binding.editAddress.getText().toString() + "& center=&radius=&referer=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77"));
                startActivity(i3);
            } else {
                ToastUtils.shortToast(this, "请输入地址名");
            }
        });

        binding.btnSelectMap.setOnClickListener(v -> {
            new MapDialog().show(getSupportFragmentManager(), "");
        });

        binding.btnGetAddressCode.setOnClickListener(v -> {
            String url = "http://api.tianditu.gov.cn/geocoder";
            String address = "{\"keyWord\":\""
                    + binding.editAddress.getText().toString() + "\"}";
            String key = "36be4904fa3cb6d614098610d43c8b1c";
            Log.d("定位", url + address + key);
            getByOkGo(url, address, key);
        });

        binding.btnAipGetAddressCode.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(binding.editAddress.getText().toString())) {
//                apiGetAddressCode();
                apiGetAddressCode2();
            }
        });

    }

    private void apiGetAddressCode() {
        OkGo.<String>post("https://jmgeocode.api.bdymkt.com/geocode/geo/query?address=" + binding.editAddress.getText().toString())
                .tag(this)
                .headers("X-Bce-Signature", "AppCode/35ce404b4a5e4219bccd356d6b0f964a")
                .headers("Content-Type", "application/json; charset=utf-8")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("定位", "onSuccess: " + response.body());

                        ApiGetAddressCodeBean codeBean = new Gson().fromJson(response.body(), ApiGetAddressCodeBean.class);
                        try {
                            String[] lonAndLat = codeBean.getData().getGeocodes().get(0).getLocation().split(",");
                            Double[] doubleArray = new Double[2];
                            for (int i = 0; i < 2; i++) {
                                doubleArray[i] = new Double(lonAndLat[i]);
                            }

                            Log.d("定位", "原始gcj20坐标: " + Arrays.toString(doubleArray));

                            double[] bdArray = CoordinateTransformUtil.gcj02tobd09(doubleArray[0], doubleArray[1]);
                            Log.d("定位", "火星坐标转百度坐标: " + Arrays.toString(bdArray));


                            double[] gcj20Array = CoordinateTransformUtil.bd09togcj02(bdArray[0], bdArray[1]);
                            Log.d("定位", "百度坐标---火星坐标: " + Arrays.toString(gcj20Array));

                        } catch (Exception e) {
                            ToastUtils.shortToast(DefaultLocation.this, "没有获得到经纬度");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.d("定位", "onError: " + response.body());
                    }
                });
    }

    private void apiGetAddressCode2() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://jmgeocode.api.bdymkt.com/geocode/geo/query?address=罗湖区水贝ibc")
                .method("POST", body)
                .addHeader("X-Bce-Signature", "AppCode/35ce404b4a5e4219bccd356d6b0f964a")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();

        new Thread(() -> {
            okhttp3.Response response = null;
            try {
                response = client.newCall(request).execute();
                String data = response.body().string();
                Log.d("定位", "apiGetAddressCode2: " + data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    /**
     * get请求获取数据
     *
     * @param url
     */
    private void getByOkGo(String url, String address, String key) {
        OkGo.<String>get(url)
                .tag(this)
                .params("ds", address)
                .params("tk", key)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String bodyData = response.body();
                        if (!DefaultLocation.this.isFinishing()) {
                            AddressCodeBean bean = new Gson().fromJson(bodyData, AddressCodeBean.class);
                            double[] tobd09 = CoordinateTransformUtil.wgs84tobd09(bean.getLocation().getLon(), bean.getLocation().getLat());
                            LogUtil.showArray("定位", tobd09);
                            binding.tvTdt84.setText(Arrays.toString(tobd09));
                            binding.tvTdtMapCode.setText(bodyData);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.d("定位", "onError: " + response.body());
                    }
                });
    }


    private static Address convertAddress(Context context, double longitude, double latitude) {
        Geocoder mGeocoder = new Geocoder(context, Locale.CHINA);
        try {
            List<Address> mAddresses = mGeocoder.getFromLocation(latitude, longitude, 1);
            if (!mAddresses.isEmpty()) {
                Address address = mAddresses.get(0);
                return address;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 初始化定位参数配置
     */

    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        locationClient = new LocationClient(getApplicationContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("bd09ll");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(true);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
    }


    /**
     * 实现定位回调
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            //获取纬度信息
            double latitude = location.getLatitude();
            //获取经度信息
            double longitude = location.getLongitude();
            //获取定位精度，默认值为0.0f
            float radius = location.getRadius();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String coorType = location.getCoorType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            int errorCode = location.getLocType();


            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
/*
baiduMap.setMyLocationData(locData);
            baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            setContentView(mapView);
            */
            Log.d("定位的", "百度纬度信息== " + latitude + "-------百度经度信息==" + longitude);
            binding.tvBaiduLocation.setText("百度定位经度" + longitude + "---纬度" + latitude);
            String addr = location.getAddrStr();
            binding.tvBaiduAddress.setText("百度定位地址：" + addr);
            //设定bai中心点坐标
            LatLng cenpt = new LatLng(latitude, longitude);
//定义地图状态
            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(cenpt)
                    .zoom(18)
                    .build();
//定义MapStatusUpdate对象，以便描述地图状态将du要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
//改变地图状态
//            baiduMap.setMapStatus(mMapStatusUpdate);
        }
    }

    @Override
    protected void onDestroy() {
        if (locationClient != null) {
            locationClient.stop();
        }
        super.onDestroy();
    }
}