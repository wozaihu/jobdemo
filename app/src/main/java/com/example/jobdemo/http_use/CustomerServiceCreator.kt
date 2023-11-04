package com.example.jobdemo.http_use

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CustomerServiceCreator {
    private const val BASE_URL = "http://0.0.0.0:8080/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}