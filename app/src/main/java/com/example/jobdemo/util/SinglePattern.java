package com.example.jobdemo.util;

/**
 * Created by Administrator on 2020/11/9.
 * 静态内部类，比双重检查单例要好,静态内部类要调用才会加载
 */

public  class SinglePattern{
    private SinglePattern() {
    }

    private static SinglePattern getInstance() {
        return siglenOnHolder.singlePattern;
    }



    private static class siglenOnHolder {
        private static final SinglePattern singlePattern = new SinglePattern();
    }
}
