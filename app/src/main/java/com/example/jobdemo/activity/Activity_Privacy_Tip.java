package com.example.jobdemo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.core.app.ActivityCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.view.LollipopFixedWebView;

/**
 * @author Administrator
 * @date 2020/7/21 13:48
 * @desc
 */
public class Activity_Privacy_Tip extends BaseActivity {
    //    @BindView(R.id.web_content)
    LollipopFixedWebView webContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_tip);
//        ButterKnife.bind(this);
        webContent = findViewById(R.id.web_content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//版本判断
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
            }
        }


//一个权限没有，就一次申请所有所需的权限，这样可以在打开应用的时候获得所有权限
//        webContent = findViewById(R.id.web_content);
        WebSettings settings = webContent.getSettings();

        /**
         * 启用mixed content    android 5.0以上默认不支持Mixed Content
         *
         * 5.0以上允许加载http和https混合的页面(5.0以下默认允许，5.0+默认禁止)
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webContent.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 设置与Js交互的权限
        //开启JavaScript支持
        settings.setJavaScriptEnabled(true);
        // 支持缩放
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        settings.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
//         是否允许通过file url加载的Javascript读取本地文件，默认值 false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(false);
        }
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
//        webContent.loadData(null, "text/html; charset=UTF-8", null);
        ; // 加载定义的代码，并设定编码格式和字符集。
        webContent.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        webContent.loadUrl("http://api-home-beta.51djt.com/share/MicroMallIndex");
        //这一步是针对vivo 会出现乱码的情况  小米也有几率出现
        //访问网页
        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        webContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //Android 8.0以下版本的需要返回true 并且需要loadUrl()
                if (Build.VERSION.SDK_INT < 26) {
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                handler.proceed();// 接受所有网站的证书
            }

        });

        webContent.setWebChromeClient(new WebChromeClient(){

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            }
        });

    }


}
