package com.example.jobdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.jobdemo.databinding.ActivityLaunchBinding;
import com.example.jobdemo.service.MyIntentService;

/**
 * @author Administrator
 */
@SuppressLint("CustomSplashScreen")
public class CustomLaunchActivity extends AppCompatActivity {

    private CountDownTimer downTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLaunchBinding binding = ActivityLaunchBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        checkLocationPermission();
        MyIntentService.startActionFoo(this, "hello", "hi");
        downTimer = new CountDownTimer(1000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                binding.tvSkip.setText(getString(R.string.skipAd) + (millisUntilFinished / 1000 + 1));
            }

            @Override
            public void onFinish() {
                MainActivity.start(CustomLaunchActivity.this);
                finish();
            }
        };
        binding.tvSkip.setOnClickListener(v -> {
            MainActivity.start(CustomLaunchActivity.this);
            downTimer.cancel();
            finish();
        });
        downTimer.start();
    }


    private void checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downTimer != null) {
            downTimer.cancel();
        }
    }
}