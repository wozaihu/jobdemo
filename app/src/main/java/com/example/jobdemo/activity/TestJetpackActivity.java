package com.example.jobdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.TestjetpackactivityBinding;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestJetpackActivity extends BaseActivity {
    @BindView(R.id.iv_picture)
    ImageView ivPicture;
    private String json = "{\"status\":\"1\",\"message\":\"信息已获取到!\",\"userInfo\":[{\"ID\":17673,\"UserId\":80251292,\"ExperienceCompany\":\"瑰丽珠宝\",\"Position\":\"珠宝首饰设计\",\"ExperienceContent\":\"主要负责公司研发系列与客来石高级定制\\\"贵宝石\\\"！胸针祖母绿大套链等，擅长动物植物！\",\"StartTime\":\"2016-05\",\"EndTime\":\"2020-03\"},{\"ID\":17674,\"UserId\":80251292,\"ExperienceCompany\":\"风彩珠宝\",\"Position\":\"珠宝首饰设计\",\"ExperienceContent\":\"主要做清新设计\",\"StartTime\":\"2014-07\",\"EndTime\":\"2015-03\"},{\"ID\":17692,\"UserId\":80251292,\"ExperienceCompany\":\"瑰丽珠宝有限公司\",\"Position\":\"珠宝首饰设计\",\"ExperienceContent\":\"主要负责公司的研发系列与高级定制. 擅长动物花鸟. 布契拉提设计   小清新设计。钻石 彩宝 珍珠 等贵宝石研发系列产品 \",\"StartTime\":\"2016-05\",\"EndTime\":\"2020-03\"}]}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestjetpackactivityBinding vBinDing = TestjetpackactivityBinding.inflate(LayoutInflater.from(this));
        setContentView(vBinDing.getRoot());
        ButterKnife.bind(this);
        vBinDing.btn2.setOnClickListener(v -> {
            LogUtil.showD("viewBinDing生效了");
            String string = vBinDing.etName.getText().toString();
            if (TextUtils.isEmpty(string)) {
                ToastUtils.shortToast(TestJetpackActivity.this,"请输入内容");
            } else {
                ToastUtils.shortToast(TestJetpackActivity.this,string);
            }
        });

        try {
            JSONObject object = new JSONObject(json);
        } catch (JSONException e) {
            LogUtil.showD("异常===" + e.getMessage());
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_changePicture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_changePicture:
                break;
        }
    }
}
