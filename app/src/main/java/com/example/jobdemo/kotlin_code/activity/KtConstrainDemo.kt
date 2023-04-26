package com.example.jobdemo.kotlin_code.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.jobdemo.bean.Book
import com.example.jobdemo.databinding.ActivityKtConstrainDemoBinding
import com.example.jobdemo.kotlin_code.bean.SentenceBean
import com.example.jobdemo.util.LogUtil
import com.example.jobdemo.util.ToastUtils
import com.google.gson.Gson

/**

 * @Author LYX

 * @Date 2023/2/8 15:04

 */
class KtConstrainDemo : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityKtConstrainDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.image5.setOnClickListener {
            ToastUtils.shortToast(this, "点击了A1")
        }
        val str =
            "{\"status\":\"1\",\"message\":\"成功!\",\"userInfo\":[{\"WordText\":\"how do you do\",\"SortId\":0,\"ID\":415708},{\"WordText\":\"逗逗你\",\"SortId\":0,\"ID\":415707},{\"WordText\":\"逗逗你\",\"SortId\":0,\"ID\":415706},{\"WordText\":\"逗逗你\",\"SortId\":0,\"ID\":415705},{\"WordText\":\"忙碌中，请稍后联系\",\"SortId\":1,\"ID\":415557},{\"WordText\":\"你好，你明天方便过来面试吗\",\"SortId\":2,\"ID\":415558},{\"WordText\":\"你好，可以发一份你的简历过来吗\",\"SortId\":3,\"ID\":415559},{\"WordText\":\"对不起，看了你的简历后觉得不太适合，祝你早日找到满意的工作\",\"SortId\":4,\"ID\":415560}]}"

        binding.btnStartSentence.setOnClickListener {
            Gson().fromJson(str, SentenceBean::class.java).let {
                startActivity(Intent(this, KtOftenSentence::class.java).apply {
                    putExtra("bean", it)
                    putParcelableArrayListExtra(
                        "list",
                        it.userInfo as ArrayList<SentenceBean.UserInfo>
                    )
                    Log.d("常用语", "第一条-----${it.userInfo[0].WordText}")
                })
            }
        }

        binding.btnStartSentenceLeft.setOnClickListener {

        }

        binding.btnStartSentenceLeft.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val vibrator = ContextCompat.getSystemService(this, Vibrator::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(
                        VibrationEffect.createOneShot(
                            30,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    vibrator?.vibrate(30)
                }
            }
            return@setOnTouchListener true
        }

        val book: Book? = null
        LogUtil.showD("bookName==${book?.bookName ?: "滚啊"}")
    }
}