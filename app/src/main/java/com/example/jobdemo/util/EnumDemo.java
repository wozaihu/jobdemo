package com.example.jobdemo.util;

/**
 * @author Administrator
 */

public enum EnumDemo {
    /**
     * 枚举类创建单例
     */
    INSTANCE2("", 0);
    private String name;
    private int age;

    EnumDemo(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "enumDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
