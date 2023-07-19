package com.example.jobdemo.activity

import android.graphics.Point
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.animation.Animation
import com.baidu.mapapi.animation.Transformation
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener
import com.baidu.mapapi.map.BitmapDescriptor
import com.baidu.mapapi.map.BitmapDescriptorFactory
import com.baidu.mapapi.map.MapStatus
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.Marker
import com.baidu.mapapi.map.MarkerOptions
import com.baidu.mapapi.map.OverlayOptions
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.search.core.SearchResult
import com.baidu.mapapi.search.geocode.GeoCodeResult
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener
import com.baidu.mapapi.search.poi.PoiDetailResult
import com.baidu.mapapi.search.poi.PoiDetailSearchResult
import com.baidu.mapapi.search.poi.PoiIndoorResult
import com.baidu.mapapi.search.poi.PoiNearbySearchOption
import com.baidu.mapapi.search.poi.PoiResult
import com.baidu.mapapi.search.poi.PoiSearch
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityChatLocationBinding
import com.example.jobdemo.util.LogUtil

/*
    定位到当前地址，并获得附近poi显示，移动地图后刷新poi列表，搜索也刷新
 */
class ChatLocation : AppCompatActivity(), OnMapStatusChangeListener, OnGetGeoCoderResultListener,
    OnGetPoiSearchResultListener {
    private lateinit var binding: ActivityChatLocationBinding
    private lateinit var poiSearch: PoiSearch
    private val markerIcon: BitmapDescriptor by lazy { BitmapDescriptorFactory.fromResource(R.drawable.overlay_cen) }
    private lateinit var mLocationClient: LocationClient
    private val myListener = MyLocationListener()
    private var mMarkerF: Marker? = null
    private var mScreenCenterPoint: Point? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatLocationBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.toolBar.rightBtn.text = getString(R.string.confirm)
        binding.toolBar.backBtn.setOnClickListener { finish() }
        binding.toolBar.titleText.text = getString(R.string.selectLocation)
        binding.rvAddress.layoutManager = LinearLayoutManager(this)

        // 开启定位图层
        mapSetting()
    }

    private fun mapSetting() {
        //一定要设置隐私政策为true
        LocationClient.setAgreePrivacy(true)
        poiSearch = PoiSearch.newInstance().apply {
            setOnGetPoiSearchResultListener(this@ChatLocation)
        }

        with(binding.mapView) {
            map.isMyLocationEnabled = true
            showZoomControls(false)
            map.uiSettings.isCompassEnabled = false
            map.uiSettings.isOverlookingGesturesEnabled = false
            map.setOnMapStatusChangeListener(this@ChatLocation)
            map.setMapStatus(MapStatusUpdateFactory.zoomTo(16f))
        }

        val option = LocationClientOption().apply {
            setIsNeedAddress(true)
            setIsNeedLocationDescribe(true)
            setIsNeedLocationPoiList(true)
            setNeedNewVersionRgc(true)
            isOpenGnss = true
            setCoorType("bd09ll")
            //多长时间定位一次，默认为0单次定位，单位毫秒需大于1000
            setScanSpan(0)
        }

        mLocationClient = LocationClient(applicationContext).apply {
            registerLocationListener(myListener)
            locOption = option
        }

        binding.mapView.map.setOnMapLoadedCallback {
            initOverlay()
        }
        mLocationClient.start()
    }

    private fun initOverlay() {
        val mBaiduMap = binding.mapView.map
        if (null != mBaiduMap.mapStatus) {
            val latLngF: LatLng = mBaiduMap.mapStatus.target
            mScreenCenterPoint = mBaiduMap.projection.toScreenLocation(latLngF)
            val markerOptionsF: OverlayOptions =
                MarkerOptions().position(latLngF)
                    .yOffset(30)
                    .icon(markerIcon)
                    .perspective(true)
                    .fixedScreenPosition(mScreenCenterPoint)
            mMarkerF = mBaiduMap.addOverlay(markerOptionsF) as Marker
            LogUtil.showD(
                "定位相关", "initOverlay"
            )
        }
    }

    inner class MyLocationListener : BDAbstractLocationListener() {
        override fun onReceiveLocation(location: BDLocation) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //这里获取到的poi没有经纬度
            val address = location.addrStr //获取详细地址信息
            val country = location.country //获取国家
            val province = location.province //获取省份
            val city = location.city //获取城市
            val district = location.district //获取区县
            val street = location.street //获取街道信息
            val adCode = location.adCode //获取adCode
            val town = location.town //获取乡镇信息
            val locationDescribe = location.locationDescribe
            val longitude = location.longitude
            val latitude = location.latitude
            LogUtil.showD(
                "定位相关",
                "address--${address}," +
                        "country--${country}" +
                        ",province--${province}" +
                        ",city--${city}" +
                        ",district--${district}" +
                        ",street--${street}" +
                        ",adCode--${adCode}" +
                        ",town--${town}" +
                        ",longitude--${longitude}" +
                        ",latitude--${latitude}" +
                        ",locationDescribe--${locationDescribe}"
            )
            val latLng = LatLng(latitude, longitude)
            //地图居中显示当前地址
            binding.mapView.map.setMapStatus(MapStatusUpdateFactory.newLatLng(latLng))
//            searchPoi(latLng)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient.stop()
        mMarkerF?.apply {
            cancelAnimation()
            // 释放检索对象
            poiSearch.destroy()
            // 清除所有图层
            binding.mapView.map.clear()
            binding.mapView.onDestroy()
            remove()
            markerIcon.recycle()
        }
    }

    //监听地图状态改变
    override fun onMapStatusChangeStart(p0: MapStatus?) {
        // 地图状态开始改变时的回调
    }

    override fun onMapStatusChangeStart(p0: MapStatus?, p1: Int) {
        // 地图状态开始改变时的回调（带动画效果）
    }

    override fun onMapStatusChange(p0: MapStatus?) {
        // 地图状态变化中的回调
    }

    override fun onMapStatusChangeFinish(p0: MapStatus) {
        // 地图状态改变结束后的回调
        mMarkerF?.apply {
            setAnimation(getTransformationPoint())
            startAnimation()
        }
        searchPoi(p0.target)
        LogUtil.showD(
            "定位相关", "onMapStatusChangeFinish==${p0.target.toString()}"
        )
    }

    private fun searchPoi(latLng: LatLng) {
        LogUtil.showD(
            "定位相关", "searchPoi==${latLng.toString()}"
        )
        poiSearch.searchNearby(
            PoiNearbySearchOption().location(
                latLng
            ).radius(200).scope(2).keyword("房地产\$门址点").pageCapacity(20)
        )
    }


    //反编码回调
    override fun onGetGeoCodeResult(p0: GeoCodeResult?) {
        // 地理编码结果回调
    }

    override fun onGetReverseGeoCodeResult(result: ReverseGeoCodeResult?) {
        // 反地理编码结果回调
        if (result != null && result.error == SearchResult.ERRORNO.NO_ERROR) {
            val address = result.address
            // 在这里获取到附近的POI地址，即address
        }
    }


    //poi搜索
    override fun onGetPoiResult(result: PoiResult?) {
        LogUtil.showD(
            "定位相关", "onGetPoiResult----${result?.error.toString()}"
        )
        // POI检索结果回调
        if (result != null && result.error == SearchResult.ERRORNO.NO_ERROR) {
            val poiList = result.allPoi
            // 在这里获取到附近区域的所有POI地址，即poiList
            for (poiInfo in poiList) {
                LogUtil.showD("定位相关", poiInfo.toString())
            }
        }
    }

    override fun onGetPoiDetailResult(p0: PoiDetailResult?) {
        // POI详情检索结果回调
    }

    override fun onGetPoiDetailResult(p0: PoiDetailSearchResult?) {
        // POI详情检索结果回调
    }

    override fun onGetPoiIndoorResult(p0: PoiIndoorResult?) {
        // 室内POI检索结果回调
    }


    /**
     * 创建平移坐标动画
     */
    private fun getTransformationPoint(): Animation? {
        mScreenCenterPoint?.let {
            val pointTo = Point(it.x, it.y - 100)
            val transformation = Transformation(mScreenCenterPoint, pointTo, mScreenCenterPoint)
            transformation.setDuration(500)
            transformation.setRepeatMode(Animation.RepeatMode.RESTART) // 动画重复模式
            transformation.setRepeatCount(1) // 动画重复次数
            transformation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart() {}
                override fun onAnimationEnd() {}
                override fun onAnimationCancel() {}
                override fun onAnimationRepeat() {}
            })
            transformation
        }
        return null
    }
}