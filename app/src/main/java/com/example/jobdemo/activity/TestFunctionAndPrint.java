package com.example.jobdemo.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.TestfunctionandprintBinding;
import com.example.jobdemo.util.EnvironmentUtil;
import com.example.jobdemo.util.FileUtil;
import com.example.jobdemo.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author Administrator
 * @Date 2021/11/17 15:45
 */
public class TestFunctionAndPrint extends BaseActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestfunctionandprintBinding inflate = TestfunctionandprintBinding.inflate(LayoutInflater.from(this));
        setContentView(inflate.getRoot());
        LogUtil.showD(TAG, "目录1----------" + Environment.getExternalStorageDirectory().getPath());
        LogUtil.showD(TAG, "目录2----------" + getFilesDir().getPath());
        LogUtil.showD(TAG, "目录3----------" + getApplicationContext().getCacheDir().getPath());
        LogUtil.showD(TAG, "目录4----------" + getExternalFilesDir("").getPath());
        LogUtil.showD(TAG, "目录4----------" + getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath());
        LogUtil.showD(TAG, "目录5----------" + getExternalCacheDir().getPath());
        for (File file : getExternalFilesDirs("")) {
            LogUtil.showD(TAG, "所有ExternalFilesDirs----------" + file.getPath());
        }

        for (File file : getExternalCacheDirs()) {
            LogUtil.showD(TAG, "所有ExternalCacheDirs----------" + file.getPath());
        }

        //此处返回的是内部存储，files下的所有文件和目录
        for (String directory : fileList()) {
            LogUtil.showD(TAG, "所有fileList目录----------" + directory);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LogUtil.showD(TAG, "是否获得读写权限:" + checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));
        }
    }

    public void testFunctionAndPrintOnClick(View v) {
        LogUtil.showD(TAG, "点击了按钮");
        if (v.getId() == R.id.saveToRoot) {
            saveData(getExternalFilesDir("").getPath(), "外部存储.txt", "1");
        } else if (v.getId() == R.id.saveToApp) {
            saveData(getFilesDir().getPath(), "内部存储.txt", "123");
        } else if (v.getId() == R.id.clearCache) {
            FileUtil.deleteFileReserveSelf(getFilesDir());
            FileUtil.deleteFileReserveSelf(getExternalFilesDir(""));
        }
    }

    private void saveData(String path, String fileName, String content) {
        if (EnvironmentUtil.isExternalStorageWritable()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + fileName, true);
                fileOutputStream.write(content.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
