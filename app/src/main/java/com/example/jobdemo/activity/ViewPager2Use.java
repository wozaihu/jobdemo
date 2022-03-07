package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jobdemo.R;
import com.example.jobdemo.adapter.ViewPagerAdapter;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.Viewpager2useBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Viewpager2useBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        list.add("页面一");
        list.add("页面二");
//        list.add("页面三");
//        list.add("页面四");
        binding.vp2Use.setAdapter(new ViewPagerAdapter(this, list, binding.vp2Use));
        callback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        };
        binding.vp2Use.registerOnPageChangeCallback(callback);

        new TabLayoutMediator(binding.tabLayout, binding.vp2Use, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //这样可实现文字选中变大，但下划线适应文字宽度有问题，因为下划线是根据view的宽度定的
                TextView view = (TextView) LayoutInflater.from(ViewPager2Use.this).inflate(R.layout.tab_layout_text, null);
                view.setText(list.get(position));
                tab.setCustomView(view);
            }
        }).attach();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                changeTabTextView(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                changeTabTextView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // 选中第一个
        changeTabTextView(binding.tabLayout.getTabAt(0), true);
    }

    /**
     * 字体加粗变颜色
     *
     * @param tab
     * @param isBold
     */
    public void changeTabTextView(TabLayout.Tab tab, boolean isBold) {
        TextView textView = (TextView) tab.getCustomView();
        if (isBold) {
            textView.setTextAppearance(this, R.style.TabLayoutBoldTextSize);
        } else {
            textView.setTextAppearance(this, R.style.TabLayoutNormalTextSize);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.vp2Use.unregisterOnPageChangeCallback(callback);
    }
}
