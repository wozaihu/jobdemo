package com.example.jobdemo.factory_demo.abstract_factory;

public class ClassicTable implements Table{
    @Override
    public void putThings() {
        System.out.println("Putting things on a classic table");
    }
}
