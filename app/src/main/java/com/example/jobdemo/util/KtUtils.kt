package com.example.jobdemo.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.jobdemo.activity.InfoShow

/**
 * 计算两个float相加，四舍五入，保留两位小数
 */
fun addAndRoundFloats(num1: Float, num2: Float): Float {
    val sum = num1 + num2
    return (sum * 100).toInt().toFloat() / 100
}

/**
 * 整个页面中，点击的不是EditText就关闭软键盘
 */
@SuppressLint("ClickableViewAccessibility")
fun closeKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val rootView = activity.window.decorView.rootView
    rootView.setOnTouchListener { _, _ ->
        val currentFocus = activity.currentFocus
        if (currentFocus != null && currentFocus is EditText) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
        false
    }
}

/**
 *view.setBackgroundColor(getRandomColor())
 */
fun getRandomColor(): Int {
    val red = (0..255).random()
    val green = (0..255).random()
    val blue = (0..255).random()
    return Color.rgb(red, green, blue)
}


/**
 * 生成一个包含 a 个元素的可变列表
 * @param a 元素个数
 * @return 可变列表
 */
fun generateList(a: Int): MutableList<String> {
    val list = mutableListOf<String>()
    for (i in 1..a) {
        list.add("第 $i 行")
    }
    return list
}

/**
 * 设置view跟随手指移动
 * @param view View
 */

fun enableDrag(view: View) {
    var lastX = 0f
    var lastY = 0f
    var isDragging = false
    view.setOnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.rawX
                lastY = event.rawY
                isDragging = false
            }

            MotionEvent.ACTION_MOVE -> {
                val deltaX = event.rawX - lastX
                val deltaY = event.rawY - lastY
                if (!isDragging && (deltaX != 0f || deltaY != 0f)) {
                    isDragging = true
                }
                if (isDragging) {
                    val viewX = view.x + deltaX
                    val viewY = view.y + deltaY
                    view.x = viewX
                    view.y = viewY
                }
                lastX = event.rawX
                lastY = event.rawY
            }

            MotionEvent.ACTION_UP -> {
                if (!isDragging) {
                    // 处理点击事件
                    view.performClick()
                }
            }
        }
        true
    }
}