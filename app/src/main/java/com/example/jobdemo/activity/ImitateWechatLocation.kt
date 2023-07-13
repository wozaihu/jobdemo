package com.example.jobdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.blankj.utilcode.util.BarUtils.transparentStatusBar
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityImitateWechatLocationBinding
import com.gyf.immersionbar.ImmersionBar

class ImitateWechatLocation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityImitateWechatLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ImmersionBar.with(this).titleBar(binding.flToolbar).statusBarDarkFont(true).init()
        binding.btnCancel.setOnClickListener { finish() }
    }
}
