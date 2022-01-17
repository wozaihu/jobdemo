package com.example.jobdemo.view_demo;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.jobdemo.R;
import com.example.jobdemo.adapter.MyExpandableAdapter;
import com.example.jobdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Administrator
 */
public class DemoExpandableListView extends BaseActivity {
    @BindView(R.id.elv_show_list_data)
    ExpandableListView elvShowListData;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandablelistview);
        ButterKnife.bind(this);
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588496489615&di=ac9376a0750213ecb9958edb1e4a256a&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F-Po3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Fdc54564e9258d109e06bdb31da58ccbf6d814dac.jpg")
                .into(ivEmpty);
        elvShowListData.setEmptyView(ivEmpty);
        String[] titles = {"动画", "音乐", "综艺", "英语单词", "技术"};
        String[][] contents = {{"火影忍者", "海贼王", "名侦探柯南", "秦时明月", "旋风管家"}
                , {"觉醒", "雨蝶", "我的梦", "明天会更好"}
                , {"王牌对王牌", "极限挑战", "歌手", "奔跑吧", "你是最棒的", "无敌大冒险", "舞动青春", "密室逃脱之无处可逃"}
                , {"welcome", "hello", "world", "big_nose_pig", "if you ", "how do you do", "thanks", "my brother"}
                , {"flutter", "kotlin", "python", "java", "gradle", "c", "switch"}
        };
        elvShowListData.setAdapter(new MyExpandableAdapter(this, titles, contents));
    }
}
