package com.example.jobdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.util.PriceTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTextDemo extends BaseActivity {
    private static final String TAG = "EditTextDemo";
    @BindView(R.id.edt_price)
    EditText edtPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittextdemo);
        ButterKnife.bind(this);
        edtPrice.addTextChangedListener(new PriceTextWatcher(edtPrice));
        double price = Double.parseDouble("0.");
        Log.d(TAG, "price:==" + price);
        "".contains("");
    }
}
