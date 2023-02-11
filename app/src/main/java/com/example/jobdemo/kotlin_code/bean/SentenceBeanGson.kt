package com.example.jobdemo.kotlin_code.bean


import com.google.gson.annotations.SerializedName

data class SentenceBeanGson(
    @SerializedName("message")
    val message: String, // 成功!
    @SerializedName("status")
    val status: String, // 1
    @SerializedName("userInfo")
    val userInfo: List<UserInfo>
) {
    data class UserInfo(
        @SerializedName("ID")
        val iD: Int, // 415708
        @SerializedName("SortId")
        val sortId: Int, // 0
        @SerializedName("WordText")
        val wordText: String // 虎牙疼
    )
}