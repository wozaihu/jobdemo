package com.example.jobdemo.kotlin_code.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.databinding.ActivityKtlistviewdemoBinding
import com.example.jobdemo.kotlin_code.adapter.FruitAdapter
import com.example.jobdemo.util.ToastUtils

/**

 * @Author LYX

 * @Date 2022/11/7 15:02

 */
class KtListViewDemo : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityKtlistviewdemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list: MutableList<String> = ArrayList()
        for (i in 0..50) {
            list.add(i.toString())
        }
        binding.lv.adapter = FruitAdapter(this, list)
        binding.lv.setOnItemClickListener { _, _, i, _ ->
            ToastUtils.shortToast(this, "点击了第$i 行")
        }
    }
}