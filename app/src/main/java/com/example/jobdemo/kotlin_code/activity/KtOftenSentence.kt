package com.example.jobdemo.kotlin_code.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.databinding.ActivityOftenSentenceBinding
import com.example.jobdemo.kotlin_code.dialog.BottomDialog
import com.example.jobdemo.kotlin_code.dialog.OftenSentenceDialog

/**

 * @Author LYX

 * @Date 2022/12/30 17:42

 */
class KtOftenSentence : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOftenSentenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnShowDialog.setOnClickListener {
            OftenSentenceDialog().showNow(
                supportFragmentManager,
                "tag"
            )
        }

        binding.btnShowBottomDialog.setOnClickListener {
            BottomDialog().showNow(
                supportFragmentManager,
                "bottomDialog"
            )
        }
    }
}