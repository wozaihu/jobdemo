package com.example.jobdemo.kotlin_code.utils

import android.text.TextUtils
import android.util.Log

/**

 * @Author LYX

 * @Date 2022/12/12 10:22

 */
fun isExistEmpty(vararg strings: String): Boolean {
    for (str in strings) {
        Log.d("KtUtils", "isAllEmpty: ------$str")
        if (TextUtils.isEmpty(str)) {
            return true
        }
    }
    return false
}

