package com.example.jobdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityBehaviorUseBinding
import com.example.jobdemo.util.ToastUtils
import com.example.jobdemo.util.enableDrag

class BehaviorUse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBehaviorUseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.greenView.setOnClickListener { ToastUtils.shortToast(this,"点击了view") }
        enableDrag(binding.greenView)
    }
}