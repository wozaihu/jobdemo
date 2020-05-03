package com.example.jobdemo.animation;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jobdemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VeiwGroupAnimation extends AppCompatActivity {
    private static final String TAG = "VeiwGroupAnimation";
    @BindView(R.id.lv_animation)
    ListView lvAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup_animation);
        ButterKnife.bind(this);
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stringList.add("第" + i + "行");
        }
        lvAnimation.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,stringList));

        lvAnimation.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(TAG, "onScrollStateChanged: 拖动了");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(TAG, "firstVisibleItem "+firstVisibleItem+"----------visibleItemCount"+visibleItemCount+"---------totalItemCount"+totalItemCount);
                Log.d(TAG, "具体内容firstVisibleItem "+stringList.get(firstVisibleItem)+"----------visibleItemCount"+stringList.get(visibleItemCount)+"---------totalItemCount"+stringList.get(totalItemCount-1));
            }
        });
    }
}
