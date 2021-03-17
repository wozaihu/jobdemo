package com.example.jobdemo.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.jobdemo.MyApplication;

public enum DensityUtil {
    INSTANCE;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = MyApplication.getAppContent().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(float pxValue) {
        final float scale = MyApplication.getAppContent().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据列表项的个数，设定listView的高度
     */
    public int setListViewHeightBasedOnChildren(ListView listView, int width) {
        BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return -1;
        }
        int totalHeight = 0;
        //获取listView的宽度
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //给item的measure设置参数是listView的宽度就可以获取到真正每一个item的高度
            listItem.measure(widthSpec, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() + 1));
        listView.setLayoutParams(params);
        return listView.getHeight();
    }
}
