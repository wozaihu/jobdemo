package com.example.jobdemo.util

import android.text.InputFilter
import android.widget.EditText

//设置edittext只能输入数字和中文
fun EditText.allowInputChineseDigitAndLetter() {
    val filter = InputFilter { source, start, end, _, _, _ ->
        for (i in start until end) {
            // 检查字符是否为数字、中文或字母
            if (!Character.isDigit(source[i]) && !isChinese(source[i]) && !Character.isLetter(source[i])) {
                // 如果不是数字、中文或字母，则过滤掉这个字符
                return@InputFilter ""
            }
        }
        // 允许输入
        null
    }

    // 设置输入过滤器
    filters = arrayOf(filter)
}

// 判断是否为中文字符
private fun isChinese(c: Char): Boolean {
    val ub = Character.UnicodeBlock.of(c)
    return (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)
}

