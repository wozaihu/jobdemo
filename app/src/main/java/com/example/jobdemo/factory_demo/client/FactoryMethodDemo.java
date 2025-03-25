package com.example.jobdemo.factory_demo.client;

import com.example.jobdemo.factory_demo.FactoryA;
import com.example.jobdemo.factory_demo.FactoryB;
import com.example.jobdemo.factory_demo.ProductA;
import com.example.jobdemo.factory_demo.ProductB;

public class FactoryMethodDemo {
    public static void main(String[] args) {
        FactoryA factoryA = new FactoryA();
        ProductA productA = (ProductA) factoryA.createProduct();
        productA.use();

        FactoryB factoryB = new FactoryB();
        ProductB productB = (ProductB) factoryB.createProduct();
        productB.use();
    }
}
