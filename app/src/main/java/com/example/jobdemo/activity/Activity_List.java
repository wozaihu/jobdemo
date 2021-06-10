package com.example.jobdemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_List extends BaseActivity {
    private static final String TAG = "Activity_List";
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_addName)
    Button btnAddName;
    private List<String> name;
    private NaemAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        name = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            name.add("第" + i + "个人");
        }
//        ImageView imageView = new ImageView(this);
//        imageView.setBackgroundResource(R.mipmap.a1);
//        lv.addHeaderView(imageView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Activity_List.this, "点击了===" + i, Toast.LENGTH_SHORT).show();
            }
        });
        adapter = new NaemAdapter();
        lv.setAdapter(adapter);
    }


    /**
     * @param view switch支持常量，现在直接用ID会报警告，studio4.1以后还是使用viewBinding吧，butterKnife不更新了
     */
    @OnClick({R.id.btn_addName, R.id.btn_default})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_addName:
                Random random = new Random();
                int i = random.nextInt();
                name.add("李白----" + i);
                adapter.setItemIsmMore(true);
                break;
            case R.id.btn_default:
                adapter.setItemIsmMore(false);
                break;
        }
    }


    public class NaemAdapter extends BaseAdapter {
        private boolean isMore = false;

        public void setItemIsmMore(boolean isMore) {
            this.isMore = isMore;
            notifyDataSetChanged();
        }


        @Override
        public int getCount() {
            //如果是更多就直接返回，如果不是，大于6个就返回6个，小于6就返回实际的个数
            Log.d(TAG, "isMore==" + isMore + "---------name.size==" + name.size());
            return isMore ? name.size() : name.size() > 6 ? 6 : name.size();
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
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(Activity_List.this).inflate(R.layout.item_expandable_child, viewGroup, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.tvChildContent.setText(name.get(i));
            return view;
        }

        class ViewHolder {
            @BindView(R.id.tv_child_content)
            TextView tvChildContent;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
