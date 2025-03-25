package com.example.jobdemo.factory_demo;

public class SimpleFactory {
    public static FactoryProduct CreateProduct(String type) {
        if (type.equals("A")) {
            return new ProductA();
        } else if (type.equals("B")) {
            return new ProductB();
        }
        return null;
    }
}
