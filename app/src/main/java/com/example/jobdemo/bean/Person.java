package com.example.jobdemo.bean;

public class Person {
    public Person(String name, int id, boolean eatRice) {
        this.name = name;
        this.id = id;
        this.eatRice = eatRice;
    }

    String name;
    int id;
    boolean eatRice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEatRice() {
        return eatRice;
    }

    public void setEatRice(boolean eatRice) {
        this.eatRice = eatRice;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", id=" + id + ", eatRice=" + eatRice + '}';
    }
}
