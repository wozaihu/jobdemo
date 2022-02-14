package com.example.jobdemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * @author Administrator
 */
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
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
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
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            WindowMetrics windowMetrics = wm.getCurrentWindowMetrics();
//            Insets insets = windowMetrics.getWindowInsets()
//                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
//            return windowMetrics.getBounds().width() - insets.left - insets.right;
//        } else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
//        }
    }

    /**
     * 获取屏幕的高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * @param context 上下文
     * @return 有虚拟按键情况下获得屏幕高度
     */
    public static int getRealHeight(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screenHeight = 0;
        DisplayMetrics dm = new DisplayMetrics();
        display.getRealMetrics(dm);
        screenHeight = dm.heightPixels;
        return screenHeight;
    }


    /**
     * @param context 虚拟按键(NavigationBar)高度可以通过读取定义在Android系统尺寸资源中的 navigation_bar_height 获得。
     *                所以不管虚拟按键是显示还是隐藏，得到的结果都是一样的。
     * @return 虚拟按键高度
     */
    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = -1;
        Resources resources = context.getApplicationContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }


    /**
     * @param context
     * @return 状态栏高度
     */
    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 不能在 onCreate 方法中使用。
     * 因为这种方法依赖于WMS（窗口管理服务的回调）。正是因为窗口回调机制，所以在Activity初始化时执行此方法得到的高度是0。
     * 这个方法推荐在回调方法onWindowFocusChanged()中执行，才能得到预期结果。
     */
    public int getAppViewHeight(Activity activity) {
        WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);

        //屏幕
        DisplayMetrics dm = new DisplayMetrics();
        activityWeakReference.get().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //应用区域
        Rect outRect1 = new Rect();
        activityWeakReference.get().getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
        //状态栏高度=屏幕高度-应用区域高度
        return dm.heightPixels - outRect1.height();
    }

    /**
     * @param activity 标题栏高度 = 应用区高度 - view 显示高度
     */

    public static int getTitleBarHeight(Activity activity) {
        WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        Rect outRect1 = new Rect();
        activityWeakReference.get().getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
        //要用这种方法
        int viewTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return viewTop - outRect1.top;
    }

    /**
     * @param activity 获得toolbar高度
     * @return
     */
    public int getToolbarHeight(Activity activity) {
        WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activityWeakReference.get().getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }
}
