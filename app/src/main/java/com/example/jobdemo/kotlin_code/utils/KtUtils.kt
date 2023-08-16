package com.example.jobdemo.kotlin_code.utils

import android.text.TextUtils
import android.util.Log

/**

 * @Author LYX

 * @Date 2022/12/12 10:22

 */

private const val key: String = "defaultKey"

fun isExistEmpty(vararg strings: String): Boolean {
    for (str in strings) {
        Log.d("KtUtils", "isAllEmpty: ------$str")
        if (TextUtils.isEmpty(str)) {
            return true
        }
    }
    return false
}

fun getDefaultValue(): String {
    return key
}


/**
 * 把工资千转换为k
 * @param salary String
 * @return String
 */
fun convertSalaryTok(salary: String): String {
    if (salary == "面议" || salary == "不限" || !salary.contains("-")) {
        return salary
    } else {
        val start = salary.substring(0, salary.indexOf("-")).toDouble()
        val end = salary.substring(salary.indexOf("-") + 1, salary.indexOf("元")).toDouble()

        if (start < 1000 && end < 1000) {
            return salary
        } else {
            val unit = salary.substring(salary.indexOf("元") + 1)
            val startFormatted = if (start >= 1000) {
                String.format("%.2f", start / 1000)
            } else {
                String.format("%.2f", start)
            }
            val endFormatted = if (end >= 1000) {
                String.format("%.2f", end / 1000) + "k"
            } else {
                String.format("%.2f", end)
            }
            return "$startFormatted-$endFormatted$unit"
        }
    }
}
