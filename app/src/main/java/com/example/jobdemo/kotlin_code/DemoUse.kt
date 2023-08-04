package com.example.jobdemo.kotlin_code

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("Start")
    GlobalScope.launch {
        delay(6000)
        println("End")
    }
//    Thread.sleep(2000)
    println("Hello World---0")
}

suspend fun fetchData(): String {
    delay(1000) // 模拟耗时的操作
    return "Data from network"
}

private fun simulationDelay() {
    println("start")
    //开启一个协程作用域
    runBlocking {
        // 启动一个后台协程
        val job = launch {
            // 模拟耗时操作
            delay(1000)
            // 打印 "Hello World"
            println("Hello World")
        }
        // 执行其他操作
        println("美好的一天")
        job.join()  //这里表示要等后面的代码要等协程执行完才能执行
        println("我是最后的")
    }
    println("end")

    /**
    start
    美好的一天
    Hello World
    我是最后的
    end
     */
}









