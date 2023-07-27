package com.example.jobdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jobdemo.R
import com.example.jobdemo.bean.SimplePictureBean
import com.example.jobdemo.databinding.ItemPictureBinding
import com.example.jobdemo.util.MeasureUtils

class ImageShowAdapter(
    private val context: Context,
    private val imageList: MutableList<SimplePictureBean>,
    val itemCallback: (ImageView, Int) -> Unit
) :
    RecyclerView.Adapter<ImageShowAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val pictureBean = imageList[position]
        val layoutParam = holder.binding.ivPicture.layoutParams
        if (pictureBean.isFailed) {
            val drawable = ContextCompat.getDrawable(context, R.drawable.loading_picture_failed)
            val failedHeight = drawable?.intrinsicHeight ?: 80
            if (failedHeight == -1) {
                layoutParam.height = 80
            } else {
                layoutParam.height = failedHeight
            }
            holder.binding.ivPicture.layoutParams = layoutParam
            Glide.with(context).load(R.drawable.loading_picture_failed)
                .into(holder.binding.ivPicture)
        } else {
            val reallyWidth = (MeasureUtils.getScreenWidth(context) - 30) / 2
            val reallyHeight = pictureBean.height * reallyWidth / pictureBean.width
            layoutParam.height = reallyHeight
            holder.binding.ivPicture.layoutParams = layoutParam
            Glide.with(context).load(pictureBean.path).into(holder.binding.ivPicture)
        }
        holder.itemView.setOnClickListener { itemCallback(holder.binding.ivPicture, position) }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ImageViewHolder(val binding: ItemPictureBinding) :
        RecyclerView.ViewHolder(binding.root)
}