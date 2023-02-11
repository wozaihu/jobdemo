package com.example.jobdemo.kotlin_code.utils

import android.content.Context
import android.text.TextUtils
import com.example.jobdemo.R
import com.example.jobdemo.util.SpUtil
import com.example.jobdemo.util.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**

 * @Author LYX

 * @Date 2023/1/5 17:37
 * 常用语工具类

 */


/**
 *获得常用语集合
 * @param context Context 上下文
 */
fun getSentenceList(context: Context): MutableList<String> {
    val sentenceKey = "sentence${Utils.getUserId(context)}"
    val param = SpUtil.getInstance().getParam(sentenceKey, "") as String
    if (!TextUtils.isEmpty(param)) {
        return Gson().fromJson(
            param,
            object : TypeToken<MutableList<String>>() {}.type
        )
    } else {
        val defaultSentenceArray = if (Utils.getUserType(context)) {
            context.resources.getStringArray(R.array.companyOftenSentence)
        } else {
            context.resources.getStringArray(R.array.normalOftenSentence)
        }
        val list = mutableListOf<String>()
        list.addAll(defaultSentenceArray)
        SpUtil.getInstance().getParam(sentenceKey, Gson().toJson(list))
        return list
    }
}

/**
 *添加的一条常用语
 * @param context Context 上下文
 * @param str: String 要添加的常用语
 *@return Boolean 是否有添加
 */
fun addSentence(context: Context, str: String): Boolean {
    val sentenceKey = "sentence${Utils.getUserId(context)}"
    val param = SpUtil.getInstance().getParam(sentenceKey, "") as String
    if (!TextUtils.isEmpty(param)) {
        val type = object : TypeToken<MutableList<String>>() {}.type
        val customList = Gson().fromJson<MutableList<String>>(param, type)
        if (!customList.contains(str)) {
            customList.add(0, str)
            SpUtil.getInstance().getParam(sentenceKey, Gson().toJson(customList))
            return true
        }
        return false
    } else {
        //按业务顺序来说永远不会走这里
        val defaultSentenceArray = if (Utils.getUserType(context)) {
            context.resources.getStringArray(R.array.companyOftenSentence)
        } else {
            context.resources.getStringArray(R.array.normalOftenSentence)
        }
        val list = mutableListOf<String>()
        list.add(str)
        list.addAll(defaultSentenceArray)
        val json = Gson().toJson(list)
        SpUtil.getInstance().getParam(sentenceKey, json)
        return true
    }
}

/**
 *保存最新的常用语集合
 * @param context Context 上下文
 * @param list MutableList<String> 要保存的集合（在外面判断size>0在调此方法）
 */
fun saveSentenceList(context: Context, list: MutableList<String>) {
    val sentenceKey = "sentence${Utils.getUserId(context)}"
    val json = Gson().toJson(list)
    SpUtil.getInstance().getParam(sentenceKey, json)
}

/**
 * 修改常用语
 * @param context Context 上下文
 * @param oldStr String 旧的常用语
 * @param newStr String 新的常用语
 */
fun reviseSentence(context: Context, oldStr: String, newStr: String) {
    val list = getSentenceList(context)
    val i = list.indexOf(oldStr)
    if (i != -1) {
        list[i] = newStr
        saveSentenceList(context, list)
    }
}

/**
 * 删除常用语
 * @param context Context 上下文
 * @param sentence String 要删除的常用语
 */
fun deleteSentence(context: Context, sentence: String) {
    val list = getSentenceList(context)
    if (list.contains(sentence)) {
        list.remove(sentence)
        saveSentenceList(context, list)
    }
}