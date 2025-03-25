package com.example.jobdemo.factory_demo.abstract_factory;

public class ClassicFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ClassicChair();
    }

    @Override
    public Sofa createSofa() {
        return new ClassicSofa();
    }

    @Override
    public Table createTable() {
        return new ClassicTable();
    }
}
