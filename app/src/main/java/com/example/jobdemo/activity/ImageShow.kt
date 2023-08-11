package com.example.jobdemo.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.jobdemo.R
import com.example.jobdemo.adapter.ImageShowAdapter
import com.example.jobdemo.base.RetrofitInterface
import com.example.jobdemo.bean.SimplePictureBean
import com.example.jobdemo.constants.Api
import com.example.jobdemo.databinding.ActivityImageShowBinding
import com.example.jobdemo.util.LogUtil
import com.example.jobdemo.widget.GridSpaceItemDecoration
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.util.SmartGlideImageLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


class ImageShow : AppCompatActivity() {
    private val list: MutableList<SimplePictureBean> = ArrayList()
    private lateinit var adapter: ImageShowAdapter
    private lateinit var binding: ActivityImageShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        binding.rv.layoutManager = layoutManager
        binding.rv.addItemDecoration(GridSpaceItemDecoration(10, true).setEndFromSize(0))
        adapter = ImageShowAdapter(this, list) { imageView, index ->
            val pathList: List<String> = list.map { it.path }
            XPopup.Builder(this)
                .isTouchThrough(true)
                .asImageViewer(
                    imageView, index, pathList,
                    false, true, -1, -1, ConvertUtils.dp2px(10f), true,
                    Color.rgb(32, 36, 46),
                    { popupView, position ->
                        //要移动一下rv，不然大图浏览到rv未显示的图片时，itemView会为空，那退出大图浏览时收缩动画就只会回到最后显示的一张图那，不能正确显示
                        binding.rv.scrollToPosition(position)
                        val itemView = layoutManager.findViewByPosition(position)
                        itemView?.let {
                            val currentImageView = itemView.findViewById<ImageView>(R.id.iv_picture)
                            popupView.updateSrcView(currentImageView)
                        }
                    }, SmartGlideImageLoader(true, R.mipmap.ic_launcher), null
                )
                .show()
        }
        binding.rv.adapter = adapter
        getImage("汽车", 0)
    }


    @SuppressLint("CheckResult")
    fun getImage(name: String, sn: Int) {
        val httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时时间
            .readTimeout(30, TimeUnit.SECONDS)
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.SEARCH_IMAGE_BY360)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClientBuilder.build())
            .build()
        val retrofitInterface = retrofit.create(RetrofitInterface::class.java)
        retrofitInterface.getImage(name, sn)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ imageShowBean ->
                val latch = CountDownLatch(imageShowBean.list.size)
                val tempList = mutableListOf<SimplePictureBean>()
                LogUtil.showD("获取图片地址成功，总数：${imageShowBean.list.size}")
                // 在这里处理返回的数据
                imageShowBean.list.forEachIndexed { index, picture ->
                    getAspectRatio(picture.img, index) { bean ->
                        latch.countDown()
                        tempList.add(bean)
                        if (latch.count == 0L) {
                            tempList.sortBy { it.index }
                            val oldCount = adapter.itemCount
                            list.addAll(tempList)
                            runOnUiThread {
                                adapter.notifyItemRangeInserted(oldCount, tempList.size)
                            }
                            LogUtil.showD("获取图片都获得宽高了最后一张为：${bean.index}")
                        }
                    }
                }

            }, { throwable ->
                LogUtil.showD("获取图片地址异常---${throwable.message}")
            })
    }


    private fun getAspectRatio(
        imageUrl: String,
        index: Int,
        callback: (SimplePictureBean) -> Unit
    ) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .apply(RequestOptions().override(Target.SIZE_ORIGINAL))
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    LogUtil.showD("第${index}张图片，地址：${imageUrl}，获取图片宽高失败：${e?.message}")
                    callback(
                        SimplePictureBean(index, imageUrl, isFailed = true)
                    )
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    val width = resource?.width ?: 0
                    val height = resource?.height ?: 0
                    // 在这里可以使用获得的宽高比例进行后续操作
                    LogUtil.showD("获取图片宽高成功，index：${index},地址：${imageUrl},宽：${width},高：${height}")
                    callback(SimplePictureBean(index, imageUrl, width, height, false))
                    return false
                }
            })
            .submit()
    }
}