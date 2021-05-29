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
        edtPrice.addTextChangedListener(new PriceTextWatcher(edtPrice));
        double price = Double.parseDouble("0.");
        Log.d(TAG, "price:==" + price);
        List<String> list = new ArrayList();
        list.add("1");
        list.add(null);
        list.add("2");
        list.add("3");
        list.add(null);
        list.add("4");

        for (int i = 0; i < list.size(); i++) {
            Log.d(TAG, i + "=原来=" + list.get(i));
        }
        list.removeAll(Collections.singleton(null));
        for (int i = 0; i < list.size(); i++) {
            Log.d(TAG, i + "==" + list.get(i));
        }
        KTPerson.isJavaSupport();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Log.d(TAG, "isClickable: " + checkBox.isClickable());
        Log.d(TAG, "isChecked: " + checkBox.isChecked());
        Log.d(TAG, "isSelected: " + checkBox.isSelected());
        int value = Integer.parseInt(edtPrice.getText().toString().trim());
        if (value > 100) {
            throw new RuntimeException("输入的值超过100了");
        } else {
            value = 999;
            new Thread(() -> {
            }).start();
        }
    }
}
