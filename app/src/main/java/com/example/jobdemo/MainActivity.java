package com.example.jobdemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.ActivityBean;
import com.example.jobdemo.bean.MainOnDestroy;
import com.example.jobdemo.databinding.ActivityMainBinding;
import com.example.jobdemo.util.ClassUtils;
import com.umeng.commonsdk.UMConfigure;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.List;

/**
 * 主页
 *
 * @author Administrator
 */
public class MainActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //友盟正式初始化，冷启动时配置过key和通道了，这里不用在设置
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        ActivityMainBinding inflate = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
        List<ActivityBean> classList = ClassUtils.getActivitiesClass(getApplicationContext(), getPackageName());
        //类名按字母进行排序,中文就首字母，英文就所有的都算
        Collections.sort(classList, (o1, o2) -> {
            int i = o1.getAllInitial().compareTo(o2.getAllInitial());
            if (i > 0) {
                return 1;
            }
            return -1;
        });
        MainActivityAdapter adapter = new MainActivityAdapter(classList);
        inflate.rvDemoInstance.setLayoutManager(new LinearLayoutManager(this));
        inflate.rvDemoInstance.setAdapter(adapter);
        checkLocationPermission();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new MainOnDestroy());
    }


    private void checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
    }
}
