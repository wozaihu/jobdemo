package com.example.jobdemo.factory_demo.abstract_factory;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // 创建现代风格家具
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Sofa modernSofa = modernFactory.createSofa();
        Table modernTable = modernFactory.createTable();

        modernChair.sitOn();  // 输出: Sitting on a modern chair
        modernSofa.lieOn();   // 输出: Lying on a modern sofa
        modernTable.putThings(); // 输出: Putting things on a modern table

        // 创建古典风格家具
        FurnitureFactory classicFactory = new ClassicFurnitureFactory();
        Chair classicChair = classicFactory.createChair();
        Sofa classicSofa = classicFactory.createSofa();
        Table classicTable = classicFactory.createTable();

        classicChair.sitOn();  // 输出: Sitting on a classic chair
        classicSofa.lieOn();   // 输出: Lying on a classic sofa
        classicTable.putThings(); // 输出: Putting things on a classic table
    }
}
