package com.example.jobdemo.kotlin_code

import com.example.jobdemo.kotlin_code.bean.Gender
import com.example.jobdemo.kotlin_code.bean.KTPerson
import com.example.jobdemo.kotlin_code.bean.KTStudent
import com.example.jobdemo.kotlin_code.bean.Teacher

/**

 * @Author Administrator

 * @Date 2021/3/31 14:56

 */

//新建一个kt文件，里面写的方法就都是顶层方法，Java类中调用KT的顶层方法（默认全是静态方法），用文件名.方法名就可以了

fun main() {
    KTPerson().appendFunction()
    val studentLi = KTStudent("唐朝", 701, "李白", 1320)
    runFunUse()
    applyFunUse()
    withFunUse()
    KTPerson.isOK() //直接调用半生类

    //kotlin要用（::对象.isInitialized判断不为空,加!就表示为空）
    if (!::s.isInitialized) { //这里表示没有初始化就进来初始化
        s = "延迟初始化后赋值"
    }
    val teacher = Teacher("王安石", Gender.MAN)
    println(teacher.toString())
}

//lateinit用来延迟初始化全局变量
lateinit var s: String

//kotlin类后面加点方法名就可以注入新函数到类中
fun KTPerson.appendFunction() {
    println("我是KTPerson的附加函数:" + KTPerson().gender)
}

//扩展属性
var KTPerson.gender: String
    get() = ""
    set(value) {

    }

val fruits = listOf<String>("Apple", "peach", "watermelon")

//run函数必须在对象后.调用，会把对象传进来，返回值为lambda最后一行
fun runFunUse() {
    val result = StringBuffer().run {
        append("start eat fruit:\n")
        for (fruit in fruits) {
            append(fruit + "\n")
        }
        append("全部吃完了\n")
    }
    println(result)
}

//apply函数必须在对象后,无法指定返回值，会直接返回调用的对象
fun applyFunUse() {
    val result = StringBuffer().apply {
        append("start eat fruit:\n")
        for (fruit in fruits) {
            append(fruit + "\n")
        }
        append("全部吃完了-apply\n")
    }
    println(result.toString())
}

//with函数接收一个对象，和一个lambda，返回值为lambda最后一行
fun withFunUse() {
    val result = with(StringBuffer()) {
        append("start eat fruit:\n")
        for (fruit in fruits) {
            append(fruit + "\n")
        }
        append("全部吃完了-with")
        //最后一次会作为返回值返回
        toString()
    }
    println(result)
}