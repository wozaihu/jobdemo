package com.example.jobdemo.kotlin_code.bean

/**

 * @Author Administrator

 * @Date 2021/3/26 19:58

kt的接口和Java8的接口都可以创建默认实现，在方法后面加大括号{}，里面就是默认实现，
这样实现这个接口的类可以选择是否覆盖实现，不覆盖就是默认实现
 */
interface Study {
    fun readBooks()
    fun doHomework() {
        println("doHomework的默认实现")
    }
}