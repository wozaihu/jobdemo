package com.example.jobdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.activity.ActivityPublicWebView;
import com.example.jobdemo.activity.Activity_List;
import com.example.jobdemo.activity.BiuldVariantDetails;
import com.example.jobdemo.activity.CameraDemo;
import com.example.jobdemo.activity.DialogDemo;
import com.example.jobdemo.activity.InfoShow;
import com.example.jobdemo.activity.PopupWindowDemo;
import com.example.jobdemo.activity.StudioDemo;
import com.example.jobdemo.activity.WidgetViewDemo;
import com.example.jobdemo.animation.GroupAnimation;
import com.example.jobdemo.animation.MyAlpha;
import com.example.jobdemo.animation.MyFrameAnimatoin;
import com.example.jobdemo.animation.MyInterpolator;
import com.example.jobdemo.animation.MyObjectAnimator;
import com.example.jobdemo.animation.MyRotate;
import com.example.jobdemo.animation.MyScaleAnimation;
import com.example.jobdemo.animation.MyTranslateAnimation;
import com.example.jobdemo.animation.MyValueAnimation;
import com.example.jobdemo.animation.VeiwGroupAnimation;
import com.example.jobdemo.baidu_map.BaiduMapDemo;
import com.example.jobdemo.bean.MainOnDestroy;
import com.example.jobdemo.exercise.RecyclerView_Demo;
import com.example.jobdemo.notification.SendNotification;
import com.example.jobdemo.util.ChannelUtil;
import com.example.jobdemo.view_demo.DemoExpandableListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.rv_demo_instance)
    RecyclerView rvDemoInstance;
    private static String[] demoName = {
            "信息显示"
            , "Notification加按钮"
            , "TranslateAnimation"
            , "ScaleAnimation"
            , "RotateAnimation"
            , "AlphaAnimation透明动画"
            , "ViewGroupAnimation"
            , "GroupAnimation"
            , "ValueAnimation"
            , "ObjectAnimator动画"
            , "Interpolator"
            , "FrameAnimation"
            , "ExpandableListView"
            , "recyclerView瀑布流"
            , "集成百度地图展示"
            , "调用相机"
            , "dialogDEMO"
            , "自定义viewDemo"
            , "PopupWindow使用"
            , "产品变种==" + BuildConfig.FLAVOR + ";----编译模式==" + BuildConfig.BUILD_TYPE
            , MyApplication.getApplication().getString(R.string.web_view)
            ,"list_view"
            ,MyApplication.getApplication().getString(R.string.studio_demo)
    };
    private List<Class> activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getListActivity();
        MainActivityAdapter adapter = new MainActivityAdapter(demoName, activityList);
        rvDemoInstance.setLayoutManager(new LinearLayoutManager(this));
        rvDemoInstance.setAdapter(adapter);
        String channel = ChannelUtil.getChannel(getApplicationContext());
        Toast.makeText(getApplicationContext(), "当前渠道：" + channel, Toast.LENGTH_SHORT).show();
        checkLOCATIONPermission();
        Log.d(TAG, "编译版本是不是dubug: " + BuildConfig.DEBUG);
        if (true) {
            return;
        }
        Toast.makeText(this, "编译版本是不是dubug: " + BuildConfig.DEBUG, Toast.LENGTH_LONG).show();
    }


    private void getListActivity() {
        activityList = new ArrayList<>();
        activityList.add(InfoShow.class);
        activityList.add(SendNotification.class);
        activityList.add(MyTranslateAnimation.class);
        activityList.add(MyScaleAnimation.class);
        activityList.add(MyRotate.class);
        activityList.add(MyAlpha.class);
        activityList.add(VeiwGroupAnimation.class);
        activityList.add(GroupAnimation.class);
        activityList.add(MyValueAnimation.class);
        activityList.add(MyObjectAnimator.class);
        activityList.add(MyInterpolator.class);
        activityList.add(MyFrameAnimatoin.class);
        activityList.add(DemoExpandableListView.class);
        activityList.add(RecyclerView_Demo.class);
        activityList.add(BaiduMapDemo.class);
        activityList.add(CameraDemo.class);
        activityList.add(DialogDemo.class);
        activityList.add(WidgetViewDemo.class);
        activityList.add(PopupWindowDemo.class);
        activityList.add(BiuldVariantDetails.class);
        activityList.add(ActivityPublicWebView.class);
        activityList.add(Activity_List.class);
        activityList.add(StudioDemo.class);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new MainOnDestroy());
        Log.d(MyApplication.TAG, "onDestroy: MainActivity");
    }


    private void checkLOCATIONPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 001);
            }
        }
    }
}
