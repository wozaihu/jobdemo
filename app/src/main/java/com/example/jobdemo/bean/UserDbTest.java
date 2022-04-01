package com.example.jobdemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author LYX
 * @Date 2022/3/11 14:12
 */
@Entity
public class UserDbTest {


    @Id
    @Unique
    @NotNull
    @OrderBy
    private Long userId;

    @Property
    private String userName;

    @Property
    private int age;

    @Generated(hash = 1902946757)
    public UserDbTest(@NotNull Long userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    @Generated(hash = 461819965)
    public UserDbTest() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
