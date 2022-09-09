package com.example.jobdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobdemo.databinding.ActivityLaunchBinding;

/**
 * @author Administrator
 */
public class LaunchActivity extends AppCompatActivity {

    private CountDownTimer downTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLaunchBinding binding = ActivityLaunchBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        downTimer = new CountDownTimer(3000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                binding.tvSkip.setText(getString(R.string.skipAd) + (millisUntilFinished / 1000 + 1));
            }

            @Override
            public void onFinish() {
                MainActivity.start(LaunchActivity.this);
                finish();
            }
        };
        binding.tvSkip.setOnClickListener(v -> {
            MainActivity.start(LaunchActivity.this);
            downTimer.cancel();
            finish();
        });
        downTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downTimer != null) {
            downTimer.cancel();
        }
    }
}