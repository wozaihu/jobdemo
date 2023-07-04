package com.example.jobdemo.kotlin_code.activity

import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.rongcloud.rtc.utils.UUID22
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityOftenSentenceBinding
import com.example.jobdemo.databinding.AshowtoastBinding
import com.example.jobdemo.kotlin_code.bean.SentenceBean
import com.example.jobdemo.kotlin_code.dialog.BottomDialog
import com.example.jobdemo.kotlin_code.dialog.OftenSentenceDialog
import com.example.jobdemo.kotlin_code.utils.getDefaultValue
import com.example.jobdemo.util.CheckInstallsPermissionUtil
import com.example.jobdemo.util.ToastUtils
import java.lang.StringBuilder
import java.lang.ref.WeakReference

/**

 * @Author LYX

 * @Date 2022/12/30 17:42

 */
class KtOftenSentence : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOftenSentenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowDialog.setOnClickListener {
            OftenSentenceDialog().showNow(
                supportFragmentManager,
                "tag"
            )
        }

        binding.btnShowBottomDialog.setOnClickListener {
            BottomDialog().showNow(
                supportFragmentManager,
                "bottomDialog"
            )
        }

        binding.btnShowFileClassConst.setOnClickListener {
            ToastUtils.shortToast(this, getDefaultValue())
        }

        intent.getParcelableArrayListExtra<SentenceBean.UserInfo>("list")?.apply {
            ToastUtils.shortToast(this@KtOftenSentence, this[0].WordText)
        }

        intent.getParcelableExtra<SentenceBean>("bean")?.let {
            Log.d("常用语", "KtOftenSentence收到了intent传值-----${it.userInfo[0].WordText}")
            Log.d("常用语", "KtOftenSentence收到了intent传值-----${it.userInfo.size}")
        }

        binding.btnSwitchBtn.setOnClickListener {
            binding.btnShowDialog.isEnabled = !binding.btnShowDialog.isEnabled
            binding.btnShowBottomDialog.isEnabled = !binding.btnShowBottomDialog.isEnabled
            binding.btnShowFileClassConst.isEnabled = !binding.btnShowFileClassConst.isEnabled
        }

        binding.btnCheckInstallPermission.setOnClickListener {
          val isInstall= CheckInstallsPermissionUtil.isInstalls(WeakReference(this))
          if (isInstall){
              val s =
                  StringBuilder(getString(R.string.canBeInstalled)).insert(2, "-").toString()
              ToastUtils.shortToast(this,s)
          }
        }
    }
}