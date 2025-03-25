package com.example.jobdemo.factory_demo;

public class FactoryB implements FactoryMethod {
    @Override
    public FactoryProduct createProduct() {
        return new ProductB();
    }
}
