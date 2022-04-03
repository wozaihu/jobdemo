package com.example.jobdemo.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jobdemo.R;
import com.example.jobdemo.adapter.Vp2FragmentAdapter;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.Viewpager2useBinding;
import com.example.jobdemo.fragment.Vp2Fragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2021/4/6 16:59
 */
public class ViewPager2Use extends BaseActivity {

    private Viewpager2useBinding binding;
    private ViewPager2.OnPageChangeCallback callback;
    private List<String> list;
    private List<Fragment> fragments;
    private float downX, downY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Viewpager2useBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
//        setVp2Slop();
        list = new ArrayList<>();
        fragments = new ArrayList<>();
        list.add("页面一");
        list.add("页面二");
        list.add("页面三");
        list.add("页面四");
        fragments.add(Vp2Fragment.newInstance(0));
        fragments.add(Vp2Fragment.newInstance(1));
        fragments.add(Vp2Fragment.newInstance(2));
        fragments.add(Vp2Fragment.newInstance(3));
        binding.vp2Use.setAdapter(new Vp2FragmentAdapter(this, fragments));
        callback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        };
        binding.vp2Use.registerOnPageChangeCallback(callback);

        new TabLayoutMediator(binding.tabLayout, binding.vp2Use, (tab, position) -> {
            //这样可实现文字选中变大，但下划线适应文字宽度有问题，因为下划线是根据view的宽度定的
            TextView view = (TextView) LayoutInflater.from(ViewPager2Use.this).inflate(R.layout.tab_layout_text, null);
            view.setText(list.get(position));
            tab.setCustomView(view);
        }).attach();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabTextView(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabTextView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // 选中第一个
        changeTabTextView(binding.tabLayout.getTabAt(0), true);
    }

    private void setVp2Slop() {
        try {
            final Field recyclerViewField = ViewPager2.class.getDeclaredField("mRecyclerView");
            recyclerViewField.setAccessible(true);

            final RecyclerView recyclerView = (RecyclerView) recyclerViewField.get(binding.vp2Use);

            final Field touchSlopField = RecyclerView.class.getDeclaredField("mTouchSlop");
            touchSlopField.setAccessible(true);

            final int touchSlop = (int) touchSlopField.get(recyclerView);
            //6 is empirical value
            touchSlopField.set(recyclerView, touchSlop * 4);
        } catch (Exception ignore) {
        }
    }

    /**
     * 字体加粗变颜色
     *
     * @param tab    TabLayout
     * @param isBold 是否
     */
    public void changeTabTextView(TabLayout.Tab tab, boolean isBold) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            TextView textView = (TextView) tab.getCustomView();
            if (isBold && textView != null) {
                textView.setTextAppearance(R.style.TabLayoutBoldTextSize);
            } else if (textView != null) {
                textView.setTextAppearance(R.style.TabLayoutNormalTextSize);
            }
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            downX = ev.getX();
            downY = ev.getY();
        } else if (action == MotionEvent.ACTION_UP) {
            float moveX = ev.getX();

            float moveY = ev.getY();

            float xDiff = Math.abs(moveX - downX);

            float yDiff = Math.abs(moveY - downY);

            double squareRoot = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

            //滑动的角度
            int yAngle = Math.round((float) (Math.asin(yDiff / squareRoot) / Math.PI * 180));

            int xAngle = Math.round((float) (Math.asin(xDiff / squareRoot) / Math.PI * 180));
            //滑动角度是否大于45du
            float slideAngle = 45;
            boolean isMeetSlidingYAngle = yAngle > slideAngle;
            //滑动角度是否大于45du
            boolean isMeetSlidingXAngle = xAngle > slideAngle;

            boolean isSlideUp = moveY < downY && isMeetSlidingYAngle;

            boolean isSlideDown = moveY > downY && isMeetSlidingYAngle;

            boolean isSlideLeft = moveX < downX && isMeetSlidingXAngle;

            boolean isSlideRight = moveX > downX && isMeetSlidingXAngle;

            if (isSlideUp) {
                Log.d("orientation", "向上滑动");

            } else if (isSlideDown) {
                Log.d("orientation", "向下滑动");

            } else if (isSlideLeft) {
                Log.d("orientation", "向左边滑动");

            } else if (isSlideRight) {
                Log.d("orientation", "向右边滑动");
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.vp2Use.unregisterOnPageChangeCallback(callback);
    }
}
