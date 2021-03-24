package com.example.jobdemo.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 实体类要用@Entity标注,标注后面（tableName=""）可以设置表名,不设置默认用类名,@PrimaryKey设置主键
 * ，autoGenerate = true（自增长int和long类型适用），@ColumnInfo(name = "person_id")设置列名，
 * 不设置默认未字段名，如果字段未private提供get，set方法，如果要忽略哪个字段，在字段上加@Ignore
 * 如果有内部类或集合里有其他类，其他类字段会默认加到主类，字段也会创建列
 */
@Entity
public class PersonStateBean {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "person_id")
    public int personId;

    public String name;

    @ColumnInfo(defaultValue = "29")
    public int age;

    @ColumnInfo(name = "is_eat")
    public boolean isEat;

    @ColumnInfo(name = "type")
    public int type;

    @Override
    public String toString() {
        return "PersonStateBean{" +
                "id=" + id +
                ", personId=" + personId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isEat=" + isEat +
                ", type=" + type +
                '}';
    }
}
