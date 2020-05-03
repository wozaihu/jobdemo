package com.example.jobdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GradualCircleColor extends View {
    public static final float RADIUS = 100f;
    private final Paint mPaint;
    private String backGroundColer;

    public String getBackGroundColer() {
        return backGroundColer;
    }

    public void setBackGroundColer(String backGroundColer) {
        this.backGroundColer = backGroundColer;
        mPaint.setColor(Color.parseColor(backGroundColer));
        invalidate();
    }


    public GradualCircleColor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawCircle(500, 500, RADIUS, mPaint);
    }
}
