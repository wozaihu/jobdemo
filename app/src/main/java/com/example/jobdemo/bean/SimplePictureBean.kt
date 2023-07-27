package com.example.jobdemo.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*index只用来表示单次请求时返回的排序*/
@Parcelize
data class SimplePictureBean(
    val index: Int,
    val path: String,
    val width: Int = 0,
    val height: Int = 0,
    val isFailed: Boolean = false
) : Parcelable
