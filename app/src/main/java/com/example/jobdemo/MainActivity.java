package com.example.jobdemo;

import android.os.Bundle;

import com.example.jobdemo.Notification.SendNotification;
import com.example.testcreatearr.ArrUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_demo_instance)
    RecyclerView rvDemoInstance;
    private static String[] demoName = {"Notification加按钮"
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
        int randomNumber = ArrUtils.getInstance().getRandomNumber();
        ArrUtils.getInstance().showToast(this, "调用了aar的方法得到数字" + randomNumber);
    }

    private void getListActivity() {
        activityList = new ArrayList<>();
        activityList.add(SendNotification.class);
    }
}
