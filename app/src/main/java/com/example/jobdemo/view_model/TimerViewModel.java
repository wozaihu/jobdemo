package com.example.jobdemo.view_model;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 缓存activity和fragment的数据，让view处理展示和交互就好，当不持有activity是会调用onCleared（可在此方法中释放资源）
 */
public class TimerViewModel extends ViewModel {
    private CountDownTimer timer;
    private MutableLiveData<Integer> currentSecond = new MutableLiveData<>(60);


    public MutableLiveData<Integer> getCurrentSecond() {
        return currentSecond;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (timer != null) {
            timer.cancel();
        }
    }

    public void startTiming() {
        if (timer == null) {
            timer = new CountDownTimer(currentSecond.getValue() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    currentSecond.setValue(currentSecond.getValue() - 1);
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
    }
}
