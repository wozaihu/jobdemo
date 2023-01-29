package com.example.jobdemo.kotlin_code.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ScaleDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityKtDrawableDemoBinding


class KtDrawableDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityKtDrawableDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawable = ContextCompat.getDrawable(this, R.drawable.banner1)
        val sd2 = ScaleDrawable(drawable, Gravity.START, 0.5f, 0.0f)
        binding.textView1.background = sd2
        binding.textView1.background.level = 5000

        binding.imageView2.drawable.level = 1

        val sd3 = ScaleDrawable(drawable, Gravity.START, 0.2f, 0.0f)
        binding.imageView3.setImageDrawable(sd3)
        binding.imageView3.drawable.level = 1

        val drawable4 = ColorDrawable(Color.GREEN)
        val sd4 = ScaleDrawable(drawable4, Gravity.START, 0.7f, 0.0f)
        binding.imageView4.setImageDrawable(sd4)
        binding.imageView4.drawable.level = 1

        //注意如果imageview设置图片时，使用的是src就用 binding.imgClipDrawable.drawable获取，设置的是背景就用背景获取
        binding.imgClipDrawable.background.level = 10000
        binding.imgLevelListDrawable.background.level = 1
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvProgress.text = progress.toString()
                //progress要转成double，不然两个int相除为0.几，默认转型为int会为0，那*10000也为0，就不会显示图片了
                //0表示完全剪裁，10000表示不剪裁，8000表示剪裁20%
                binding.imgClipDrawable.background.level =
                    (progress.toDouble() / 100 * 10000).toInt()
                binding.imgLevelListDrawable.background.level =
                    (progress.toDouble() / 100 * 10000).toInt()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.btnStartTransition.setOnClickListener {
            (binding.imgTransitionDrawable.background as TransitionDrawable).startTransition(3000)
        }
    }
}