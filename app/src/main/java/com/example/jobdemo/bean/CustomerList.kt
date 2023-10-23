package com.example.jobdemo.bean


import com.google.gson.annotations.SerializedName

class CustomerList : ArrayList<CustomerList.Customer>() {
    data class Customer(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("age")
        val age: String
    )
}