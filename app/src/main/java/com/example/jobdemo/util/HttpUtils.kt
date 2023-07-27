package com.example.jobdemo.util

import android.os.Handler
import android.os.Looper
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.IOException
object HttpUtils {
    private val client = OkHttpClient()
    private val uiHandler = Handler(Looper.getMainLooper())
    fun getRequest(url: String, params: Map<String, String>?, callback: Callback) {
        val httpBuilder = url.toHttpUrlOrNull()!!.newBuilder()
        params?.forEach { (key, value) ->
            httpBuilder.addQueryParameter(key, value)
        }
        val request = Request.Builder()
            .url(httpBuilder.build())
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                uiHandler.post { callback.onFailure(call, e) }
            }
            override fun onResponse(call: Call, response: Response) {
                uiHandler.post {
                    try {
                        callback.onResponse(call, response)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }
    fun postRequest(url: String, params: Map<String, String>?, callback: Callback) {
        val formBuilder = FormBody.Builder()
        params?.forEach { (key, value) ->
            formBuilder.add(key, value)
        }
        val requestBody = formBuilder.build()
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                uiHandler.post { callback.onFailure(call, e) }
            }
            override fun onResponse(call: Call, response: Response) {
                uiHandler.post {
                    try {
                        callback.onResponse(call, response)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}