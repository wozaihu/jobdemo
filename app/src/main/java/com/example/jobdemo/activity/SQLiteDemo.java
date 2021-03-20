package com.example.jobdemo.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.base.MySQLiteHelper;
import com.example.jobdemo.bean.AnimalBean;
import com.example.jobdemo.databinding.SqlitedemoBinding;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SQLiteDemo extends BaseActivity {
    private static final String TAG = "SQL相关";
    private static final String[] permissionsGroup = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION};
    private SqlitedemoBinding sqlitedemoBinding;
    private AnimalBean animalBean;
    private MySQLiteHelper mySQLiteHelper;
    private String path;
    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlitedemoBinding = DataBindingUtil.setContentView(this, R.layout.sqlitedemo);
        mySQLiteHelper = new MySQLiteHelper(this, "animal", null, 1);
        animalBean = new AnimalBean();
        sqlitedemoBinding.setAnimal(animalBean);
    }


    public void SQLiteOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_selectPicture:
                goSelectPicture();
                break;
            case R.id.btn_saveDbPicture:
                if (TextUtils.isEmpty(sqlitedemoBinding.etDogName.getText().toString()) || TextUtils.isEmpty(path)) {
                    ToastUtils.shortToast("请输入狗的名字和选择图片");
                    return;
                }
                saveDogDB();
                break;
            case R.id.btn_query:
                SQLiteDatabase db = null;
                db = mySQLiteHelper.getReadableDatabase();
                Cursor c = db.query("dog", new String[]{"id", "dogName", "picture"}, "dogName=?", new String[]{"小花"}, null, null, null);
                while (c.moveToNext()) {
                    String id = c.getString(c.getColumnIndex("id"));
                    String name = c.getString(c.getColumnIndex("dogName"));
                    byte[] in = c.getBlob(c.getColumnIndex("picture"));
                    Bitmap bit = BitmapFactory.decodeByteArray(in, 0, in.length);
                    sqlitedemoBinding.imgQueryDog.setImageBitmap(bit);
                }
                break;
            case R.id.btn_setVersion:
                SQLiteDatabase database = mySQLiteHelper.getReadableDatabase();
                mySQLiteHelper.onUpgrade(database, database.getVersion(), database.getVersion() + 1);
                LogUtil.showD(TAG, "点击了设置了版本为");
                database.close();
                break;
        }
    }

    /**
     * 保存到数据库
     */
    private void saveDogDB() {
        SQLiteDatabase database = mySQLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dogName", sqlitedemoBinding.etDogName.getText().toString());
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        ContentResolver cr = getContentResolver();
        try {
            Bitmap bit = BitmapFactory.decodeStream(cr.openInputStream(uri));
            bit.compress(Bitmap.CompressFormat.PNG, 100, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        values.put("picture", os.toByteArray());
        database.insert("dog", null, values);
        database.close();
        Log.d(TAG, "saveDogDB: 保存完成");
    }

    /**
     * 去相册选择图片
     */
    private void goSelectPicture() {
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(permissionsGroup).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Intent intent = new Intent(Intent.ACTION_PICK, null);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            "image/*");
                    startActivityForResult(intent, 9);
                } else {
                    ToastUtils.shortToast("请给予读取SD卡权限");
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9 && resultCode == RESULT_OK) {
            path = data.getData().toString();
            uri = data.getData();
            animalBean.setSavePicturePath(this.path); //直接toString才能用，不能用getPath
            LogUtil.showD(TAG, "选择的图片地址===" + this.path);
        }
    }
}
