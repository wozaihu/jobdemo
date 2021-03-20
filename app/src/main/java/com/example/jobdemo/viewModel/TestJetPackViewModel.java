package com.example.jobdemo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class TestJetPackViewModel extends AndroidViewModel {
    private String a;
    private MutableLiveData<String> b;

    public MutableLiveData<String> getB() {
        if (b == null) {
            b = new MutableLiveData<>();
            b.setValue("0");
        }
        return b;
    }


    public TestJetPackViewModel(@NonNull Application application) {
        super(application);
    }
}
