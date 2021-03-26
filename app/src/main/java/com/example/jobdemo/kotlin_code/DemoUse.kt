package com.example.jobdemo.kotlin_code

import kotlin.math.max

fun main() {
    println(getMax(89, 1320))
    println("杜甫年龄为：" + getAge("杜甫"))
    println("杜思明年龄为：" + getAge("杜思明"))
    getAttr(1)
    rangeDemo1()
    rangeDemo2()
    rangeDemo3()
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