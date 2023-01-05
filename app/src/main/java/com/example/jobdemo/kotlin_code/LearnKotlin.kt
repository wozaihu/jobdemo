package com.example.jobdemo.kotlin_code


/**

 * @Author LYX

 * @Date 2022/11/4 14:12

 */
fun main() {
    isNull(null)
    isNull("1")
    isNull("    ")
}

fun downToFor(n: Int) {
    for (i in n downTo 0) {
        println(i)
    }
}

fun printAToZ() {
    //创建从 “a” 到 “z” 的字符串数组
    val array3 = Array(26) { i -> ('a' + i).toString() }
    println(array3.contentToString())
}

fun isNull(str: String?) {
    val value = str == null || str.isBlank()
    println("内容是否为空：$value")
}