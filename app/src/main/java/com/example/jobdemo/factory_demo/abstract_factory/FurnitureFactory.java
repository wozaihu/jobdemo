package com.example.jobdemo.factory_demo.abstract_factory;

/**
 * 创建家具的接口
 */
public interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
    Table createTable();
}
