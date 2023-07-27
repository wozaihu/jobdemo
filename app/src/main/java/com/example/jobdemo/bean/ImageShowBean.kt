package com.example.jobdemo.bean


import com.google.gson.annotations.SerializedName

data class ImageShowBean(
    @SerializedName("adstar")
    val adstar: Int,
    @SerializedName("boxresult")
    val boxresult: Any,
    @SerializedName("ceg")
    val ceg: Int,
    @SerializedName("cn")
    val cn: Int,
    @SerializedName("cuben")
    val cuben: Int,
    @SerializedName("end")
    val end: Boolean,
    @SerializedName("gn")
    val gn: Int,
    @SerializedName("kn")
    val kn: Int,
    @SerializedName("lastindex")
    val lastindex: Int,
    @SerializedName("list")
    val list: List<Picture>,
    @SerializedName("manun")
    val manun: Int,
    @SerializedName("pc")
    val pc: Int,
    @SerializedName("pornn")
    val pornn: Int,
    @SerializedName("prevsn")
    val prevsn: Int,
    @SerializedName("ps")
    val ps: Int,
    @SerializedName("ran")
    val ran: Int,
    @SerializedName("ras")
    val ras: Int,
    @SerializedName("sid")
    val sid: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("wordguess")
    val wordguess: Any
) {
    data class Picture(
        @SerializedName("color")
        val color: Int,
        @SerializedName("comm_purl")
        val commPurl: String,
        @SerializedName("downurl")
        val downurl: Boolean,
        @SerializedName("downurl_true")
        val downurlTrue: String,
        @SerializedName("dsptime")
        val dsptime: String,
        @SerializedName("dspurl")
        val dspurl: String,
        @SerializedName("fixedSize")
        val fixedSize: Boolean,
        @SerializedName("fnum")
        val fnum: String,
        @SerializedName("grpcnt")
        val grpcnt: Boolean,
        @SerializedName("grpmd5")
        val grpmd5: Boolean,
        @SerializedName("height")
        val height: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("img")
        val img: String,
        @SerializedName("imgkey")
        val imgkey: String,
        @SerializedName("imgsize")
        val imgsize: String,
        @SerializedName("imgtype")
        val imgtype: String,
        @SerializedName("index")
        val index: Int,
        @SerializedName("key")
        val key: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("litetitle")
        val litetitle: String,
        @SerializedName("qqface_down_url")
        val qqfaceDownUrl: Boolean,
        @SerializedName("site")
        val site: String,
        @SerializedName("source")
        val source: Int,
        @SerializedName("src")
        val src: String,
        @SerializedName("thumb")
        val thumb: String,
        @SerializedName("_thumb")
        val _thumb: String,
        @SerializedName("thumb_bak")
        val thumbBak: String,
        @SerializedName("_thumb_bak")
        val _thumbBak: String,
        @SerializedName("thumbHeight")
        val thumbHeight: Int,
        @SerializedName("thumbWidth")
        val thumbWidth: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: Int,
        @SerializedName("width")
        val width: String
    )
}