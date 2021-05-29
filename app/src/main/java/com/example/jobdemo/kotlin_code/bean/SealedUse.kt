package com.example.jobdemo.kotlin_code.bean

/**

 * @Author Administrator

 * @Date 2021/4/5 22:05

 */
//用sealed定义密封类,密封类及其子类只能定义在同一个类的顶层，不能嵌套在其他类中
sealed class SealedUse {
    class LeftViewHolder(var name: String) : SealedUse()
}