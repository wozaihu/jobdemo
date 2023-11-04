package com.example.jobdemo.http_use

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobdemo.R
import com.example.jobdemo.bean.CustomerList
import com.example.jobdemo.databinding.ActivityHttpUseDemoBinding
import com.example.jobdemo.util.LogUtil
import com.example.jobdemo.util.RandomNameUtil
import com.example.jobdemo.util.RandomUtils
import com.example.jobdemo.util.ToastUtils
import com.google.gson.Gson
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter
import com.yuyh.easyadapter.recyclerview.EasyRVHolder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


/**
 *电脑开代理，手机链接电脑后才能访问http://0.0.0.0:8080
 */

class HttpUseDemo : AppCompatActivity() {
    private val tag = "HttpUseDemo打印"
    private lateinit var binding: ActivityHttpUseDemoBinding
    private val list = mutableListOf<CustomerList.Customer>()
    private lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpUseDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnHttp.setOnClickListener { httpUse() }
        binding.btnOkHttp.setOnClickListener { okHttpUse() }
        binding.btnOkPost.setOnClickListener { commitCustomer() }
        binding.btnQuery.setOnClickListener { queryCustomer() }
        binding.rvCustomer.layoutManager = LinearLayoutManager(this)
        binding.rvCustomer.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = RecyclerViewAdapter(this, list, R.layout.item_customer)
        binding.rvCustomer.adapter = adapter
    }

    private fun queryCustomer() {
        if (!binding.etv.text.isNullOrEmpty()) {
            getCustomer(binding.etv.text.toString())
        }
    }

    private fun getCustomer(id: String) {
        val customerService = CustomerServiceCreator.create<CustomerService>()
        customerService.getSingleCustomer(id)
            .enqueue(object : retrofit2.Callback<String> {
                override fun onResponse(
                    call: retrofit2.Call<String>,
                    response: retrofit2.Response<String>
                ) {
                    LogUtil.showD(tag, response.body())
                }

                override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                    LogUtil.showD(tag, t.message)
                }
            })

    }

    @SuppressLint("SetTextI18n")
    private fun commitCustomer() {
        thread {
            //创建请求实例，创建客户端实例，调用newCall(request).execute执行，得到respond
            val lb = CustomerList.Customer(
                "001",
                RandomNameUtil.randomName(true, 3),
                RandomUtils.getInstance().randomAge.toString()
            )

            val request = Request.Builder()
                .url("http://0.0.0.0:8080/customer")
                .post(Gson().toJson(lb).toRequestBody("application/json".toMediaTypeOrNull()))
                .build()
            OkHttpClient().newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    LogUtil.showD(tag, e.message)
                }

                override fun onResponse(call: Call, response: Response) {
                    val string = response.body?.string()
                    runOnUiThread {
                        ToastUtils.shortToast(this@HttpUseDemo, string)
                    }
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun httpUse() {
        thread {
            val respond = StringBuilder()
            val customerURLConnection =
                URL("http://0.0.0.0:8080/customer").openConnection() as HttpURLConnection
            customerURLConnection.connectTimeout = 10000
            customerURLConnection.readTimeout = 10000
            val reader =
                BufferedReader(InputStreamReader(customerURLConnection.inputStream))
            reader.use {
                reader.forEachLine {
                    respond.append(it)
                }
            }
            customerURLConnection.disconnect()
            runOnUiThread {
                if (respond.toString().isEmpty()) {
                    ToastUtils.shortToast(this, "httpUse请求到的数据为空")
                } else if (respond.toString() == "No customers found") {
                    ToastUtils.shortToast(this, "No customers found")
                } else {
                    setDate(respond.toString())
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun okHttpUse() {
        thread {
            //创建请求实例，创建客户端实例，调用newCall(request).execute执行，得到respond
            val request = Request.Builder().url("http://0.0.0.0:8080/customer").build()
            val response = OkHttpClient().newCall(request).execute()
            //注意这段代码会阻塞线程，所以也要在工作线程执行
            val string = response.body?.string()
            runOnUiThread {
                if (string.isNullOrEmpty()) {
                    ToastUtils.shortToast(this, "okHttpUse请求到的数据为空")
                } else if (string == "No customers found") {
                    ToastUtils.shortToast(this, "No customers found")
                } else {
                    setDate(string)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setDate(string: String) {
        val customerList = Gson().fromJson(string, CustomerList::class.java)
        list.clear()
        list.addAll(customerList)
        adapter.notifyDataSetChanged()
    }

    class RecyclerViewAdapter(context: Context, list: List<CustomerList.Customer>, layoutIds: Int) :
        EasyRVAdapter<CustomerList.Customer>(context, list, layoutIds) {
        override fun onBindData(
            viewHolder: EasyRVHolder,
            position: Int,
            item: CustomerList.Customer
        ) {
            viewHolder.setText(R.id.tv_id, "ID：${item.id}")
            viewHolder.setText(R.id.tv_name, "姓名：${item.name}")
            viewHolder.setText(R.id.tv_age, "年龄：${item.age}")
        }
    }
}