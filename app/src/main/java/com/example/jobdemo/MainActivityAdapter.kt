package com.example.jobdemo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdemo.bean.ActivityBean
import com.example.jobdemo.databinding.ItemMainactivityRycyclerviewBinding

/**
 * @author Administrator
 */
class MainActivityAdapter(private val activityS: List<ActivityBean>) :
    RecyclerView.Adapter<MainActivityAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMainactivityRycyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvDemoName.text = activityS[position].activityChinesName
        holder.itemView.setOnClickListener { v: View ->
            v.context.startActivity(Intent(v.context, activityS[position].className))
        }
    }

    override fun getItemCount(): Int {
        return activityS.size
    }

    class MyViewHolder(binding: ItemMainactivityRycyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvDemoName: TextView = binding.tvDemoName
    }


    /**
     * 判断是否是每一组的头部
     */
    fun isGroupHead(position: Int): Boolean {
        return if (position == 0) {
            true
        } else {
            val currentGroup = getGroupName(position)
            val preGroup = getGroupName(position - 1)
            //判断当前名，与上一行是否一样，不一样就是头
            currentGroup != preGroup
        }
    }

    /**
     * 获取组名
     */
    fun getGroupName(position: Int): String = activityS[position].initial


    fun getSortLettersFirstPosition(letters: String?): Int {
        if (activityS.isEmpty()) {
            return -1
        }
        var position = -1
        for (index in activityS.indices) {
            if (activityS[index].initial.equals(letters)) {
                position = index
                break
            }
        }
        return position
    }
}