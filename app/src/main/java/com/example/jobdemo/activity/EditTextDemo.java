package com.example.jobdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.kotlin_code.bean.KTPerson;
import com.example.jobdemo.util.PriceTextWatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditTextDemo extends BaseActivity {
    private static final String TAG = "打印数据";
    @BindView(R.id.edt_price)
    EditText edtPrice;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittextdemo);
        ButterKnife.bind(this);
    }
}
