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

import com.example.jobdemo.activity.ActivityPublicWebView;
import com.example.jobdemo.activity.Activity_List;
import com.example.jobdemo.activity.BiuldVariantDetails;
import com.example.jobdemo.activity.CameraDemo;
import com.example.jobdemo.activity.DataBinDingDemo;
import com.example.jobdemo.activity.DialogDemo;
import com.example.jobdemo.activity.DraftActivity;
import com.example.jobdemo.activity.DragLayout;
import com.example.jobdemo.activity.EditTextDemo;
import com.example.jobdemo.activity.InfoShow;
import com.example.jobdemo.activity.LayoutCreatePicture;
import com.example.jobdemo.activity.MaterialDesignDemo;
import com.example.jobdemo.activity.MeasureView;
import com.example.jobdemo.activity.NavigationDemo;
import com.example.jobdemo.activity.NavigationUIDemo;
import com.example.jobdemo.activity.NotificationDemo;
import com.example.jobdemo.activity.PopupWindowDemo;
import com.example.jobdemo.activity.RetrofitDemoActivity;
import com.example.jobdemo.activity.RoomDemo;
import com.example.jobdemo.activity.RxjavaTiming;
import com.example.jobdemo.activity.SQLiteDemo;
import com.example.jobdemo.activity.SearchViewDemo;
import com.example.jobdemo.activity.StudioDemo;
import com.example.jobdemo.activity.TestJetpackActivity;
import com.example.jobdemo.activity.ViewModelTest;
import com.example.jobdemo.activity.ViewPager2Use;
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
import com.example.jobdemo.base.BaseActivity;
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

public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_demo_instance)
    RecyclerView rvDemoInstance;
    private static String[] demoName = {
            "草稿"
            , "信息显示"
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
            , MyApplication.getAppContent().getString(R.string.web_view)
            , "list_view"
            , MyApplication.getAppContent().getString(R.string.studio_demo)
            , MyApplication.getAppContent().getString(R.string.layout_create_picture)
            , "EditTextDemo"
            , "测量view"
            , "retrofit简单使用"
            , "RXJava使用"
            , "jetPack使用"
            , "dataBinDing使用"
            , "SQLite使用"
            , "Room使用"
            , "ViewModel使用"
            , "Navigation使用"
            , "NavigationUI使用"
            , "MaterialDesign一些布局和控件使用"
            , "ViewPager2使用"
            , "Notification使用"
            ,"拖拽创建布局"
            ,"searchView"
    };

    private List<Class> activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("点击测试", "onCreate: ===MainActivity");
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
        activityList.add(DraftActivity.class);
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
        activityList.add(LayoutCreatePicture.class);
        activityList.add(EditTextDemo.class);
        activityList.add(MeasureView.class);
        activityList.add(RetrofitDemoActivity.class);
        activityList.add(RxjavaTiming.class);
        activityList.add(TestJetpackActivity.class);
        activityList.add(DataBinDingDemo.class);
        activityList.add(SQLiteDemo.class);
        activityList.add(RoomDemo.class);
        activityList.add(ViewModelTest.class);
        activityList.add(NavigationDemo.class);
        activityList.add(NavigationUIDemo.class);
        activityList.add(MaterialDesignDemo.class);
        activityList.add(ViewPager2Use.class);
        activityList.add(NotificationDemo.class);
        activityList.add(DragLayout.class);
        activityList.add(SearchViewDemo.class);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("点击测试", "onRestart: ===MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("点击测试", "onResume: ===MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("点击测试", "onPause: ===MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("点击测试", "onStop: ===MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new MainOnDestroy());
        Log.d(MyApplication.TAG, "onDestroy: MainActivity");
        Log.d("点击测试", "onDestroy: ===MainActivity");
    }


    private void checkLOCATIONPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 001);
            }
        }
    }
}
