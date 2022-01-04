package com.example.jobdemo.testLeak;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * @Author LYX
 * @Date 2021/12/27 9:50
 */
public class TestToast3 {
    public static void showToast(WeakReference<Context> context) {
        Toast.makeText(context.get(), "弱引用且，直接使用文字", Toast.LENGTH_SHORT).show();
    }
}
