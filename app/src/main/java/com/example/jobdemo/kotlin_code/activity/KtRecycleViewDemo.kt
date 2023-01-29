package com.example.jobdemo.kotlin_code.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityKtRecycleViewDemoBinding
import com.example.jobdemo.kotlin_code.adapter.FruitRecycleAdapter
import com.example.jobdemo.util.ToastUtils
import com.google.android.material.divider.MaterialDividerItemDecoration

class KtRecycleViewDemo : AppCompatActivity() {
    private val list: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityKtRecycleViewDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        for (i in 0..50) {
            list.add("第${i}行")
        }
        binding.rv.layoutManager = LinearLayoutManager(this)
        //MaterialDividerItemDecoration需要material:1.5.0（1.5需要目标版本32）
        val itemDecoration =
            MaterialDividerItemDecoration(this, MaterialDividerItemDecoration.VERTICAL)
        itemDecoration.setDividerColorResource(this, R.color.green)
        itemDecoration.dividerThickness = 1
        binding.rv.addItemDecoration(itemDecoration)
        val adapter = FruitRecycleAdapter(this, list)
        /**
         * setListener需要的是一个lambda，语法结构：{参数名1：参数类型,参数名2：参数类型->函数体}，一定要有{}大括号
         *相当于：adapter.setListener({ str:String -> ToastUtils.shortToast(this, "点击了${str}的图片") })
         * 只有一个参数就直接简写了
         */
        adapter.setListener { ToastUtils.shortToast(this, "点击了${it}的图片") }
        binding.rv.adapter = adapter
        val helper = ItemTouchHelper(getTouchHelper())



        binding.btnSort.setOnClickListener {
            val button = it as Button
            if (button.text.equals("排序")) {
                button.text = "完成"
                adapter.setType(1)
                helper.attachToRecyclerView(binding.rv)
            } else {
                button.text = "排序"
                adapter.setType(0)
                helper.attachToRecyclerView(null)
            }
        }
    }

    private fun getTouchHelper(): ItemTouchHelper.SimpleCallback {
        val touchHelperCall =
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
                @SuppressLint("NotifyDataSetChanged")
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    //取出原位置的position
                    val from = viewHolder.bindingAdapterPosition
                    //取出目标位置的position
                    val to = target.bindingAdapterPosition
                    //取出原位置的数据
                    val temp = list[from]
                    //删除原位置的数据
                    list.removeAt(from)
                    //向目标位置添加原位置的数据
                    list.add(to, temp)
                    //通知Adapter移动了Item,移动后未全局刷新，adapter中使用position获取list数据会有bug，排序时避免点击获取数据
                    recyclerView.adapter?.notifyItemMoved(from, to)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                }
            }
        return touchHelperCall
    }
}