package com.example.jobdemo.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobdemo.R
import com.example.jobdemo.R2.id.fitToContents
import com.example.jobdemo.databinding.ActivityTestStatusBarUtilsBinding
import com.example.jobdemo.kotlin_code.adapter.FruitRecycleAdapter
import com.example.jobdemo.kotlin_code.list
import com.example.jobdemo.util.DensityUtil
import com.example.jobdemo.util.MeasureUtils
import com.example.jobdemo.util.StatusBarUtils
import com.example.jobdemo.util.ToastUtils
import com.example.jobdemo.util.Utils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.gyf.immersionbar.ImmersionBar
import io.rong.imkit.utils.RongUtils.screenHeight

class TestStatusBarUtils : AppCompatActivity() {
    private lateinit var binding: ActivityTestStatusBarUtilsBinding
    private var screenHeight: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenHeight = MeasureUtils.getScreenHeight(this@TestStatusBarUtils)
        binding = ActivityTestStatusBarUtilsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //当前案例含ActionBar
        ImmersionBar.with(this).titleBar(binding.flToolbar).navigationBarColor(R.color.transparent)
            .statusBarDarkFont(true).init()
        val list: MutableList<String> = ArrayList()
        for (i in 0..50) {
            list.add("第${i}行")
        }
        binding.rv.layoutManager = LinearLayoutManager(this)
        val adapter = FruitRecycleAdapter(binding.rv.context, list)
        adapter.setListener { ToastUtils.shortToast(this, "点击了${it}的图片") }
        binding.rv.adapter = adapter
    }
}