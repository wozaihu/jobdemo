package com.example.jobdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.util.DensityUtil;

/**
 * @author Administrator
 */
public class SpinnerView extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "SpinnerView";
    private static final int MINUS_TWO = -2;

    private final Context context;

    /**
     * 提示文字
     */
    private String hint;

    /**
     * 提示文字的颜色
     */
    private int hintColor;

    /**
     * 提示的textView
     */
    private TextView tvHint;

    /**
     * 右侧图标imageView
     */
    private ImageView imgIcon;

    /**
     * 下拉列表数组
     */
    private CharSequence[] itemArray;

    /**
     * 根布局background
     */
    private int rootLayoutBackground;

    /**
     * 右侧图标资源
     */
    private int spinnerViewIcon;
    private BaseAdapter adapter;
    private PopupWindow popupWindow;
    private final ListView listView = new ListView(this.getContext());


    /**
     * 默认适配器选中的item
     */
    private int defaultSelectorPosition = -2;

    private ItemClickBackCall itemClickBackCall;

    /**
     * 标记，当一个界面有多个spinnerView控件时，用来判断是哪个listView的回调
     */
    private int tag;

    /**
     * @param itemClickBackCall 设置回调
     */
    public void setItemClickBackCall(ItemClickBackCall itemClickBackCall, int tag) {
        this.itemClickBackCall = itemClickBackCall;
        this.tag = tag;
    }


    /**
     * 提示文字的颜色
     */
    private final int itemSelectorTextColor;

    /**
     * 是否开启选中item变文字颜色
     */
    private final boolean itemSelector;

    public ListView getListView() {
        return listView;
    }

    /**
     * popupWindowDismiss
     */
    public void popupWindowDismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
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
        this.context = context;
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SpinnerView, defStyleAttr, 0);
        rootLayoutBackground = typedArray.getResourceId(R.styleable.SpinnerView_root_layout_background, R.color.white);
        hint = typedArray.getString(R.styleable.SpinnerView_hint_text);
        hintColor = typedArray.getColor(R.styleable.SpinnerView_hint_text_color, Color.BLACK);

        itemSelectorTextColor = typedArray.getColor(R.styleable.SpinnerView_itemSelectorTextColor
                , ContextCompat.getColor(context, R.color.colorAccent));

        itemSelector = typedArray.getBoolean(R.styleable.SpinnerView_itemSelector, false);
        spinnerViewIcon = typedArray.getResourceId(R.styleable.SpinnerView_icon, 0);
        itemArray = typedArray.getTextArray(R.styleable.SpinnerView_entries);
        typedArray.recycle();
        initView(context);
    }

    /**
     * @param context 上下文
     */
    private void initView(Context context) {

        if (rootLayoutBackground != 0) {
            setBackgroundResource(rootLayoutBackground);
        }
        setOnClickListener(this);

        //先把控件加进去，再设置属性
        tvHint = new TextView(context);
        tvHint.setOnClickListener(this);
        if (!TextUtils.isEmpty(hint)) {
            tvHint.setText(hint);
        }
        addView(tvHint);

        imgIcon = new ImageView(context);
        imgIcon.setOnClickListener(this);
        imgIcon.setId(R.id.spinner_icon);

        RelativeLayout.LayoutParams params = (LayoutParams) tvHint.getLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.LEFT_OF, R.id.spinner_icon);
        params.setMargins(DensityUtil.dip2px(context, 5), 0, DensityUtil.dip2px(context, 5), 0);
        tvHint.setTextColor(hintColor);
        tvHint.setLines(1);
        tvHint.setEllipsize(TextUtils.TruncateAt.END);
        tvHint.setGravity(Gravity.CENTER_VERTICAL);
        tvHint.setLayoutParams(params);

        imgIcon = new ImageView(context);
        imgIcon.setOnClickListener(this);
        imgIcon.setId(R.id.spinner_icon);
        addView(imgIcon);
        RelativeLayout.LayoutParams imgIconLayoutParams = (LayoutParams) imgIcon.getLayoutParams();
        imgIconLayoutParams.height = DensityUtil.dip2px(context, 15);
        imgIconLayoutParams.width = DensityUtil.dip2px(context, 15);
        imgIconLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        imgIconLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imgIconLayoutParams.setMargins(0, 0, DensityUtil.dip2px(context, 5), 0);
        if (spinnerViewIcon != 0) {
            imgIcon.setImageResource(spinnerViewIcon);
        }
        imgIcon.setLayoutParams(imgIconLayoutParams);

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
        //传进来charSequence数组时使用默认的adapter
        if (adapter == null && itemArray != null) {
            adapter = new DefaultAdapter(itemArray, itemSelector);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                defaultSelectorPosition = position;
                adapter.notifyDataSetChanged();
                tvHint.setText(itemArray[position]);
                if (itemClickBackCall != null) {
                    itemClickBackCall.itemClick(position, tag);
                }
                popupWindow.dismiss();
            });
        }

        //没有设置适配器点击不做反应
        if (adapter == null) {
            return;
        }
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setAdapter(adapter);
        if (popupWindow == null) {
            popupWindow = new PopupWindow(listView, this.getWidth(), LayoutParams.WRAP_CONTENT, true);
            popupWindow.setOutsideTouchable(false);
            popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_white));
        }
        Log.d(TAG, "listViewHeight== " + listView.getHeight());

        if (defaultSelectorPosition != MINUS_TWO && defaultSelectorPosition < listView.getCount()) {
            listView.setSelection(defaultSelectorPosition);
        }
        imgIcon.setSelected(true);
        popupWindow.setOnDismissListener(() -> imgIcon.setSelected(false));
        showPw(popupWindow, this, 0, 0);
    }

    /**
     * 固定popupWindow的位置，及时位置不够也不改变
     *
     * @param pw     popupWindow
     * @param anchor v
     * @param xOff   x轴偏移
     * @param yOff   y轴偏移
     */
    public static void showPw(final PopupWindow pw, final View anchor, final int xOff, final int yOff) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            int min = Math.min(height, pw.getHeight());
            pw.setHeight(min);
        }
        pw.showAsDropDown(anchor, xOff, yOff);
    }

    /**
     * @param drawable 设置背景图片资源
     */
    public void setRootViewBackground(int drawable) {
        rootLayoutBackground = drawable;
        this.setBackgroundResource(rootLayoutBackground);
    }

    /**
     * @param str 设置提示文字
     */
    public void setHint(String str) {
        hint = str;
        tvHint.setText(hint);
    }

    /**
     * @param gravity 设置提示文字
     */
    public void setWay(int gravity) {
        tvHint.setGravity(gravity);
    }

    /**
     * @param tvColor 设置提示文字颜色
     */
    public void setTvHintColor(int tvColor) {
        hintColor = tvColor;
        tvHint.setTextColor(hintColor);
    }

    /**
     * @param drawable 设置右侧图标，支持使用selectStatus的drawable，popupWindow显示时选中，dismiss时未选中
     */
    public void setIcon(int drawable) {
        spinnerViewIcon = drawable;
        imgIcon.setImageResource(spinnerViewIcon);
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

    /**
     * 获得被选中的position
     */
    public int getDefaultSelectorPosition() {
        return defaultSelectorPosition;
    }


    /**
     * @param defaultSelectorPosition 设置陪选中的position
     */
    public void setDefaultSelectorPosition(int defaultSelectorPosition) {
        this.defaultSelectorPosition = defaultSelectorPosition;
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 默认适配器，支持设置选中item的文字颜色
     */
    private class DefaultAdapter extends BaseAdapter {

        private final CharSequence[] array;
        private final boolean isSelector;

        public DefaultAdapter(CharSequence[] array, boolean isSelector) {
            this.array = array;
            this.isSelector = isSelector;
        }

        @Override
        public int getCount() {
            return array.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("ResourceType")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (isSelector) {
                holder.textView.setText(array[position]);
                if (defaultSelectorPosition == position) {
                    holder.textView.setTextColor(itemSelectorTextColor);
                } else {
                    holder.textView.setTextColor(tvHint.getCurrentTextColor());
                }
            } else {
                holder.textView.setText(array[position]);
            }
            return convertView;
        }

        class ViewHolder {
            private final TextView textView;

            public ViewHolder(View view) {
                textView = view.findViewById(android.R.id.text1);
            }
        }
    }

    /**
     * 点击listViewItem后的回调(只有使用Char数组时才会调用)
     */
    public interface ItemClickBackCall {
        void itemClick(int position, int tag);
    }
}
