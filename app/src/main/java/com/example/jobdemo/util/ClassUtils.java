package com.example.jobdemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.jobdemo.bean.ActivityBean;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;

import java.util.ArrayList;
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
     * @return activityBean
     */
    public static List<ActivityBean> getActivitiesClass(Context context, String packageName) {

        List<ActivityBean> activityList = new ArrayList<>();
        try {
            //Get all activity classes in the AndroidManifest.xml
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (packageInfo.activities != null) {
                for (ActivityInfo ai : packageInfo.activities) {
                    Class c = null;
                    try {
                        if (ai.name.contains(packageName)) {
                            c = Class.forName(ai.name);
                        }
                        // 排除其他SDK合并的activity，只显示自己写的activity，用包名排除
                        //descriptionRes做一个标记，不为空则为要排除的activity
                        if (c != null && Activity.class.isAssignableFrom(c) && ai.descriptionRes == 0) {
                            String activityName;
                            if (ai.labelRes == 0) {
                                activityName = c.getSimpleName();
                            } else {
                                activityName = context.getString(ai.labelRes);
                            }
                            String tempName = activityName.toLowerCase();
                            String pinyin = PinyinHelper.toPinyin(tempName, PinyinStyleEnum.FIRST_LETTER, StringUtil.EMPTY);
                            activityList.add(new ActivityBean(activityName, pinyin.substring(0, 1).toUpperCase(), pinyin, c));
                        }
                    } catch (Exception e) {
                        LogUtil.showD(TAG, "Class Not Found:" + e.getMessage());
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return activityList;
    }

}
