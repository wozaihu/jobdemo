package com.example.jobdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.util.DensityUtil;
import com.example.jobdemo.util.LogUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证码输入框
 * 1、未设置inputWidth宽度，则验证码平分宽度；
 * 2、有设置inputWidth宽度则宽度固定，如果宽度过大可能显示不全，要缩小默认间隔请设置间隔
 * 3、左右间隔由VerCodeInputView的margin控制
 * 4、验证码高度匹配VerCodeInputView的高度
 * invalidate 只调用onDraw 重绘，
 * requestLayout调用onMeasure和onLayout
 */

public class VerCodeInputView extends FrameLayout {
    private static final String TAG = "VerCodeInputView";
    /**
     * 输入框个数
     */
    private int inputNum;
    /**
     * 输入框宽度
     */
    private int inputWidth;
    /**
     * 输入框之间的间隔
     */
    private int childPadding;
    /**
     * 输入框背景
     */
    private int editTextBg;
    /**
     * 文本颜色
     */
    private int textColor;
    /**
     * 文本字体大小
     */
    private int textSize;
    /**
     * 输入类型
     */
    private int inputType;

    /**
     * 样式，默认是1 方框，2是横线（参考百度贴吧）
     */
    private int styleType;
    /**
     * 默认间隔
     */
    private final int defaultChildPadding;
    private LinearLayout llTextViewRoot;


    public VerCodeInputView(Context context) {
        this(context, null);
    }

