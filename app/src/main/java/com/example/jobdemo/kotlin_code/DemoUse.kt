package com.example.jobdemo.kotlin_code

import com.example.jobdemo.kotlin_code.bean.KTPerson
import com.example.jobdemo.kotlin_code.bean.KTStudent
import kotlin.concurrent.thread
import kotlin.math.max

fun main() {
    println(getMax(89, 1320))
    println("杜甫年龄为：" + getAge("杜甫"))
    println("杜思明年龄为：" + getAge("杜思明"))
    getAttr(1)
    rangeDemo1()
    rangeDemo2()
    rangeDemo3()
    //调用没有参数的次级构造函数
    val ktStudent = KTStudent()

    //调用两个参数的次级构造函数
    val ktStudent1 = KTStudent("王阳明", 996)

    //调用主构造函数
    val ktStudent2 = KTStudent("学号23", 97, "杜甫", 1309)

    forList()
    addData()
    forMap()

    getMaxLength()

    Thread { println("lambda启动一个线程") }.start()

    canNull(null)

}

fun getMax(a: Int, b: Int) = max(a, b)


fun getAge(name: String) = when {
    name.startsWith("杜") -> 106
    name == "李白" -> 1320
    name == "王明" -> 99
    else -> 0
}

//属性匹配，相当于instanceOf(是否包含)
fun getAttr(num: Number) = when (num) {
    is Int -> println("传进来的是int类型")
    is Double -> println("传进来的是double类型")
    else -> println("没有匹配的类型类型")
}

fun rangeDemo1() {
    for (i in 0..10) {
        print(i)
        print(" ")
    }
    println()
}

//不包含10，且步长为2
fun rangeDemo2() {
    for (i in 0 until 10 step 2) {
        print(i)
        print(" ")
    }
    println()
}

//倒叙
fun rangeDemo3() {
    for (i in 10 downTo 0) {
        print(i)
        print(" ")
    }
    println()
}

//不可变集合
val list = listOf("李白", "杜甫", "白居易")

//可变集合mutable开头
val muTableList = mutableListOf(99, 55)

//map集合使用
val mapList = mapOf(1 to "孔子", 2 to "王阳明")

fun forList() {
    for (i in list) {
        print(i + " ")
    }
    println()
}

fun addData() {
    muTableList.add(88)
    for (i in muTableList) {
        print(i)
    }
    println()
}

fun forMap() {
    for ((number, name) in mapList) {
        print("编号==" + number + "------name==" + name)
    }
    println()
}
//lambda用法{参数名:参数类型，参数名:参数类型->函数体}

fun getMaxLength() {
    //maxBy查找最大长度的字符串
    println("最大长度是：" + list.maxBy { it.length })

    //过滤字符串
    val newFilter = muTableList.filter { it < 60 }
    for (f in newFilter) {
        print(f)
    }
    println("--------")
}

//toUpperCase 转换为大写
//any 任意一个满足需求
//all 全部满足需求

//参数类型后面加？表示可以为空，否则不可以为null,可为null的一定要加非空判断

//?.表示判断对象是否为空，不为空则返回后面紧跟的操作，?:表示为空返回的内容（为空也可以不处理，那就没有操作了）

//!!. 让编译器不要检查是否为空，这样可以通过编译，慎用
fun canNull(ktStudent: KTStudent?) = ktStudent?.readBooks() ?: "为空就返回我了啊"

//let 函数可以把原始对象传到lambda函数中，还能处理全局对象（kotlin中因为全局对象随时有可能被其他线程修改，用if判断这不准确）

fun letUse(ktStudent: KTStudent?) {
    ktStudent?.let {
        it.readBooks()
        it.toString()
    }
    val yourName = "宗天华"
    val yourAge = 99

    //字符串内嵌${} 如果大括号里只有一个参数可以省略
    fun stringUse() {
        println("你的名字：$yourName,你的年龄：$yourAge")
    }

    //主构造函数都设置一个默认值，就可以通过键值对方式，想传几个值就传几个值了，就用不到次级函数了
}






