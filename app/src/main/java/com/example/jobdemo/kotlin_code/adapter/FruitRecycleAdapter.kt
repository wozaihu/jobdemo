package com.example.jobdemo.kotlin_code.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ItemImageTextBinding
import com.example.jobdemo.util.ToastUtils

/**

 * @Author LYX

 * @Date 2022/11/8 14:23

 */
class FruitRecycleAdapter(private val data: MutableList<String>) :
    RecyclerView.Adapter<FruitRecycleAdapter.ViewHolder>() {

    /**
     *
     * @property binding ItemImageTextBinding
     * @constructor 这样定义了ViewHolder
     */
    inner class ViewHolder(var binding: ItemImageTextBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * 定义了一个lambda（匿名函数，这是一个方法）类型的变量
     * lateInit 表示延迟初始化
     * mListener变量名
     * （）括号中的为参数，不需要参数就是空括号（），有参数就直接写参数类型，如果有两个参数，一个为string，一个为int就是（String,Int）
     * ->后面的为返回值，Unit表示为空
     * 完整的相当于：mListener: (String) -> Unit={str:String-> 要执行的操作}
     */
    private lateinit var mListener: (String) -> Unit

    /**
     * 和Java一样，为变量赋值，这里需要一个lambda
     */
    fun setListener(listener: (String) -> Unit) {
        this.mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemImageTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder.binding.img.setImageResource(R.mipmap.a2)
        } else {
            holder.binding.img.setImageResource(R.mipmap.a3)
        }
        holder.binding.tvName.text = data[position]
        holder.binding.root.setOnClickListener {
            ToastUtils.shortToast(
                it.context,
                "点击了第${position}行"
            )
        }

        holder.binding.img.setOnClickListener {
            //相当于调用了一方法，需要一个参数，然后会执行函数体
            mListener(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}