package com.example.jobdemo.api

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ImageApi {
    private val client = OkHttpClient()
    interface ApiCallback {
        fun onSuccess(responseData: String)
        fun onFailure(error: String)
    }
    fun requestData(p: Int, callback: ApiCallback) {
        val url = "https://bed.mydog.buzz/p?p=${p}"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(e.message ?: "Unknown error occurred")
            }
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                if (response.isSuccessful && responseData != null) {
                    callback.onSuccess(responseData)
                } else {
                    callback.onFailure("Request failed")
                }
            }
        })
    }
}