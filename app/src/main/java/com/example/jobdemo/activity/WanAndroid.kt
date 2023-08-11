package com.example.jobdemo.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.R
import com.example.jobdemo.bean.WanAndroidArticleBean
import com.example.jobdemo.databinding.ActivityWanAndoridBinding
import com.example.jobdemo.util.ToastUtils
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.yuyh.easyadapter.abslistview.EasyLVAdapter
import com.yuyh.easyadapter.abslistview.EasyLVHolder


class WanAndroid : AppCompatActivity() {
    private val list = mutableListOf<WanAndroidArticleBean.Data.Data>()
    private lateinit var adapter: ListViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWanAndoridBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ListViewAdapter(this, list, R.layout.item_wan)
        binding.lv.adapter = adapter
        getData()

    }


    fun getData() {
        val url = "https://www.wanandroid.com/article/list/0/json"

        OkGo.get<String>(url)
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>?) {
                    response?.body().let {
                        Log.d("玩安卓", "onSuccess: $it")
                        val bean = Gson().fromJson(it, WanAndroidArticleBean::class.java)
                        list.addAll(bean.data.datas)
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onError(response: Response<String>?) {
                    val errorMsg = response?.exception?.message
                    // 处理请求失败的情况
                    ToastUtils.shortToast(this@WanAndroid, errorMsg)
                }
            })
    }


    class ListViewAdapter(
        context: Context?,
        list: List<WanAndroidArticleBean.Data.Data>,
        layoutIds: Int
    ) :
        EasyLVAdapter<WanAndroidArticleBean.Data.Data>(context, list, layoutIds) {
        override fun convert(
            holder: EasyLVHolder,
            position: Int,
            bean: WanAndroidArticleBean.Data.Data
        ) {

            holder.setText(R.id.tv_title, bean.title)
                .setText(R.id.tv_author, bean.shareUser)
        }
    }
}
