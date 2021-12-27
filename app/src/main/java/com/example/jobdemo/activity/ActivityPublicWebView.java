package com.example.jobdemo.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;

import androidx.core.app.ActivityCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @author Administrator
 * @date 2020/7/21 13:48
 * @desc
 */
public class ActivityPublicWebView extends BaseActivity {
    //    @BindView(R.id.web_content)
    com.tencent.smtt.sdk.WebView webContent;
    private com.tencent.smtt.sdk.WebSettings settings;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_tip);
        webContent = findViewById(R.id.web_content);
        settings = webContent.getSettings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//版本判断
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
            }
        }


        /**
         * 启用mixed content    android 5.0以上默认不支持Mixed Content
         * 5.0以上允许加载http和https混合的页面(5.0以下默认允许，5.0+默认禁止)
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webContent.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setPluginsEnabled(true);           //支持插件
        settings.setJavaScriptEnabled(true);        //开启JavaScript支持
        settings.setSupportZoom(true);              // 支持缩放
        settings.setUseWideViewPort(true);          //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true);     // 缩放至屏幕的大小
        settings.setDomStorageEnabled(true);        //设置DOM Storage缓存
        settings.supportMultipleWindows();//多窗口
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowContentAccess(true);   // 是否可访问Content Provider的资源，默认值 true
        settings.setAllowFileAccess(true);      // 是否可访问本地文件，默认值 true
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webContent.loadUrl("http://api-home-beta.51djt.com/share/MicroMallIndex");
//        webContent.loadUrl("https://www.baidu.com");
        webContent.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                if (Build.VERSION.SDK_INT < 26) {
                    webView.loadUrl(s);
                    return true;
                }
                return false;
            }
        });

        webContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
                return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
            }

            @Override
            public void openFileChooser(ValueCallback<Uri> valueCallback, String s, String s1) {
                super.openFileChooser(valueCallback, s, s1);
            }
        });
    }

    @Override
    protected void onDestroy() {

        try{
            if (webContent != null) {
                webContent.stopLoading();
                webContent.removeAllViewsInLayout();
                webContent.removeAllViews();
//                CookieSyncManager.getInstance().stopSync();
                webContent.destroy();
                webContent = null;
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        } finally {
            super.onDestroy();
        }
    }

}
