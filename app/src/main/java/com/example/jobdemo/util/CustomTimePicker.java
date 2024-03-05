package com.example.jobdemo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import com.example.jobdemo.R;

import java.util.ArrayList;
import java.util.List;

public class CustomTimePicker extends FrameLayout {

    private final NumberPicker hourPicker;
    private final NumberPicker minutePicker;

    public CustomTimePicker(Context context) {
        this(context, null);
    }

    @SuppressLint("DefaultLocale")
    public CustomTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.custom_time_picker, this);

        hourPicker = findViewById(R.id.hour_picker);
        minutePicker = findViewById(R.id.minute_picker);

        // 设置小时选择器范围
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);
        hourPicker.setFormatter(value -> String.format("%02d", value));

        // 设置分钟选择器范围和间隔
        List<String> displayedValues = new ArrayList<>();
        for (int i = 0; i < 60; i += 5) {
            displayedValues.add(String.format("%02d", i));
        }
        String[] values = displayedValues.toArray(new String[0]);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(values.length - 1);
        minutePicker.setDisplayedValues(values);
    }

    // 获取选中的时间（小时）
    @SuppressLint("DefaultLocale")
    public String getSelectedHour() {
        return String.format("%02d", hourPicker.getValue());
    }

    // 获取选中的分钟
    public String getSelectedMinute() {
        String selectedMinute = minutePicker.getDisplayedValues()[minutePicker.getValue()];
        return selectedMinute;
    }

    // 设置选中的时间和分钟
    public void setSelectedTime(int hour, int minute) {
        // 处理小时
        if (hour < 0 || hour > 23) {
            hour = 0;
        }
        hourPicker.setValue(hour);

        // 处理分钟
        int adjustedMinute = adjustMinute(minute);
        minutePicker.setValue(getMinuteIndex(adjustedMinute));
    }

    // 根据规则调整分钟数
    private int adjustMinute(int minute) {
        if (minute % 5 != 0) {
            minute = ((minute / 5) * 5); // 向下取5的倍数
        }
        return minute;
    }

    // 获取分钟数在 displayedValues 中的索引
    @SuppressLint("DefaultLocale")
    private int getMinuteIndex(int minute) {
        String adjustedMinuteString = String.format("%02d", minute);
        String[] displayedValues = minutePicker.getDisplayedValues();
        for (int i = 0; i < displayedValues.length; i++) {
            if (displayedValues[i].equals(adjustedMinuteString)) {
                return i;
            }
        }
        // 默认返回 0，即分钟为00
        return 0;
    }

}

