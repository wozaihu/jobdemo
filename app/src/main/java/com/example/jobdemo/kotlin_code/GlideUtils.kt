package com.example.jobdemo.kotlin_code

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.jobdemo.util.MeasureUtils
import java.util.*

class GlideUtils {

    //请求头 ,不需要可以不加
    var url: String? = null;
    var mContext: Context? = null

    companion object {
        //重要 图片的宽高的缓存，后面会讲
        @JvmStatic
        var imageSize: HashMap<Int, ImageSize> = hashMapOf()

        private var glideUtils: GlideUtils? = null

        @JvmStatic
        fun cleanImageSize() {
            imageSize?.clear()
        }

        @JvmStatic
        fun getGlide(): GlideUtils {
            if (glideUtils == null) {
                glideUtils = GlideUtils()
            }
            return glideUtils!!
        }
    }

    fun load(url: String): GlideUtils {
        this.url = url
        return glideUtils!!
    }

    fun with(context: Context): GlideUtils {
        this.mContext = context
        return glideUtils!!
    }

    //主要方法：
    fun into(view: ImageView, position: Int) {
        val glideUrl = GlideUrl(url, LazyHeaders.Builder()
                .build())

        Glide.with(mContext!!).asBitmap().load(glideUrl).listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: Bitmap, model: Any, target: Target<Bitmap>, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                //拿到图片的宽和高
                var width = resource.width
                var height = resource.height
                Log.d("原始宽高", width.toString() + "---" + height)
                //拿到当前屏幕的宽度的一半  如果是3列就除以3
                var screenWidthPx = MeasureUtils.getScreenWidth()!! / 2
                //通过宽高比例动态计算高度,使图片撑满屏幕
                height *= (width / screenWidthPx)
                //设置图片的宽高
                val params = view.layoutParams
                //将图片的宽高放入hashmap缓存,下一次加载图片从缓存中取出宽高
                if (!imageSize.containsKey(position)) {
                    //设置图片的宽高
                    params?.width = width
                    params?.height = height
                    view.layoutParams = params
                    //存入缓存
                    imageSize[position] = ImageSize(width, height)
                    Log.d("图片的宽高", width.toString() + "---" + height)
                }
                return false
            }
        }).into(view)
    }
}