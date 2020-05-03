package com.example.jobdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.jobdemo.bean.Point;
import com.example.jobdemo.evaluator.PointEvaluator;

import androidx.annotation.Nullable;

public class MyCircle extends View {
    public static final float RADIUS = 70f;
    private Paint paint; //画笔
    private Point currentPoint;  //当前坐标点

    public MyCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, paint);

            Point startPoint = new Point(RADIUS, RADIUS);
            Point endPoint = new Point(700, 1000);

            ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
            animator.setDuration(5000);
            animator.setRepeatCount(3);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentPoint = (Point) animation.getAnimatedValue();
                    invalidate();
                }
            });

            animator.start();
        } else {
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, paint);
        }
    }
}
