package com.example.jobdemo.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobdemo.databinding.ActivityDatePickerAndTimePickerBinding;
import com.example.jobdemo.kotlin_code.utils.KtUtilsKt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Administrator
 */
public class DatePickerAndTimePicker extends AppCompatActivity {
    public static final String DEFAULT_TIME = "2022-12-7 2:8:49";

    public static void start(Context context) {
        Intent starter = new Intent(context, DatePickerAndTimePicker.class);
        context.startActivity(starter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatePickerAndTimePickerBinding binding = ActivityDatePickerAndTimePickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.timePicker.setIs24HourView(true);
        binding.datePicker.setMinDate(System.currentTimeMillis());
        resizePicker(binding.datePicker);
        resizePicker(binding.timePicker);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        try {
            binding.datePicker.setMaxDate(Objects.requireNonNull(simpleDateFormat.parse("2030-12-31 23:59")).getTime());
            long dateAndTime = System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dateAndTime);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            binding.tvParse.setText("Calendar获取当前日期" + year + "年" + month + "月" + day + "日" + hour + ":" + minute);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        binding.getTime.setOnClickListener(v -> {
            int year = binding.datePicker.getYear();
            int month = binding.datePicker.getMonth()+1;
            int day = binding.datePicker.getDayOfMonth();
            String hour = binding.customTimePicker.getSelectedHour();
            String minute = binding.customTimePicker.getSelectedMinute();
            binding.tvParse.setText("Calendar获取当前日期" + year + "年" + month + "月" + day + "日" + hour + ":" + minute);
        });

        binding.setTime.setOnClickListener(v -> {
            long dateAndTime = 0;
            try {
                dateAndTime = Objects.requireNonNull(simpleDateFormat.parse(DEFAULT_TIME)).getTime();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dateAndTime);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            binding.datePicker.init(year, month, day, null);
            binding.customTimePicker.setSelectedTime(hour, minute);
        });
    }

    /**
     * 调整FrameLayout的大小,DatePicker和TimePicker继承自FrameLayout
     */
    private void resizePicker(FrameLayout tp) {
        List<NumberPicker> npList = findNumberPicker(tp);
        //找到组成的NumberPicker
        for (NumberPicker np : npList) {
            //调整每个NumberPicker的宽度
            resizeNumberPicker(np);
        }
    }

    /**
     * 得到viewGroup 里面的numberPicker组件
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> npList = new ArrayList<>();
        View child;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                } else if (child instanceof LinearLayout) {
                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    /**
     * 调整numberPicker大小
     */
    private void resizeNumberPicker(NumberPicker np) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(10, 0, 10, 0);
        np.setLayoutParams(params);
    }
}