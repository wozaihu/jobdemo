package com.example.jobdemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;

/**
 * @author Administrator
 * @date 2020/7/21 13:48
 * @desc
 */
public class ActivityPublicWebView extends BaseActivity {

    com.tencent.smtt.sdk.WebView webContent;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_tip);
        webContent = findViewById(R.id.web_content);
        webContent.getSettings().setUseWideViewPort(true);
        webContent.getSettings().setLoadWithOverviewMode(true);
        webContent.loadUrl("file:///android_asset/xieyi.html");
        String a = "";
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webContent != null && webContent.getParent() != null) {
            try {
                ((ViewGroup) webContent.getParent()).removeView(webContent);
                webContent.destroy();
                webContent = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
