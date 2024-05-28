package com.example.jobdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.R2;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.PersonBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowContent extends BaseActivity {
    @BindView(R2.id.tv_age)
    TextView tvAge;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.img_heat_portrait)
    ImageView imgHeatPortrait;
    @BindView(R2.id.lv_hobby)
    ListView lvHobby;
    @BindView(R2.id.tv_gender)
    TextView tvGender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcontent);
        ButterKnife.bind(this);
        PersonBean personBean = getIntent().getParcelableExtra("personBean");
        tvName.setText(getResources().getString(R.string.name) + personBean.getName());
        tvAge.setText(getResources().getString(R.string.age) + personBean.getAge());
        imgHeatPortrait.setImageBitmap(personBean.getHeadPortrait());
        lvHobby.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, personBean.getHobby()));
    }


    @OnClick({R.id.tv_age, R.id.tv_gender, R.id.tv_name, R.id.img_heat_portrait})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_age:
                break;
            case R.id.tv_gender:
                if (tvName.getVisibility() == View.VISIBLE) {
                    tvName.setVisibility(View.GONE);
                } else {
                    tvName.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_name:
                startActivity(new Intent(this, FlowLayoutDemo.class));
                break;
            case R.id.img_heat_portrait:
                break;
        }
    }
}
