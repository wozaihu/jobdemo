package com.example.jobdemo.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.jobdemo.util.LogUtil;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQL相关";
    //创建表的格式为： "create table 表名 (字段名 数据类型(是否为主键，主键如果为integer可设置为自增长),字段名 数据类型);"

    private String table = "create table dog(id INTEGER PRIMARY KEY AUTOINCREMENT,dogName TEXT,picture BLOB)";

    private String dogAddColumn = "ALTER TABLE dog ADD COLUMN age INTEGER";

    private String monkey = "create table monkey(id INTEGER PRIMARY KEY AUTOINCREMENT,monkeyName TEXT,age INTEGER)";

    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        LogUtil.showD(TAG, "调用了：MySQLiteHelper构造方法");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table);
        LogUtil.showD(TAG, "调用了：MySQLiteHelper------------onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtil.showD(TAG, "调用了：MySQLiteHelper------------onUpgrade");
        db.execSQL(monkey);
        db.execSQL(dogAddColumn);
    }
}
