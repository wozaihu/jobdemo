package com.example.jobdemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import com.example.jobdemo.BuildConfig;
import com.example.jobdemo.R;
import com.example.jobdemo.constants.Api;

import java.lang.ref.WeakReference;

/**
 * @author Administrator
 * @date 2020/9/17 16:55
 * @desc
 */
public class CheckInstallsPermissionUtil {

    public static boolean isInstalls(WeakReference<Context> weakReference) {
        final Context context = weakReference.get();
        try {
            if (Build.VERSION.SDK_INT >= 26 && !context.getPackageManager().canRequestPackageInstalls()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示");
                builder.setMessage(String.format(context.getString(R.string.update_tip), context.getString(R.string.app_name)));
                Uri uri=Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                builder.setPositiveButton("去开启", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,uri);
                    ((Activity) context).startActivityForResult(intent, Api.UPDATE_APK_REQUEST_CODE);
                    dialog.dismiss();
                });
                builder.create().show();
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.showD("update", "检查是否有安装未知应用权限异常" + e.getMessage());
        }
        return false;
    }
}
