package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity_List extends BaseActivity {
    @BindView(R.id.lv)
    ListView lv;
    private String[] name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        name = new String[]{"李白"};
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.mipmap.a1);
        lv.addHeaderView(imageView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Activity_List.this,"点击了==="+i,Toast.LENGTH_SHORT).show();
            }
        });
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return name.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view = LayoutInflater.from(Activity_List.this).inflate(R.layout.item_expandable_child, viewGroup, false);
                TextView text1 = view.findViewById(R.id.tv_child_content);
                text1.setText(name[i]);
                return text1;
            }
        });
    }
}
