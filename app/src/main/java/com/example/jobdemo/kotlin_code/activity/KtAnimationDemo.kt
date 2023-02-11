package com.example.jobdemo.kotlin_code.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.databinding.ActivityKtAnimationDemoBinding
import com.example.jobdemo.util.ToastUtils


/**

 * @Author LYX

 * @Date 2023/2/8 14:31

 */
class KtAnimationDemo : AppCompatActivity() {
    lateinit var binding: ActivityKtAnimationDemoBinding
    private var tvCancelWidth = 0
    private var etOneWidth = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKtAnimationDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnChangeTwo.setOnClickListener {
            //监听动画运行时间改变
            //byUpdateChange()

            //属性动画直接改变
            byPropertyChange()
        }

        binding.btnChangeCancel.setOnClickListener {
            binding.tvCancel.clearAnimation()
            if (binding.tvCancel.width == 0) {
                ToastUtils.shortToast(this, "显示")
                ObjectAnimator.ofInt(binding.tvCancel, "width", 0, tvCancelWidth).apply {
                    duration = 500
                }.start()
            } else {
                ToastUtils.shortToast(this, "隐藏")
                ObjectAnimator.ofInt(binding.tvCancel, "width", binding.tvCancel.width, 0)
                    .setDuration(500).start()
            }
        }

        binding.btnChangeThree.setOnClickListener {
            val viewWrapper = ViewWrapper(binding.etThree)
            ObjectAnimator.ofInt(viewWrapper, "width", binding.etThree.width, binding.etOne.width)
                .setDuration(1000).start()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (etOneWidth == 0) {
            tvCancelWidth = binding.tvCancel.width
            etOneWidth = binding.etOne.width
            Log.d("动画", "tvCancelWidth:${tvCancelWidth},etOneWidth:${etOneWidth} ")
        }
    }

    private fun byPropertyChange() {
        val viewWrapper = ViewWrapper(binding.etTwo)
        ObjectAnimator.ofInt(viewWrapper, "width", binding.etTwo.width, binding.etOne.width)
            .setDuration(3000).start()
    }


    private fun byUpdateChange() {
        ValueAnimator.ofInt(binding.etTwo.width, binding.etOne.width).apply {
            duration = 3000
            startDelay = 500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addUpdateListener {
                Log.d("动画", "实时数值: ${it.animatedValue as Int}")
                Log.d("动画", "etTwo宽: ${binding.etTwo.width}")
                binding.etTwo.layoutParams.width = it.animatedValue as Int
                binding.etTwo.requestLayout()
            }
        }.start()
    }

    @Keep
    private class ViewWrapper(target: View) {
        private val rView: View
        var width: Int
            get() = rView.layoutParams.width
            set(width) {
                rView.layoutParams.width = width
                rView.requestLayout()
            }
        var height: Int
            get() = rView.layoutParams.height
            set(height) {
                rView.layoutParams.height = height
                rView.requestLayout()
            }

        init {
            rView = target
        }
    }
}