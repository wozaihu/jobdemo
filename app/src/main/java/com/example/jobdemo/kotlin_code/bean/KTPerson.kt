package com.example.jobdemo.kotlin_code.bean

/*
* 使用open关键字修饰的kotlin类才能被继承
* */
open class KTPerson {
    var name = ""

    var age = 0
    override fun toString(): String {
        return "KTPerson(name='$name', age=$age)"
    }

    //普通类,kotlin也要先创建实例才能调用KTPerson(),想想静态方法那样调用KTPerson需要是object修饰的单例类
    // 或companion object修饰的半生类
    fun isUse() {

    }

    //定义半生类，这样就可以直接调用了（因为object修饰的单例类会把所有方法弄成静态那样直接点调用，
    // 用companion object，则只有这里面的半生类能直接类名.调用）
    companion object {
        fun isOK() {
            println("用companion object 修饰的半生类，一个类只有一个半生类")
        }

        //用@JvmStatic修饰的方法才能被Java方法当静态方法调用，只用单例类（用object修饰的类和半生类<用companion object修饰的类才能调用@JvmStatic注释>）
        @JvmStatic
        fun isJavaSupport() {
            println("KT类中定义能让Java调用的静态方法")
        }
    }

}