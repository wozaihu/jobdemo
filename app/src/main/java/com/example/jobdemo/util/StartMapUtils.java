package com.example.jobdemo.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.jobdemo.R;
import com.example.jobdemo.constants.Api;

import java.net.URISyntaxException;

/**
 * @Author LYX
 * @Date 2022/10/8 9:50
 */
public class StartMapUtils {
    /**
     * 高德导航
     *
     * @param context
     * @param location
     */
    public static void gaodeGuide(Context context, double[] location) {
        if (Utils.checkAppsIsExist(context, Api.GAODE_MAP)) {
            try {
                Intent intent = Intent.getIntentOld("androidamap://navi?sourceApplication=" +
                        context.getResources().getString(R.string.app_name) +
                        "&poiname=我的目的地" +
                        "&lat=" + location[1] +
                        "&lon=" + location[0] +
                        "&dev=0");
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }

    /**
     * 百度导航
     *
     * @param context
     * @param location location[0]纬度lat，location[1]经度lon
     */
    public static void baiduGuide(Context context, double[] location) {

        if (Utils.checkAppsIsExist(context, Api.BAIDU_MAP)) {
            try {
                Intent intent = Intent.getIntent("intent://map/direction?" +
                        //"origin=latlng:"+"34.264642646862,108.95108518068&" +   //起点  此处不传值默认选择当前位置
                        "destination=latlng:" + location[1] + "," + location[0] + "|name:我的目的地" +
                        "&mode=driving" +          //导航路线方式
                        "&region=" +           //
                        "&src=" +
                        context.getResources().getString(R.string.app_name) +
                        "#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            //market为路径，id为包名
            //显示手机上所有的market商店
            Toast.makeText(context, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }

    /**
     * 腾讯导航
     *
     * @param context
     * @param location
     */
    public static void tencentGuide(Context context, double[] location) {
        String downloadUri = "http://softroute.map.qq.com/downloadfile?cid=00001";
        String baseUrl = "qqmap://map/";
        String searchPlace = "search?keyword=酒店&bound=39.907293,116.368935,39.914996,116.379321";
        String searchAround = "search?keyword=肯德基&center=39.908491,116.374328&radius=1000";
        String busPlan = "routeplan?type=bus&from=我的家&fromcoord=39.980683,116.302&to=柳巷&tocoord=39.9836,116.3164&policy=2";
        String drivePlan = "routeplan?type=drive&from=&fromcoord=&to=&tocoord=" + location[1] + "," + location[0] + "&policy=1";

        String tencnetUri = baseUrl + drivePlan + "&referer=" + context.getResources().getString(R.string.app_name);

        if (Utils.checkAppsIsExist(context, Api.TENCENT_MAP)) {
            try {
                Intent intent = Intent.parseUri(tencnetUri, 0);
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            //市场下载
            Toast.makeText(context, "您尚未安装腾讯地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=" + Api.TENCENT_MAP);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }
}
