package com.example.jobdemo.base;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.jobdemo.MyApplication;
import com.example.jobdemo.bean.PersonStateBean;
import com.example.jobdemo.dao.PersonStateDao;
import com.example.jobdemo.util.LogUtil;

/**
 * room基本用法：创建一个抽象类，继承RoomDatabase,类加注解@Database，entities为要创建的表（数组形式，多个用逗号分隔）
 * ，version代表版本，升级版本直接把数字改大就可以了（在构建room的地方添加addMigrations，版本升级或降级都会调用相应的Migrations）
 * 写个抽象方法，返回Dao，调用这个类就可以获得dao增删改查了
 */
@Database(entities = {PersonStateBean.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    private static volatile AppDataBase instance;
    private static final String DBName = "testRoom";

    public abstract PersonStateDao getPersonStateDao();

    public static AppDataBase getInstance() {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                if (instance == null) {
                    instance = createDB();
                }
            }
        }
        return instance;
    }

    private static AppDataBase createDB() {
        return Room.databaseBuilder(MyApplication.getAppContent(), AppDataBase.class, DBName)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        LogUtil.showD("RoomDemo", "AppDataBase---------onCreate");
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        LogUtil.showD("RoomDemo", "AppDataBase---------onOpen");
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        super.onDestructiveMigration(db);
                    }
                })
                .addMigrations(MIGRATION_1_2)
                .addMigrations(MIGRATION_2_1)
                .allowMainThreadQueries()
                .build();
    }

    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE IF EXISTS LessonVerBean");
            database.execSQL("CREATE TABLE IF NOT EXISTS LessonVerBean(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "lesson_id INTEGER NOT NULL" +
                    ",version INTEGER NOT NULL,type INTEGER NOT NULL)");
        }
    };

    static Migration MIGRATION_2_1 = new Migration(2, 1) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE IF EXISTS LessonVerBean");
        }
    };
}
