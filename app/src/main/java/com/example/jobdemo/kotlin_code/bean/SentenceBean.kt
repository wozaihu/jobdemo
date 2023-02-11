package com.example.jobdemo.kotlin_code.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.collections.ArrayList

@Parcelize
data class SentenceBean(
    val message: String, // 成功!
    val status: String, // 1
    val userInfo: ArrayList<UserInfo>
) : Parcelable {
    @Parcelize
    data class UserInfo(
        val ID: Int, // 415557
        val SortId: Int, // 1
        var WordText: String // 忙碌中，请稍后联系
    ) : Parcelable
}