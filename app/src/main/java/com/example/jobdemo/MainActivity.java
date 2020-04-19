package com.example.jobdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_demo_instance)
    RecyclerView rvDemoInstance;
    private static String[] demoName = {"Notification加按钮"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainActivityAdapter adapter = new MainActivityAdapter(demoName);
        rvDemoInstance.setLayoutManager(new LinearLayoutManager(this));
        rvDemoInstance.setAdapter(adapter);
    }
}
