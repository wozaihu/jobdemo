package com.example.jobdemo.util;

/**
 *
 * @author Administrator
 * @date 2020/11/9
 * 静态内部类，比双重检查单例要好,静态内部类要调用才会加载
 */

public class SinglePattern {
    private SinglePattern() {
    }

    private static SinglePattern getInstance() {
        return SingleOnHolder.SINGLE_PATTERN;
    }


    private static class SingleOnHolder {
        private static final SinglePattern SINGLE_PATTERN = new SinglePattern();
    }
}
