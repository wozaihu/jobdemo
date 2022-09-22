package com.example.jobdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityTestStatusBarUtilsBinding
import com.example.jobdemo.util.StatusBarUtils

class TestStatusBarUtils : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestStatusBarUtilsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //当前案例含ActionBar
        StatusBarUtils.with(this)
            .setIsActionBar(true)
            .clearActionBarShadow()
            .setDrawable(AppCompatResources.getDrawable(this, R.drawable.title_bar))
            .init()
    }
}