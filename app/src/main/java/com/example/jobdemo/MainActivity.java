package com.example.jobdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.MainOnDestroy;
import com.example.jobdemo.util.ChannelUtil;
import com.example.jobdemo.util.ClassUtils;
import com.umeng.commonsdk.UMConfigure;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_demo_instance)
    RecyclerView rvDemoInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("点击测试", "onCreate: ===MainActivity");
        //友盟正式初始化，冷启动时配置过key和通道了，这里不用在设置
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<Class> exclude = new ArrayList<>();
        exclude.add(this.getClass());
        List<Class> classList = ClassUtils.getActivitiesClass(this, getPackageName(), exclude);
        //类名按字母进行排序
        Collections.sort(classList, new Comparator<Class>() {
            @Override
            public int compare(Class o1, Class o2) {
                int i = o1.getSimpleName().compareTo(o2.getSimpleName());
                if (i > 0) {
                    return 1;
                }
                return -1;
            }
        });
        MainActivityAdapter adapter = new MainActivityAdapter(classList);
        rvDemoInstance.setLayoutManager(new LinearLayoutManager(this));
        rvDemoInstance.setAdapter(adapter);
        String channel = ChannelUtil.getChannel(getApplicationContext());
        Toast.makeText(getApplicationContext(), "当前渠道：" + channel, Toast.LENGTH_SHORT).show();
        checkLOCATIONPermission();
        Log.d(TAG, "编译版本是不是dubug: " + BuildConfig.DEBUG);
        Toast.makeText(this, "编译版本是不是dubug: " + BuildConfig.DEBUG, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new MainOnDestroy());
    }


    private void checkLOCATIONPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
    }
}
