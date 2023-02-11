package com.example.jobdemo.kotlin_code.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ItemImageTextBinding
import com.example.jobdemo.util.ToastUtils

/**

 * @Author LYX

 * @Date 2022/11/8 14:23

 */
class FruitRecycleAdapter(val context: Context, private val list: MutableList<String>) :
    RecyclerView.Adapter<FruitRecycleAdapter.ViewHolder>() {

    /**
     *
     * @property binding ItemImageTextBinding
     * @constructor 这样定义了ViewHolder
     */
    class ViewHolder(var binding: ItemImageTextBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * 参考链接：https://zhuanlan.zhihu.com/p/495402574
     * 定义了一个高级函数变量，后面可以用lambda或相同参数返回值的函数赋值
     * lateInit 表示延迟初始化
     * mListener变量名
     * （）括号中的为参数，不需要参数就是空括号（），有参数就直接写参数类型，如果有两个参数，一个为string，一个为int就是（String,Int）
     * ->后面的为返回值，Unit表示为空
     * 完整的相当于：mListener: (String) -> Unit={str:String-> 要执行的操作}
     * (String) -> Unit是函数类型，每个函数都有函数类型,如
    fun add(num1: Int, num2: Int): Int {
    return num1 + num2
    }
    这个方法的函数类型就是(Int, Int) -> Int，两个参数类型是int，返回值也是int
     */
    private lateinit var mListener: (String) -> Unit

    lateinit var tvOnclick: (Int, String) -> Unit

    /**
     * 0默认，1排序
     */
    private var type = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setType(i: Int) {
        this.type = i
        notifyDataSetChanged()
    }

    /**
     * 和Java一样，为变量赋值，这里需要一个lambda：{str:String->要执行的操作},或者一个相同类型的函数：fun 函数名（string：String）{要执行的操作}
     */
    fun setListener(listener: (String) -> Unit) {
        this.mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemImageTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder.binding.img.setImageResource(R.mipmap.a2)
        } else {
            holder.binding.img.setImageResource(R.mipmap.a3)
        }
        holder.binding.tvName.text = list[position]
        holder.binding.root.setOnClickListener {
            ToastUtils.shortToast(
                it.context,
                list[position]
            )
        }

        holder.binding.img.setOnClickListener {
            //相当于调用了一方法，需要一个参数，然后会执行函数体
            mListener(list[position])
        }

        holder.binding.tvName.setOnClickListener { tvOnclick(position, list[position]) }

        if (type == 0) {
            holder.binding.imgSort.visibility = View.GONE
        } else {
            holder.binding.imgSort.visibility = View.VISIBLE
        }


        holder.binding.imgSort.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val vibrator = context.getSystemService<Vibrator>()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(
                        VibrationEffect.createOneShot(
                            30,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    vibrator?.vibrate(30)
                }
            }
            return@setOnTouchListener true
        }

        holder.itemView.setOnLongClickListener {
            if (type == 0) {
                val alertDialog = AlertDialog.Builder(context)
                    .setMessage("确定删除？")
                    .setPositiveButton("确定") { dialog, _ ->
                        list.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, list.size - 1)
                        dialog.dismiss()
                    }.setNegativeButton("取消") { _, _ -> }
                    .create()
                alertDialog.show()
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.gray
                    )
                )
            }
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}