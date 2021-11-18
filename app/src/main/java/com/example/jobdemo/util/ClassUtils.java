package com.example.jobdemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 获得manifest中注册的所有activity
 *
 * @Author Administrator
 * @Date 2021/8/9 9:56
 */
public class ClassUtils {
    private final static String TAG = "ClassUtils";

    private ClassUtils() {
    }

    /**
     * 返回AndroidManifest.xml中注册的所有Activity的class
     *
     * @param context     环境
     * @param packageName 包名
     * @param excludeList 排除class列表
     * @return
     */
    public final static List<Class> getActivitiesClass(Context context, String packageName, List<Class> excludeList) {

        List<Class> returnClassList = new ArrayList<Class>();
        try {
            //Get all activity classes in the AndroidManifest.xml
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (packageInfo.activities != null) {
                LogUtil.showD(TAG, "Found " + packageInfo.activities.length + " activity in the AndrodiManifest.xml");
                for (ActivityInfo ai : packageInfo.activities) {
                    Class c = null;
                    try {
                        if (ai.name.contains(packageName)) {
                            c = Class.forName(ai.name);
                        }
                        // 排除其他SDK合并的activity，只显示自己写的activity，用包名排除
                        if (c != null && Activity.class.isAssignableFrom(c) && ai.name.contains(packageName)) {
                            returnClassList.add(c);
                            LogUtil.showD(TAG, ai.name + "...OK");
                        }
                    } catch (Exception e) {
                        LogUtil.showD(TAG, "Class Not Found:" + e.getMessage());
                        LogUtil.showD(TAG, "Class Not Found:" + ai.name);
                    }
                }
                LogUtil.showD(TAG, "Filter out, left " + returnClassList.size() + " activity," + Arrays.toString(returnClassList.toArray()));

                //Exclude some activity classes
                if (excludeList != null) {
                    returnClassList.removeAll(excludeList);
                    LogUtil.showD(TAG, "Exclude " + excludeList.size() + " activity," + Arrays.toString(excludeList.toArray()));
                }
                LogUtil.showD(TAG, "Return " + returnClassList.size() + " activity," + Arrays.toString(returnClassList.toArray()));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return returnClassList;
    }

}
