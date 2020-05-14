package com.example.jobdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
import com.example.jobdemo.exercise.RecyclerView_Demo;
import com.example.jobdemo.notification.SendNotification;
import com.example.jobdemo.view_demo.DemoExpandableListView;

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
            , "ViewGroupAnimation"
            , "GroupAnimation"
            , "ValueAnimation"
            , "ObjectAnimator"
            , "Interpolator"
            , "FrameAnimation"
            , "ExpandableListView"
            , "recyclerView瀑布流"
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
        String channel = ChannelUtil.getChannel(getApplicationContext());
        Toast.makeText(getApplicationContext(), "当前渠道：" + channel, Toast.LENGTH_SHORT).show();
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
        activityList.add(MyObjectAnimator.class);
        activityList.add(MyInterpolator.class);
        activityList.add(MyFrameAnimatoin.class);
        activityList.add(DemoExpandableListView.class);
        activityList.add(RecyclerView_Demo.class);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
