package com.example.jobdemo.http_use

import com.example.jobdemo.bean.CustomerList
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface CustomerService {
    @GET("customer/{id}")
    fun getSingleCustomer(@Path("id") id: String): Call<String>

    @DELETE("customer/{id}")
    fun deleteCustomer(@Path("id") id: String): Call<String>
}