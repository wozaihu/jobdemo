package com.example.jobdemo.kotlin_code.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ItemImageTextBinding

/**

 * @Author LYX

 * @Date 2022/11/7 16:11

 */
class FruitAdapter(val context: Context, private val data: MutableList<String>) : BaseAdapter() {

    override fun getCount(): Int = data.size

    override fun getItem(p0: Int): Any = data[p0]

    override fun getItemId(p0: Int): Long = 0

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding: ItemImageTextBinding
        var view = p1
        if (p1 == null) {
            binding = ItemImageTextBinding.inflate(LayoutInflater.from(context), p2, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = view!!.tag as ItemImageTextBinding
        }
        if (p0 % 2 == 0) {
            binding.img.setImageResource(R.mipmap.a2)
        } else {
            binding.img.setImageResource(R.mipmap.a3)
        }
        binding.tvName.text = data[p0]
        return view
    }

}