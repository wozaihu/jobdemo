package com.example.jobdemo.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityRenameOrEditSignatureBinding
import com.example.jobdemo.util.closeKeyboard


class RenameOrEditSignature : AppCompatActivity() {
    var name: String? = null
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRenameOrEditSignatureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //设置获得焦点
        binding.edit.requestFocus()
        name = "王浩就是不得了"
        name?.let {
            binding.edit.setText(it)
            //设置光标到位置
            binding.edit.text?.let { it1 -> binding.edit.setSelection(it1.length) }
        }
        closeKeyboard(this)

        Glide.with(this)
            .load(getDrawable(R.mipmap.a1))
            .apply(RequestOptions.circleCropTransform()) // 使用 Glide 的圆形裁剪
            .into(binding.imageView)
    }
}