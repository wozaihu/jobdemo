package com.example.jobdemo.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.databinding.ActivityCoroutinesDemoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesDemo : AppCompatActivity() {
    private val tag = "协程demo"
    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCoroutinesDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e(tag, "start---Id：${Thread.currentThread().id}")
        job = CoroutineScope(Dispatchers.Main).launch {
            Log.e(tag, "开始执行协程任务---Id：${Thread.currentThread().id}")
            val result = fetchDataFromNetwork()
            Log.e(tag, "网络请求结果：$result,---Id：${Thread.currentThread().id}")
        }
        Log.e(tag, "end")
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        Log.e(tag, "onDestroy")

    }

    private suspend fun fetchDataFromNetwork(): String {
        delay(2000) // 模拟网络请求的延迟
        return "网络请求结果"
    }
}