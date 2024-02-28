package com.example.jobdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobdemo.base.BaseActivity
import com.example.jobdemo.databinding.ActivityMainBinding
import com.example.jobdemo.util.ClassUtils
import com.example.jobdemo.util.SpUtil
import com.example.jobdemo.util.ToastUtils
import com.example.jobdemo.util.UserItemDecoration
import com.umeng.commonsdk.UMConfigure
import io.rong.callkit.util.SPUtils

/**
 * 主页
 *
 * @author Administrator
 */
class MainActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //友盟正式初始化，冷启动时配置过key和通道了，这里不用在设置
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val classList = ClassUtils.getActivitiesClass(applicationContext, packageName)
        classList.sortBy { it.allInitial }
        val adapter = MainActivityAdapter(classList)
        binding.rvDemoInstance.addItemDecoration(UserItemDecoration())
        binding.rvDemoInstance.layoutManager = LinearLayoutManager(this)
        binding.rvDemoInstance.adapter = adapter

        binding.barList.setOnLetterChangeListener { letter ->
            val position: Int = adapter.getSortLettersFirstPosition(letter)
            if (position != -1) {
                if (binding.rvDemoInstance.layoutManager is LinearLayoutManager) {
                    (binding.rvDemoInstance.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                        position,
                        0
                    )
                }
            }
        }
    }

}