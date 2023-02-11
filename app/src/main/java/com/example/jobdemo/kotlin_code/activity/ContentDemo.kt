package com.example.jobdemo.kotlin_code.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

/**

 * @Author LYX

 * @Date 2023/2/10 11:51

 */
class ContentDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MySingleton.init(this)
    }

    //object修饰为单例类
    object MySingleton {
        var weakContext: WeakReference<Context>? = null
        fun init(context: Context) {
            this.weakContext = WeakReference(context)
        }
    }
}