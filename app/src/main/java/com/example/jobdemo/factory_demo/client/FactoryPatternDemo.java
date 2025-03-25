package com.example.jobdemo.factory_demo.client;

import com.example.jobdemo.factory_demo.FactoryProduct;
import com.example.jobdemo.factory_demo.ProductA;
import com.example.jobdemo.factory_demo.ProductB;
import com.example.jobdemo.factory_demo.SimpleFactory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        ProductA productA = (ProductA) SimpleFactory.CreateProduct("A");
        assert productA != null;
        productA.use();

        ProductB productB = (ProductB) SimpleFactory.CreateProduct("B");
        assert productB != null;
        productB.use();
    }
}
