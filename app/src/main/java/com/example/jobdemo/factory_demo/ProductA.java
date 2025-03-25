package com.example.jobdemo.factory_demo;


public class ProductA implements FactoryProduct {
    @Override
    public void use() {
        System.out.println("Using Product A");
    }
}
