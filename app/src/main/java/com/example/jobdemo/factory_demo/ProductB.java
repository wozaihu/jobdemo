package com.example.jobdemo.factory_demo;


public class ProductB implements FactoryProduct {
    @Override
    public void use() {
        System.out.println("Using Product B");
    }
}
