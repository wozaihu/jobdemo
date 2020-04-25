package com.example.jobdemo;

import android.os.Bundle;
import android.util.Log;

import com.example.jobdemo.Animation.GroupAnimation;
import com.example.jobdemo.Animation.MyAlpha;
import com.example.jobdemo.Animation.MyRotate;
import com.example.jobdemo.Animation.MyScaleAnimation;
import com.example.jobdemo.Animation.MyTranslateAnimation;
import com.example.jobdemo.Animation.MyValueAnimation;
import com.example.jobdemo.Animation.VeiwGroupAnimation;
import com.example.jobdemo.Notification.SendNotification;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.rv_demo_instance)
    RecyclerView rvDemoInstance;
    private static String[] demoName = {"Notification加按钮"
            , "TranslateAnimation"
            , "ScaleAnimation"
            , "RotateAnimation"
            , "AlphaAnimation"
            ,"ViewGroupAnimation"
            ,"GroupAnimation"
            ,"ValueAnimation"
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
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setStarteEndAnimation(String s) {
        Log.d(TAG, "setStarteEndAnimation:----- " + s);
//        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
//        overridePendingTransition(R.anim.out_right, R.anim.in_left);
    }

    private void getListActivity() {
        activityList = new ArrayList<>();
        activityList.add(SendNotification.class);
        activityList.add(MyTranslateAnimation.class);
        activityList.add(MyScaleAnimation.class);
        activityList.add(MyRotate.class);
        activityList.add(MyAlpha.class);
        activityList.add(VeiwGroupAnimation.class);
        activityList.add(GroupAnimation.class);
        activityList.add(MyValueAnimation.class);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
