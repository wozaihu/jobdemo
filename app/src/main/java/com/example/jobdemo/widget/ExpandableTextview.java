package com.example.jobdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.util.LogUtil;

/**
 * @Author LYX
 * @Date 2022/4/8 16:17
 */
public class ExpandableTextview extends AppCompatTextView {

    /**
     * 超过多少个字显示后缀提示
     */
    private int maxWord = 150;

    /**
     * 是否已显示全部文字
     */
    private boolean isShowAll = false;

    /**
     * 展示全部内容时是否显示收起
     */
    private boolean showFold;


    /**
     * 完整文字内容
     */
    private String allString;

    /**
     * 收缩时后缀文字
     */
    private String unfoldWord;

    /**
     * 收缩时后缀文字颜色
     */
    private int unfoldWordColor;

    /**
     * 收缩时后缀文字大小
     */
    private int unfoldWordSize;

    /**
     * 完全展示时后缀文字
     */
    private String foldWord;


    /**
     * 完全展示时后缀文字颜色
     */
    private int foldWordColor;

    /**
     * 完全展示时后缀文字大小
     */
    private int foldWordSize;

    private OnExpandListener onExpandListener;
    /**
     * 加上unfold 提示文字后的内容
     */
    private String unfoldStr;
    /**
     * 加上fold提示文字后的内容
     */
    private String foldStr;

    public interface OnExpandListener {
        /**
         * 状态变化监听
         *
         * @param status 展开状态
         * @return true为显示全部内容
         */
        boolean expandChange(boolean status);
    }

    public void setOnExpandListener(OnExpandListener onExpandListener) {
        this.onExpandListener = onExpandListener;
    }

    public ExpandableTextview(@NonNull Context context, int maxWord) {
        this(context);
        this.maxWord = maxWord;
    }

    private ExpandableTextview(@NonNull Context context) {
        this(context, null);
    }

    public ExpandableTextview(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private ExpandableTextview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ExpandableTextview, defStyleAttr, 0);
        maxWord = ta.getInteger(R.styleable.ExpandableTextview_max_word, maxWord);
        showFold = ta.getBoolean(R.styleable.ExpandableTextview_show_fold, false);
        unfoldWord = ta.getString(R.styleable.ExpandableTextview_unfold_word);
        foldWord = ta.getString(R.styleable.ExpandableTextview_fold_word);
        if (TextUtils.isEmpty(unfoldWord)) {
            unfoldWord = "展开";
        }
        if (TextUtils.isEmpty(foldWord)) {
            foldWord = "收起";
        }
        unfoldWordColor = ta.getColor(R.styleable.ExpandableTextview_unfold_word_color, ContextCompat.getColor(context, R.color.green));
        foldWordColor = ta.getColor(R.styleable.ExpandableTextview_fold_word_color, ContextCompat.getColor(context, R.color.green));
        unfoldWordSize = ta.getDimensionPixelSize(R.styleable.ExpandableTextview_unfold_word_size, -1);
        foldWordSize = ta.getDimensionPixelSize(R.styleable.ExpandableTextview_fold_word_size, -1);
        ta.recycle();
        if (TextUtils.isEmpty(getText())) {
            setText(allString);
            LogUtil.showD("---设置后缀异常-------ExpandableTextview" + allString);
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        LogUtil.showD("---设置后缀异常-------" + text);
        try {
            if (TextUtils.isEmpty(allString)) {
                allString = text.toString();
            }

            if (TextUtils.isEmpty(text) || text.length() <= maxWord) {
                super.setText(text, type);
            } else {
                if (!text.equals(unfoldStr) && !text.equals(foldStr)) {
                    allString = text.toString();
                }
                if (!TextUtils.isEmpty(unfoldWord)) {
                    SpannableString spannable = getSpannable(text.toString());
                    super.setText(spannable, type);
                }
            }
        } catch (Exception e) {
            LogUtil.showD("---设置后缀异常-------" + e.getMessage());
        }
    }

    private SpannableString getSpannable(String s) {
        String str;
        SpannableString msp;
        if (!isShowAll) {
            str = s.substring(0, maxWord) + "..." + unfoldWord;
            unfoldStr = str;
            msp = new SpannableString(str);
            msp.setSpan(new ForegroundColorSpan(unfoldWordColor), str.length() - unfoldWord.length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            if (unfoldWordSize != -1) {
                msp.setSpan(new AbsoluteSizeSpan(unfoldWordSize, true), str.length() - unfoldWord.length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            msp.setSpan(new ClickableSpan() {

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    ds.setUnderlineText(false);
                    ds.setColor(unfoldWordColor);
                }

                @Override
                public void onClick(@NonNull View widget) {
                    isShowAll = true;
                    setText(allString);
                    if (onExpandListener != null) {
                        onExpandListener.expandChange(isShowAll);
                    }
                }

            }, str.length() - unfoldWord.length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        } else {
            //不显示收起直接返回
            if (!showFold) {
                return new SpannableString(s);
            }
            str = s + foldWord;
            foldStr = str;
            msp = new SpannableString(str);
            msp.setSpan(new ForegroundColorSpan(foldWordColor), str.length() - foldWord.length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            if (foldWordSize != -1) {
                msp.setSpan(new AbsoluteSizeSpan(foldWordSize, true), str.length() - foldWord.length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            msp.setSpan(new ClickableSpan() {

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    ds.setUnderlineText(false);
                    ds.setColor(foldWordColor);
                }

                @Override
                public void onClick(@NonNull View widget) {
                    isShowAll = false;
                    setText(allString);
                    if (onExpandListener != null) {
                        onExpandListener.expandChange(isShowAll);
                    }
                }
            }, str.length() - foldWord.length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        setHighlightColor(Color.TRANSPARENT);
        setMovementMethod(LinkMovementMethod.getInstance());
        return msp;
    }


    public void setShowFold(boolean showFold) {
        this.showFold = showFold;
        setText(allString);
    }

    public void setUnfoldWord(String unfoldWord) {
        this.unfoldWord = unfoldWord;
        setText(allString);
    }

    public void setUnfoldWordColor(int unfoldWordColor) {
        this.unfoldWordColor = unfoldWordColor;
        setText(allString);
    }

    public void setUnfoldWordSize(int unfoldWordSize) {
        this.unfoldWordSize = unfoldWordSize;
        setText(allString);
    }

    public void setFoldWord(String foldWord) {
        this.foldWord = foldWord;
        setText(allString);
    }

    public void setFoldWordColor(int foldWordColor) {
        this.foldWordColor = foldWordColor;
        setText(allString);
    }

    public void setFoldWordSize(int foldWordSize) {
        this.foldWordSize = foldWordSize;
        setText(allString);
    }
}
