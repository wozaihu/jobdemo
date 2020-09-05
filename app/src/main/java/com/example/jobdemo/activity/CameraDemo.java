package com.example.jobdemo.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.jobdemo.MyApplication;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.MainOnDestroy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraDemo extends BaseActivity {
    private static final String TAG = "CameraDemo";
    @BindView(R.id.btn_start_camera)
    Button btnStartCamera;
    @BindView(R.id.btn_2)
    Button btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MainOnDestroy mainOnDestroy) {
        Log.d(MyApplication.TAG, "onMessageEvent: CameraDemo");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.d(MyApplication.TAG, "onDestroy: CameraDemo");
    }

    private void startCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "startCamera: 还没有权限");
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 001);
            } else {
                Log.d(TAG, "startCamera: 判断是有权限了");
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);   //拍照界面的隐式意图
                startActivityForResult(intent, 100);
            }
        } else {
            Log.d(TAG, "startCamera: 直接就跳转了");
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);   //拍照界面的隐式意图
            startActivityForResult(intent, 100);
        }
    }

    @OnClick({R.id.btn_start_camera, R.id.btn_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start_camera:
                startCamera();
                break;
            case R.id.btn_2:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 001) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: 没有获得权限");
                popAlterDialog();
            }

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: 还是没有权限啊");
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 001 && resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);   //拍照界面的隐式意图
            startActivityForResult(intent, 100);
            Log.d(TAG, "onActivityResult: ");
        }
    }


    public void popAlterDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("需要相机权限，请前往设置")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("中间的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(getXiaoMi(CameraDemo.this));
                    }
                })
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //前往应用详情界面
                        try {
//                            Uri packUri = Uri.parse("package:" + getPackageName());
////                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packUri);
////                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                            startActivity(intent);

//                            Intent i = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
//                            String pkg = "com.android.settings";
//                            String cls = "com.android.settings.applications.InstalledAppDetails";
//                            i.setComponent(new ComponentName(pkg, cls));
//                            i.setData(Uri.parse("package:" + getPackageName()));
//                            startActivity(i);
                            toSelfSetting(CameraDemo.this);
//                            start222();
                        } catch (Exception e) {
                            Toast.makeText(CameraDemo.this, "跳转失败", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                }).create().show();
    }


    public void start222() {
        ComponentName componetName = new ComponentName(
                "com.miui.securitycenter",//主包名
                "com.miui.permcenter.MainAcitivty");//小米安全中心的权限管理的Activity包名
        try {
            Intent intent = new Intent();
            intent.setComponent(componetName);
            startActivity(intent);
            //上面这种跳转是在本应用中跳转的，如果要开启新的页面，如下：
//                    Intent intent = new Intent();
//                    intent.setComponent(componetName);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "程序未安装", Toast.LENGTH_LONG).show();
        }
    }


    // 代码片段2：跳转到本应用的设置权限详情页面
    private static Intent getXiaoMi(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if (hasIntent(context, intent)) {
            return intent;
        }
        intent.setPackage("com.miui.securitycenter");
        if (hasIntent(context, intent)) {
            return intent;
        }
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        if (hasIntent(context, intent)) {
            return intent;
        }
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        return intent;
    }

    private static boolean hasIntent(Context context, Intent intent) {
        return !context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).isEmpty();
    }


    // 代码片段1：跳转到本应用的设置详情页面
    public static void toSelfSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(mIntent);
    }
}