    public VerCodeInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public VerCodeInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VerCodeInputView, defStyleAttr, 0);
        inputNum = ta.getInteger(R.styleable.VerCodeInputView_inputNum, 6);
        styleType = ta.getInteger(R.styleable.VerCodeInputView_styleType, 1);
        if (styleType == 1) {
            editTextBg = ta.getResourceId(R.styleable.VerCodeInputView_inputBg, R.drawable.selector_bg_edit);
        } else {
            editTextBg = ta.getResourceId(R.styleable.VerCodeInputView_inputBg, R.drawable.transverse_lin);
        }
        inputWidth = ta.getDimensionPixelSize(R.styleable.VerCodeInputView_inputWidth, 0);
        defaultChildPadding = DensityUtil.dip2px(context,8f);
        childPadding = ta.getDimensionPixelSize(R.styleable.VerCodeInputView_inputPadding, defaultChildPadding);
        textColor = ta.getColor(R.styleable.VerCodeInputView_inputTxtColor, ContextCompat.getColor(context, R.color.green));
        textSize = ta.getDimensionPixelSize(R.styleable.VerCodeInputView_inputTxtSize, 14);
        inputType = ta.getInt(R.styleable.VerCodeInputView_inputType, InputType.TYPE_CLASS_NUMBER);
        ta.recycle();
        textViewList = new ArrayList<>(inputNum);
        initViews();
    }

    private List<TextView> textViewList;
    private EditText editText;

    private void initViews() {
        textViewList = new ArrayList<>(inputNum);
        llTextViewRoot = new LinearLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        llTextViewRoot.setLayoutParams(layoutParams);
        llTextViewRoot.setOrientation(LinearLayout.HORIZONTAL);
        addView(llTextViewRoot);
        for (int i = 0; i < inputNum; i++) {
            textViewList.add(getTextView());
        }
        initEditText();
        initListener();
    }

    private TextView getTextView() {
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        if (llTextViewRoot.getChildCount() == 0) {
            textView.setText("|");
        } else {
            params.leftMargin = childPadding;
        }
        if (inputWidth == 0) {
            params.weight = 1;
        } else {
            params.width = inputWidth;
        }
        params.gravity = Gravity.CENTER;
        textView.setLayoutParams(params);
        textView.setTextColor(textColor);
        textView.setTextSize(textSize);
        textView.setGravity(Gravity.CENTER);
        textView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
        textView.setInputType(inputType);
        textView.setBackgroundResource(editTextBg);
        llTextViewRoot.addView(textView);
        return textView;
    }

    /**
     * 初始化输入框
     */
    private void initEditText() {
        editText = new EditText(getContext());
        LayoutParams layoutParam2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        editText.setLayoutParams(layoutParam2);
        editText.setTextSize(0.0001f);
        //设置透明光标，如果直接不显示光标的话，长按粘贴会没效果
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                editText.setTextCursorDrawable(R.drawable.edit_cursor_bg_transparent);
            } else {
                Field setCursor = TextView.class.getDeclaredField("mCursorDrawableRes");
                setCursor.setAccessible(true);
                setCursor.set(this, R.drawable.edit_cursor_bg_transparent);
            }
        } catch (Exception e) {
            LogUtil.showD(TAG, "验证码光标颜色设置异常" + e.getMessage());
            e.printStackTrace();
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(inputNum)});
        editText.setInputType(inputType);
        editText.setTextColor(ContextCompat.getColor(getContext(), R.color.transparent));
        editText.setBackground(null);
        editText.addTextChangedListener(textWatcher);
        addView(editText);
    }

    private void initListener() {
        //屏蔽双击： 好多手机双击会出现 选择 剪切 粘贴 的选项卡，
        new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return true;
            }
        });
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String inputContent = TextUtils.isEmpty(editText.getText().toString()) ? "" : editText.getText().toString();
            //已经有输入时，屏蔽长按和光标
            if (inputContent.length() > 0) {
                editText.setLongClickable(false);
                editText.setCursorVisible(false);
            } else {
                editText.setLongClickable(true);
                editText.setCursorVisible(true);
            }
            //输入完成调用的方法
            if (listener != null && inputContent.length() >= inputNum) {
                listener.onComplete(inputContent);
            }
            for (int i = 0; i < textViewList.size(); i++) {
                TextView textView = textViewList.get(i);
                if (i < inputContent.length()) {
                    textView.setText(String.valueOf(inputContent.charAt(i)));
                } else if (i == inputContent.length()) {
                    textView.setText("|");
                } else {
                    textView.setText("");
                }
            }
        }
    };


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        if (inputWidth > 0) {
            resetMargin(width);
        }
    }


    /**
     * @param width 有设置inputWidth则从新设置宽度
     */
    private void resetMargin(int width) {
        if (width > 0) {
            //剩余的宽度
            int remainWidth = width - (inputNum * inputWidth);

            //设置宽度后还有空间，且没有设置间隔，则间隔为剩余空间平分
            if (remainWidth > 0 && defaultChildPadding == childPadding) {
                childPadding = remainWidth / (inputNum - 1);
            }
            for (int i = 0; i < textViewList.size(); i++) {
                View child = textViewList.get(i);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) child.getLayoutParams();
                //最后一个textView 不设置margin
                if (i != 0) {
                    params.leftMargin = childPadding;
                }
                params.weight = inputWidth;
                params.gravity = Gravity.CENTER;
                child.setLayoutParams(params);
            }
        }
    }

    /**
     * @param inputNum 验证码个数
     */
    public void setInputNum(int inputNum) {

        if (inputNum != this.inputNum) {
            this.inputNum = inputNum;
            textViewList.clear();
            llTextViewRoot.removeAllViews();
            for (int i = 0; i < inputNum; i++) {
                textViewList.add(getTextView());
            }
            initEditText();
        }
    }

    /**
     * @param inputWidth 设置验证码单个宽度
     */
    public void setInputWidth(int inputWidth) {
        this.inputWidth = inputWidth;
        Log.d("重新绘制验证码框", "总的宽度为===" + getWidth());
        resetMargin(getWidth());
    }

    /**
     * @param childPadding 设置间隔
     */
    public void setChildPadding(int childPadding) {
        this.childPadding = childPadding;
        textViewList.clear();
        llTextViewRoot.removeAllViews();
        for (int i = 0; i < inputNum; i++) {
            textViewList.add(getTextView());
        }
    }

    /**
     * @param editTextBg 设置背景
     */
    public void setEditTextBg(int editTextBg) {
        this.editTextBg = editTextBg;
        updateText();
    }

    /**
     * @param textColor 设置文字颜色
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
        updateText();
    }

    /**
     * @param textSize 设置文字大小
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
        updateText();
    }

    /**
     * @param inputType 设置键盘输入类型
     */
    public void setInputType(int inputType) {
        this.inputType = inputType;
        updateText();
    }

    /**
     * @param styleType 设置验证码样式
     */
    public void setStyleType(int styleType) {
        this.styleType = styleType;
        for (int i = 0; i < textViewList.size(); i++) {
            TextView child = textViewList.get(i);
            if (styleType == 1) {
                child.setBackgroundResource(R.drawable.selector_bg_edit);
            } else {
                child.setBackgroundResource(R.drawable.transverse_lin);
            }
        }
    }

    /**
     * 更新text属性
     */
    public void updateText() {
        for (int i = 0; i < textViewList.size(); i++) {
            TextView child = textViewList.get(i);
            child.setTextColor(textColor);
            child.setTextSize(textSize);
            child.setInputType(inputType);
            child.setBackgroundResource(editTextBg);
        }
    }

    /**
     * 获取编辑框内容
     *
     * @return 编辑框内容
     */
    public String getEditContent() {
        return editText.getText().toString();
    }

    public OnCompleteListener listener;

    public void setOnCompleteListener(OnCompleteListener listener) {
        this.listener = listener;
    }

    public interface OnCompleteListener {
        /**
         * 完成验证码的填写
         *
         * @param content 填写内容
         */
        void onComplete(String content);
    }
}

