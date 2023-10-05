package com.example.jobdemo.kotlin_code.activity

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Window
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityOftenSentenceBinding
import com.example.jobdemo.databinding.DialogRegisterInputCompanyBinding
import com.example.jobdemo.kotlin_code.bean.SentenceBean
import com.example.jobdemo.kotlin_code.dialog.BottomDialog
import com.example.jobdemo.kotlin_code.dialog.OftenSentenceDialog
import com.example.jobdemo.kotlin_code.utils.getDefaultValue
import com.example.jobdemo.util.CheckInstallsPermissionUtil
import com.example.jobdemo.util.ToastUtils
import com.example.jobdemo.util.Utils
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

        binding.btnWithInputDialog.setOnClickListener {
            val dialog = Dialog(this, R.style.CustomDialogWidth)
            dialog.setCanceledOnTouchOutside(false)
            val inputDialogBinding = DialogRegisterInputCompanyBinding.inflate(layoutInflater)
            dialog.setContentView(inputDialogBinding.root)
            inputDialogBinding.tvTitle.text = "请输入公司全称"
            inputDialogBinding.tvCancel.setOnClickListener {
                dialog.cancel()
            }
            inputDialogBinding.tvConfirm.setOnClickListener {
                val text = inputDialogBinding.etInput.text.toString()
                if (!TextUtils.isEmpty(text)) {
                    ToastUtils.shortToast(this, text)
                    dialog.cancel()
                }
            }
            dialog.window?.setDimAmount(0.2f)
            dialog.show()
        }

        binding.btnSwitchBtn.setOnClickListener {
            binding.btnShowDialog.isEnabled = !binding.btnShowDialog.isEnabled
            binding.btnShowBottomDialog.isEnabled = !binding.btnShowBottomDialog.isEnabled
            binding.btnShowFileClassConst.isEnabled = !binding.btnShowFileClassConst.isEnabled
        }

        binding.btnCheckInstallPermission.setOnClickListener {
            val isInstall = CheckInstallsPermissionUtil.isInstalls(WeakReference(this))
            if (isInstall) {
                val s =
                    StringBuilder(getString(R.string.canBeInstalled)).insert(2, "-").toString()
                ToastUtils.shortToast(this, s)
            }
        }

        binding.btnStartGpsString.setOnClickListener {
            Utils.openGPS(this)
        }
    }
}