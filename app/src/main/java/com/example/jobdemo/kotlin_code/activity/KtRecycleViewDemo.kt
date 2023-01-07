package com.example.jobdemo.kotlin_code.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityKtRecycleViewDemoBinding
import com.example.jobdemo.kotlin_code.adapter.FruitRecycleAdapter
import com.example.jobdemo.util.DensityUtil
import com.example.jobdemo.util.ToastUtils
import com.google.android.material.divider.MaterialDividerItemDecoration

class KtRecycleViewDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityKtRecycleViewDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list: MutableList<String> = ArrayList()
        for (i in 0..50) {
            list.add("第${i}行")
        }
        binding.rv.layoutManager = LinearLayoutManager(this)
        //MaterialDividerItemDecoration需要material:1.5.0（1.5需要目标版本32）
        val itemDecoration =
            MaterialDividerItemDecoration(this, MaterialDividerItemDecoration.VERTICAL)
        itemDecoration.setDividerColorResource(this, R.color.lightGray)
        itemDecoration.dividerThickness = DensityUtil.px2dip(this, 10f)
        binding.rv.addItemDecoration(itemDecoration)
        val adapter = FruitRecycleAdapter(list)
        /**
         * setListener需要的是一个lambda，语法结构：{参数名1：参数类型,参数名2：参数类型->函数体}，一定要有{}大括号
         *相当于：adapter.setListener({ str:String -> ToastUtils.shortToast(this, "点击了${str}的图片") })
         * 只有一个参数就直接简写了
         */
        adapter.setListener { ToastUtils.shortToast(this, "点击了${it}的图片") }
        binding.rv.adapter = adapter
    }
}