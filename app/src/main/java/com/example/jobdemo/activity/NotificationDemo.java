package com.example.jobdemo.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.NotificationdemoBinding;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.ToastUtils;

import java.util.Random;

/**
 * @Author Administrator
 * @Date 2021/4/7 14:06
 */
public class NotificationDemo extends BaseActivity {

    private NotificationdemoBinding binding;
    private StringBuilder stringBuilder;
    private Random random;
    private CountDownTimer timer;
    /**
     * 毫秒
     */
    private final int countDown = 10 * 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NotificationdemoBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        downTime();

        binding.tvDownTime.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.etPhone.getText().toString())) {
                ToastUtils.shortToast(this, "请先输入手机号");
            } else if (!TextUtils.isEmpty(binding.etPhone.getText().toString()) && isPhoneNumber() && binding.tvDownTime.getText().toString().equals("获取验证码")
                    || binding.tvDownTime.getText().toString().equals("重新获取验证码")) {
                sendVerification(binding.etPhone.getText().toString()); //发送验证码
                binding.tvDownTime.setSolid(ContextCompat.getColor(this, R.color.dove_gray));
                timer.start();
            }
        });
        binding.tvLogin.setOnClickListener(v -> DraftActivity.start(this));

        binding.btnCancel.setOnClickListener(v -> {
            binding.tvDownTime.setText(getString(R.string.getVerifyCode));
            binding.tvDownTime.setSolid(ContextCompat.getColor(this, R.color.green));
            timer.cancel();
        });

        binding.btnRestart.setOnClickListener(v->{
            binding.tvDownTime.setSolid(ContextCompat.getColor(this, R.color.dove_gray));
            timer.start();
        });
    }


    //发送验证码
    @SuppressLint({"CommitPrefEdits", "SetTextI18n"})
    private void sendVerification(String phoneNumber) {
        if (stringBuilder == null) {
            stringBuilder = new StringBuilder();
            random = new Random();
        } else {
            stringBuilder.setLength(0);
        }

        //生成6位数0-9的验证码
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        Log.d(TAG, "生成的验证码是: " + stringBuilder.toString());
        String channelId = "verification";
        String currentVerificationNumber = stringBuilder.toString();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //适配8.0及以上需要设置NotificationChannel，且NotificationChannel的id与Notification的channelId必须相同
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "模拟验证码", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("登录验证码是" + stringBuilder.toString())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(PendingIntent.getActivity(this, 99
                        , new Intent(this, InfoShow.class).putExtra("code", stringBuilder.toString())
                        , PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE))
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 1000, 1000, 1000}) //设置振动， 需要添加权限  <uses-permission android:name="android.permission.VIBRATE"/>
                .setLights(Color.GREEN, 1000, 1000)//设置前置LED灯进行闪烁， 第一个为颜色值  第二个为亮的时长  第三个为暗的时长
                .setDefaults(NotificationCompat.DEFAULT_ALL)  //使用默认效果， 会根据手机当前环境播放铃声， 是否振动
//                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        notificationManager.notify(1, notification);
        binding.tvVerificationNumber.setText("验证码：" + stringBuilder.toString() + "已发送至手机" + phoneNumber);
    }

    //开启倒计时
    private void downTime() {
        timer = new CountDownTimer(countDown, 1000) { //第一个参数是总倒计时，第二个参数是每次跳多少时间
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                //每次都调用，不要判断页面是否显示
                binding.tvDownTime.setText(l / 1000 + 1 + "秒后可重发");
                LogUtil.showD(TAG, "onTick: " + l);
            }

            @Override
            public void onFinish() {
                //倒计时结束调用
                binding.tvDownTime.setText("重新获取验证码");
                binding.tvDownTime.setSolid(ContextCompat.getColor(NotificationDemo.this, R.color.green));
                LogUtil.showD(TAG, "onFinish: ");
            }
        };
    }


    private boolean isPhoneNumber() {
        //登录时先判断有没有输入手机号，再判断有没有11位，如果第一位不为1，提示用户输入正确的手机号（要更完整的判断请根据手机号规则写正则表达式）
        String phoneNumber = binding.etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phoneNumber.length() < 11 || !String.valueOf(phoneNumber.charAt(0)).equals("1")) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
