package com.example.jobdemo.factory_demo;

public class FactoryA implements FactoryMethod{
    @Override
    public FactoryProduct createProduct() {
        return new ProductA();
    }
}
