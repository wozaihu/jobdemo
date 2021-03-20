package com.example.jobdemo.bean;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class StudentBean {
    @PrimaryKey(autoGenerate = true)
    private Long Id;
    private String name;
    private int age;
    private String gender;
    private String studentClass;

    @Ignore
    public StudentBean(String name, int age, String gender, String studentClass) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.studentClass = studentClass;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", studentClass='" + studentClass + '\'' +
                '}';
    }
}
