package com.example.jobdemo.kotlin_code.bean

/**

 * @Author Administrator

 * @Date 2021/3/26 20:31
KT定义个数据类直接用data修饰就可以了，会自动生成get，set，toString，equals，hashcode
 */
data class Company(var companyName: String, var companyPhone: String)