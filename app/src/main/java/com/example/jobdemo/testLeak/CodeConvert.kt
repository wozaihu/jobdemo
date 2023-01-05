package com.example.jobdemo.testLeak

import android.content.Context
import android.content.Intent
import com.example.jobdemo.activity.DatePickerAndTimePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @Author LYX
 * @Date 2023/1/3 17:18
 */
class CodeConvert : BottomSheetDialogFragment() {
    fun start(context: Context) {
        val starter = Intent(context, DatePickerAndTimePicker::class.java)
        context.startActivity(starter)
    }
}