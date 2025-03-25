package com.example.jobdemo.factory_demo.abstract_factory;

public class ClassicChair implements Chair{
    @Override
    public void sitOn() {
        System.out.println("Sitting on a classic chair");
    }
}
