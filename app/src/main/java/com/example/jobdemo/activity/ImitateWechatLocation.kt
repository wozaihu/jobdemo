package com.example.jobdemo.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.BitmapDescriptorFactory
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.Marker
import com.baidu.mapapi.map.MarkerOptions
import com.baidu.mapapi.map.TextureMapView
import com.baidu.mapapi.model.LatLng
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityImitateWechatLocationBinding
import com.example.jobdemo.util.DensityUtil
import com.example.jobdemo.util.MeasureUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.gyf.immersionbar.ImmersionBar


class ImitateWechatLocation : AppCompatActivity() {

    private lateinit var binding: ActivityImitateWechatLocationBinding
    private var statusBarHeight: Int = 0
    private var peekHeight: Int = 0
    private var screenHeight: Int = 0
    private lateinit var mMapView: TextureMapView
    private lateinit var mBaiduMap: BaiduMap
    private lateinit var mMarkerA: Marker
    private val bitmapA = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarHeight = ImmersionBar.getStatusBarHeight(this)
        screenHeight = MeasureUtils.getScreenHeight(this@ImitateWechatLocation)
        binding = ActivityImitateWechatLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ImmersionBar.with(this).titleBar(binding.flToolbar).navigationBarColor(R.color.transparent)
            .statusBarDarkFont(true).init()
        binding.btnCancel.setOnClickListener { finish() }
        val behavior = BottomSheetBehavior.from(binding.bottomDrawer)
        peekHeight = behavior.peekHeight
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // 检测底部拖动视图的状态变化
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    setMapViewHeight(200f)
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    setMapViewHeight(400f)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//         根据底部拖动视图的偏移量来调整mapView的高度,往下拖可能后绘制地图view，导致连接处有黑条
                val offsetHeight = peekHeight + peekHeight * slideOffset
                binding.flMapView.layoutParams.height =
                    (screenHeight + statusBarHeight - offsetHeight).toInt()
                binding.flMapView.requestLayout()
            }
        })


        mMapView = binding.mapView
        mBaiduMap = mMapView.map
        mBaiduMap.setOnMapLoadedCallback { initOverlay() }
    }


    private fun initOverlay() {
        // add marker overlay
        val latLngA = LatLng(40.023537, 116.289429)
        val markerOptionsA = MarkerOptions().position(latLngA).icon(bitmapA)
        mMarkerA = mBaiduMap.addOverlay(markerOptionsA) as Marker
        val mapStatusUpdate = MapStatusUpdateFactory.zoomTo(12.0f)
        mBaiduMap.setMapStatus(mapStatusUpdate)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            setMapViewHeight(200f)
        }
    }


    private fun setMapViewHeight(bottomHeight: Float) {
        // 获取屏幕高度
        // 获取bottomDrawer的高度
        val bottomDrawerHeight = DensityUtil.dip2px(this, bottomHeight)
        // 修改flMapView的布局参数
        val layoutParams = binding.flMapView.layoutParams
        layoutParams.height =
            statusBarHeight + screenHeight - bottomDrawerHeight
        binding.flMapView.layoutParams = layoutParams
    }
}
