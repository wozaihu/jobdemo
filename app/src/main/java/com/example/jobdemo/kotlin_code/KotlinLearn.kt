package com.example.jobdemo.kotlin_code

/**

 * @Author LYX

 * @Date 2022/11/10 11:05

 */
class KotlinLearn(var li: String) {
    private var i = 0

    /**
     *直接定义的内部类就像Java的静态内部类，不会持有外部类的引用
     */
    class InClass() {
    }

    /**
     * 使用inner定义的内部类就像Java的普通内部类，会持有外部类的引用
     */
    inner class HasOutClassObject() {
        val n = i
        val name = li
    }
}


