package com.example.jobdemo.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Administrator on 2020/11/4.
 * 如果输入的内容以点“.”开头，就改为“0.”
 */

public class PriceTextWatcher implements TextWatcher {
    private EditText editText;

    public PriceTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (s.length() > 0 && String.valueOf(s.charAt(0)).equals(".")) {
            editText.setText("0.");
            editText.setSelection(2);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
