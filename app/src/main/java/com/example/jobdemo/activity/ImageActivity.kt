package com.example.jobdemo.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.jobdemo.R
import com.example.jobdemo.bean.SimplePictureBean
import com.example.jobdemo.fragment.ImageFragment

class ImageActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var imageUrls: ArrayList<SimplePictureBean>
    private var currentPosition: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        viewPager = findViewById(R.id.viewPager)
        imageUrls = intent.getParcelableArrayListExtra("imageUrls") ?: arrayListOf()
        currentPosition = intent.getIntExtra("position", 0)
        val adapter = ImagePagerAdapter()
        viewPager.adapter = adapter
        viewPager.setCurrentItem(currentPosition, false)
        val textViewIndex: TextView = findViewById(R.id.textViewIndex)
        textViewIndex.text = "${currentPosition + 1}/${imageUrls.size}"
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                textViewIndex.text = "${position + 1}/${imageUrls.size}"
            }
        })
    }

    private inner class ImagePagerAdapter : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = imageUrls.size
        override fun createFragment(position: Int): ImageFragment {
            return ImageFragment.newInstance(imageUrls[position].path)
        }
    }
}