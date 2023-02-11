package com.example.jobdemo.testLeak;

import android.content.Context;
import android.widget.Toast;

import com.example.jobdemo.R;
import com.example.jobdemo.util.ToastUtils;

/**
 * @Author LYX
 * @Date 2021/12/27 9:50
 */
public class TestToast {
    public String description = "一个测试类而已";

    public static void showToast(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.shear_your_idea), Toast.LENGTH_SHORT).show();
    }

    public void accessPerson(Context context) {
        Person person = new Person();
        ToastUtils.shortToast(context, person.name);
    }

    class Person {
        public String name = "王维";
    }
}
