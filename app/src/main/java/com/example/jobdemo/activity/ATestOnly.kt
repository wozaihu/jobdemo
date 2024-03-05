package com.example.jobdemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.databinding.ActivityAtestOnlyBinding
import com.example.jobdemo.util.ToastUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects


class ATestOnly : AppCompatActivity() {

    private lateinit var binding: ActivityAtestOnlyBinding
    private val tag = "测试专用"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtestOnlyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCreatePtfModel.setOnClickListener { }
        binding.btnFillPtfModel.setOnClickListener { }
        binding.btnFillColor.setOnClickListener { }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
        val date = simpleDateFormat.parse("2019-12-31 23:59")?.time
        date?.let {
            if (it > System.currentTimeMillis()) {
                ToastUtils.shortToast(this, "特定时间大")
            } else {
                ToastUtils.shortToast(this, "当前时间大")
            }
        }
    }
}