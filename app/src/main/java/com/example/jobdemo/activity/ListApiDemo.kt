package com.example.jobdemo.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdemo.base.BaseActivity
import com.example.jobdemo.databinding.ActivityListApiBinding
import com.example.jobdemo.databinding.ItemTextBinding
import com.example.jobdemo.kotlin_code.bean.SentenceBean
import com.example.jobdemo.util.ToastUtils
import com.google.gson.Gson

/**
 * @author Administrator
 * recyclerview 刷新，删除，插入使用
 * list 一些api使用
 */
class ListApiDemo : BaseActivity() {
    val str =
        "{\"status\":\"1\",\"message\":\"成功!\",\"userInfo\":[{\"WordText\":\"how do you do\",\"SortId\":0,\"ID\":415708},{\"WordText\":\"逗逗你\",\"SortId\":0,\"ID\":415707},{\"WordText\":\"逗逗你\",\"SortId\":0,\"ID\":415706},{\"WordText\":\"逗逗你\",\"SortId\":0,\"ID\":415705},{\"WordText\":\"忙碌中，请稍后联系\",\"SortId\":1,\"ID\":415557},{\"WordText\":\"你好，你明天方便过来面试吗\",\"SortId\":2,\"ID\":415558},{\"WordText\":\"你好，可以发一份你的简历过来吗\",\"SortId\":3,\"ID\":415559},{\"WordText\":\"对不起，看了你的简历后觉得不太适合，祝你早日找到满意的工作\",\"SortId\":4,\"ID\":415560}]}"
    lateinit var adapter: MyAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListApiBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val bean: SentenceBean = Gson().fromJson(str, SentenceBean::class.java)
        with(binding) {
            val list = bean.userInfo

            rv.layoutManager = LinearLayoutManager(this@ListApiDemo)
            adapter = MyAdapter(this@ListApiDemo, list)
            rv.adapter = adapter

            btnRevamp.setOnClickListener {
                if (etPosition.text.isEmpty() || etContent.text.isEmpty()) {
                    ToastUtils.shortToast(this@ListApiDemo, "position和内容都不能为空")
                } else {
                    for ((i, value) in list.withIndex()) {
                        if (value.ID.toString() == etPosition.text.toString()) {
                            value.WordText = etContent.text.toString()
                            adapter.notifyItemChanged(i)
                            break
                        }
                    }
                }
            }

            btnDelete.setOnClickListener {
                list.firstOrNull { it.ID == etPosition.text.toString().toInt() }?.also {
                    val index = list.indexOfFirst { it.ID == etPosition.text.toString().toInt() }
                    list.removeAt(index)
                    adapter.notifyItemRemoved(index)
                    adapter.notifyItemRangeChanged(index, adapter.itemCount)
                }
            }

            btnAdd.setOnClickListener {
                if (etContent.text.toString().isNotEmpty()) {
                    list.maxBy { it.ID }.apply {
                        list.add(0, SentenceBean.UserInfo(ID + 1, 0, etContent.text.toString()))
                        adapter.notifyItemInserted(0)
                        adapter.notifyItemRangeChanged(0, adapter.itemCount)
                    }
                } else {
                    ToastUtils.shortToast(this@ListApiDemo, "请输入要添加的内容")
                }
            }

        }
    }


    class MyAdapter(val context: Context, val list: ArrayList<SentenceBean.UserInfo>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        class MyViewHolder(val binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val binding = ItemTextBinding.inflate(LayoutInflater.from(context), parent, false)
            return MyViewHolder(binding)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.binding.tv.text = "id:${list[position].ID}，${list[position].WordText}"
            holder.itemView.setOnClickListener {
                ToastUtils.shortToast(context, "id:${list[position].ID}，${list[position].WordText}")
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
}