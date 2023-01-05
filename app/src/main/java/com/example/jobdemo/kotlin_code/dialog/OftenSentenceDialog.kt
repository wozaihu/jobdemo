package com.example.jobdemo.kotlin_code.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobdemo.R
import com.example.jobdemo.databinding.DialogOftenSentenceBinding
import com.example.jobdemo.kotlin_code.adapter.FruitRecycleAdapter
import com.example.jobdemo.util.ToastUtils

/**

 * @Author LYX

 * @Date 2022/12/30 11:35
 * AppCompatDialogFragment 通过onCreateView 直接返回view，或通过 onCreateDialog直接返回一个dialog

 */
class OftenSentenceDialog : AppCompatDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogOftenSentenceBinding.inflate(inflater, container, false)
        val list: MutableList<String> = ArrayList()
        for (i in 0..50) {
            list.add("第${i}行")
        }
        binding.rvSentenceList.layoutManager = LinearLayoutManager(context)
        val adapter = FruitRecycleAdapter(list)
        adapter.setListener { ToastUtils.shortToast(context, "点击了${it}的图片") }
        binding.rvSentenceList.adapter = adapter
        binding.tvAdd.setOnClickListener { ToastUtils.shortToast(context, "点击了添加") }
        binding.tvRevamp.setOnClickListener { ToastUtils.shortToast(context, "点击了修改") }
        return binding.root
    }


    override fun onStart() {
        super.onStart()
//        initWindow() 如果手动设置宽高，在此处设置
    }


    private fun initWindow() {
        //初始化window相关表现
        val window = dialog?.window
        //设备背景为透明（默认白色）,不设置透明，根布局设置的带角度的背景无法显示，设置透明后会导致宽度不能自适应为对话框
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //设置window位置
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        window?.attributes?.gravity = Gravity.CENTER
    }

    /**
     * @return Int 对话框主题
     */
    override fun getTheme(): Int {
        return R.style.CustomDialogWidth
    }
}