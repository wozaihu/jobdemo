package com.example.jobdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;

public class SpinnerView extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "SpinnerView";

    /**
     * 提示文字
     */
    private String hint = "";

    /**
     * 提示文字的颜色
     */
    private int hint_color;

    /**
     * 提示的textView
     */
    private TextView tv_hint;

    /**
     * 右侧图标imageView
     */
    private ImageView img_icon;

    /**
     * 下拉列表数组
     */
    private CharSequence[] itemArray;

    /**
     * 根布局background
     */
    private int root_layout_background;

    /**
     * 右侧图标资源
     */
    private int SpinnerView_icon;
    private BaseAdapter adapter;
    private PopupWindow popupWindow;
    private ListView listView;

    public ListView getListView() {
        return listView;
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public SpinnerView(Context context) {
        this(context, null);
    }


    public SpinnerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("ResourceAsColor")
    public SpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SpinnerView, defStyleAttr, 0);
        root_layout_background = typedArray.getResourceId(R.styleable.SpinnerView_root_layout_background, R.color.white);
        hint = typedArray.getString(R.styleable.SpinnerView_hint_text);
        hint_color = typedArray.getColor(R.styleable.SpinnerView_hint_text_color, R.color.black);
        SpinnerView_icon = typedArray.getResourceId(R.styleable.SpinnerView_icon, 0);
        itemArray = typedArray.getTextArray(R.styleable.SpinnerView_entries);
        typedArray.recycle();
        initView(context);
    }

    /**
     * @param context
     * @// TODO: 2020/12/28 初始化view
     */
    private void initView(Context context) {

        if (root_layout_background != 0) {
            setBackgroundResource(root_layout_background);
        }
        setOnClickListener(this);
        tv_hint = new TextView(context);
        tv_hint.setOnClickListener(this);
        if (!TextUtils.isEmpty(hint)) {
            tv_hint.setText(hint);
        }
        addView(tv_hint);

        RelativeLayout.LayoutParams params = (LayoutParams) tv_hint.getLayoutParams();
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.setMargins(dip2px(5, context), 0, 0, 0);
        tv_hint.setLayoutParams(params);

        img_icon = new ImageView(context);
        img_icon.setOnClickListener(this);
        addView(img_icon);
        RelativeLayout.LayoutParams imgIconLayoutParams = (LayoutParams) img_icon.getLayoutParams();
        imgIconLayoutParams.height = dip2px(15, context);
        imgIconLayoutParams.width = dip2px(15, context);
        imgIconLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        imgIconLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imgIconLayoutParams.setMargins(0, 0, dip2px(5, context), 0);
        if (SpinnerView_icon != 0) {
            img_icon.setImageResource(SpinnerView_icon);
        }
        img_icon.setLayoutParams(imgIconLayoutParams);

    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(float pxValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        showPopupWindow(v.getContext());
    }

    /**
     * @param context 显示下拉框
     */
    @SuppressLint({"RestrictedApi", "UseCompatLoadingForDrawables"})
    private void showPopupWindow(Context context) {
        if (adapter == null && itemArray != null) {
            adapter = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_dropdown_item, itemArray);
        }
        listView = new ListView(context);
        listView.setAdapter(adapter);
        if (popupWindow == null) {
            popupWindow = new PopupWindow(listView, this.getWidth(), 400, true);
            popupWindow.setOutsideTouchable(false);
            popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_white));
        }
        ShowPw(popupWindow, this, 0, 0);
    }

    /**
     * @param pw     popupWindow
     * @param anchor v
     * @param xoff   x轴偏移
     * @param yoff   y轴偏移
     */
    public static void ShowPw(final PopupWindow pw, final View anchor, final int xoff, final int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            pw.setHeight(height);
            pw.showAsDropDown(anchor, xoff, yoff);
        } else {
            pw.showAsDropDown(anchor, xoff, yoff);
        }
    }

    /**
     * @param drawable 设置背景图片资源
     */
    public void setRootViewBackground(int drawable) {
        root_layout_background = drawable;
        this.setBackgroundResource(root_layout_background);
    }

    /**
     * @param str 设置提示文字
     */
    public void setHint(String str) {
        hint = str;
        tv_hint.setText(hint);
    }

    /**
     * 设置提示文字显示
     */
    public void setHintVisibly() {
        if (tv_hint != null)
            tv_hint.setVisibility(VISIBLE);
    }

    /**
     * 设置提示文字隐藏
     */
    public void setHintGone() {
        if (tv_hint != null)
            tv_hint.setVisibility(GONE);
    }

    /**
     * @param tvColor 设置提示文字颜色
     */
    public void setTv_hintColor(int tvColor) {
        hint_color = tvColor;
        tv_hint.setTextColor(hint_color);
    }

    /**
     * @param drawable 设置右侧图标
     */
    public void setIcon(int drawable) {
        SpinnerView_icon = drawable;
        img_icon.setImageResource(SpinnerView_icon);
    }

    /**
     * @param array 设置数组（如果设置了会使用默认的adapter）
     */
    public void setItemArray(String[] array) {
        itemArray = array;
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
