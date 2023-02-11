package com.example.jobdemo.testLeak;

import android.content.Context;
import android.widget.Toast;

import com.example.jobdemo.R;

import java.lang.ref.WeakReference;

/**
 * @Author LYX
 * @Date 2021/12/27 9:50
 */
public class TestToast2 {
    public static void showToast(Context context) {
        WeakReference<Context> contextWeakReference = new WeakReference<>(context);
        Toast.makeText(contextWeakReference.get(), context.getResources().getString(R.string.shear_your_idea), Toast.LENGTH_SHORT).show();
        TestToast toast = new TestToast();
        TestToast.Person person = toast.new Person();
    }
}
