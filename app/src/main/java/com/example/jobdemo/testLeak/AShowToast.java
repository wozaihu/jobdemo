package com.example.jobdemo.testLeak;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

/**
 * @Author LYX
 * @Date 2021/12/27 9:42
 */
public class AShowToast extends BaseActivity implements Handler.Callback {
    public static final int SUCCESS = 101;
    public static final int FAILED = 101;
    private Handler mHandler;
    private Button button;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ashowtoast);
        mHandler = new Handler(this);
        button = findViewById(R.id.btnShowToast);
        button.setOnClickListener(v -> {
            mHandler.sendEmptyMessageDelayed(SUCCESS, 1000 * 10);
            finish();
        });
    }

    /**
     * @param msg 消息 直接实现，不是匿名内部类
     * @return
     */
    @Override
    public boolean handleMessage(@NonNull Message msg) {
        //关闭后调用未崩溃
        if (msg.what == SUCCESS) {
            Log.d("AShowToast打印看看", "AShowToast---------handleMessage收到了消息");
            //这里使用view不会导致内存泄漏
            button.setText("要崩溃吗");
            //toast使用this就会持有外部引用，会导致泄漏
//            if (this.getClass().getSimpleName().equals("")) {
//                Toast.makeText(this, "类名等于空", Toast.LENGTH_SHORT).show();
//            }
            //getApplicationContext也是基于this获得的，等于还是使用了this
            Toast.makeText(getApplicationContext(), "成功收到消息", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭界面时要清除handler的回调和消息，不然还是会回调，导致内存泄漏
        mHandler.removeCallbacksAndMessages(null);
        Log.d("AShowToast打印看看", "AShowToast---------onDestroy");
    }
}
