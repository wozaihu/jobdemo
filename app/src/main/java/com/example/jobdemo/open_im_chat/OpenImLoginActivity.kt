package com.example.jobdemo.open_im_chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.databinding.ActivityOpenImLoginBinding
import io.openim.android.sdk.OpenIMClient
import io.openim.android.sdk.listener.OnBase


class OpenImLoginActivity : AppCompatActivity() {
    val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOiI1NjMwMTk1NzQwIiwiUGxhdGZvcm1JRCI6NCwiZXhwIjoxNzI3MTcwMDUzLCJuYmYiOjE3MTkzOTM3NTMsImlhdCI6MTcxOTM5NDA1M30.56M-HEudFFYkUvLHO6zkIgm56WcPvEKzUDFsyzzypWE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOpenImLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            OpenIMClient.getInstance().login(object : OnBase<String?> {
                override fun onError(code: Int, error: String) {
                    binding.tvLoginStatus.text = "登录失败${error}"
                }

                override fun onSuccess(data: String?) {
                    binding.tvLoginStatus.text = "登录成功${data}"
                }
            }, "5630195740", token)
        }
    }
}