package com.example.jobdemo.bean;

public class TestBean {
    private String name;
    private int age;
    private String grander;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


    public TestBean(String name, int age, String grander) {
        this.name = name;
        this.age = age;
        this.grander = grander;
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

    public String getGrander() {
        return grander;
    }

    public void setGrander(String grander) {
        this.grander = grander;
    }
}
