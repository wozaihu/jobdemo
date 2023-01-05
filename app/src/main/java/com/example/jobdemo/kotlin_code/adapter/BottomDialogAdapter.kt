package com.example.jobdemo.kotlin_code.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdemo.databinding.ItemTextBinding
import com.example.jobdemo.util.ToastUtils

/**

 * @Author LYX

 * @Date 2022/11/8 14:23

 */
class BottomDialogAdapter(private val data: MutableList<String>) :
    RecyclerView.Adapter<BottomDialogAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: ItemTextBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tv.text = data[position]
        holder.binding.root.setOnClickListener {
            ToastUtils.shortToast(
                it.context,
                "点击了第${position}行"
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}