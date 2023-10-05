package com.example.jobdemo.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat

class LocationUtils(private val context: Context) {

    companion object {
        private const val MIN_TIME_BETWEEN_UPDATES = 1000L // 更新间隔时间为1秒
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES = 10f // 位置变化最小距离为10米
    }

    private val locationManager: LocationManager by lazy {
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    fun getLocation(callback: (Location?) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 没有定位权限，处理权限申请逻辑
            callback(null)
            return
        }

        // 尝试使用网络定位
        val networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (networkLocation != null) {
            callback(networkLocation)
            return
        }

        // 尝试使用 GPS 定位
        val gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (gpsLocation != null) {
            callback(gpsLocation)
            return
        }

        // 注册监听器以获取位置更新
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                callback(location)
                locationManager.removeUpdates(this)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

            override fun onProviderEnabled(provider: String) {}

            override fun onProviderDisabled(provider: String) {
                callback(null)
                locationManager.removeUpdates(this)
            }
        }

        // 请求位置更新
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            MIN_TIME_BETWEEN_UPDATES,
            MIN_DISTANCE_CHANGE_FOR_UPDATES,
            locationListener
        )
    }
}