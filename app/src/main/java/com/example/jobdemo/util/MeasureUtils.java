package com.example.jobdemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MeasureUtils {
    private volatile static MeasureUtils measureUtils;

    private MeasureUtils() {
    }

    public static MeasureUtils getInstance() {
        if (measureUtils == null) {
            synchronized (MeasureUtils.class) {
                if (measureUtils == null) {
                    measureUtils = new MeasureUtils();
                }
            }
        }
        return measureUtils;
    }


    /**
     * 获取DisplayMetrics
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }


    /**
     * 获取屏幕的宽
     * 物理屏幕宽高
     * 底部没有虚拟按键
     * 这里获取到的宽高，就是你眼睛能看到的，屏幕亮着的地方的宽高。
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /*    二、底部有虚拟按键
        华为手机底部都会有一个黑色的虚拟按键(NavigationBar)，
        通过上面这个方式得到的屏幕高度是屏幕真是高度-虚拟按键的高度。
        所以有虚拟按键的情况获取屏幕的高度就是另一种方法了。*/
    public static int getRealHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screenHeight = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics dm = new DisplayMetrics();
            display.getRealMetrics(dm);
            screenHeight = dm.heightPixels;

            //或者也可以使用getRealSize方法
//            Point size = new Point();
//            display.getRealSize(size);
//            screenHeight = size.y;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            try {
                screenHeight = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) {
                DisplayMetrics dm = new DisplayMetrics();
                display.getMetrics(dm);
                screenHeight = dm.heightPixels;
            }
        }
        return screenHeight;
    }

/*    虚拟按键高度
    虚拟按键(NavigationBar)高度可以通过读取定义在Android系统尺寸资源中的 navigation_bar_height 获得。
    所以不管虚拟按键是显示还是隐藏，得到的结果都是一样的。*/

    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = -1;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }


    //获得状态栏高度（电量，信号栏）
    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 不能在 onCreate 方法中使用。
     * 因为这种方法依赖于WMS（窗口管理服务的回调）。正是因为窗口回调机制，所以在Activity初始化时执行此方法得到的高度是0。
     * 这个方法推荐在回调方法onWindowFocusChanged()中执行，才能得到预期结果。
     */
    public void getAppViewHeight(Activity activity) {
        //屏幕
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //应用区域
        Rect outRect1 = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
        int statusBar = dm.heightPixels - outRect1.height();  //状态栏高度=屏幕高度-应用区域高度
    }

    //    标题栏高度
    //  标题栏高度 = 应用区高度 - view 显示高度
    public static void getTitleBarHeight(Activity activity) {
        Rect outRect1 = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);

        int viewTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();   //要用这种方法
        int titleBarH = viewTop - outRect1.top;
    }

    //获得toolbar高度
    public int getToolbarHeight(Activity activity) {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }
}
