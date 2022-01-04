package com.example.jobdemo.testLeak;

import android.content.Context;
import android.widget.Toast;

import com.example.jobdemo.R;

/**
 * @Author LYX
 * @Date 2021/12/27 9:50
 */
public class TestToast {
    public static void showToast(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.shear_your_idea), Toast.LENGTH_SHORT).show();
    }
}
