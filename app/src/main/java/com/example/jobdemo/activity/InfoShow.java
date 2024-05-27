package com.example.jobdemo.activity;

import static com.example.jobdemo.util.Utils.showStr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobdemo.R;
import com.example.jobdemo.bean.LaptopBean;
import com.example.jobdemo.bean.Person;
import com.example.jobdemo.databinding.ActivityInfoBinding;
import com.example.jobdemo.util.AppInfoUtils;
import com.example.jobdemo.util.DefaultVerificationDialog;
import com.example.jobdemo.util.IntToChineseNumUtil;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.ToastUtils;
import com.example.jobdemo.util.Utils;
import com.gyf.immersionbar.ImmersionBar;

import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author Administrator
 */
@AndroidEntryPoint
public class InfoShow extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "InfoShow";
    @Inject
    LaptopBean laptopBean;

    private com.example.jobdemo.databinding.ActivityInfoBinding binding;

    /**
     * 启动 InfoShow 活动，并传递参数
     *
     * @param context   上下文对象
     * @param parameter 传递给 InfoShow 活动的参数
     */
    public static void start(Context context, String parameter) {
        Intent starter = new Intent(context, InfoShow.class);
        starter.putExtra("parameter", parameter);
        context.startActivity(starter);
        //I am very tired，I don't want to do anything
        //if you have something you want to do ,just do it .
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImmersionBar.with(this).titleBar(R.id.toolbar)
                .navigationBarColor(android.R.color.transparent)
                .keyboardEnable(true)
                .init();

        if (getIntent().getStringExtra("parameter") != null) {
            String extra = getIntent().getStringExtra("parameter");
            binding.tvSha265.setText(String.format("从%s跳转过来", extra));
        }
        binding.tvSha1.setText(AppInfoUtils.sHA1(this));
        binding.tvMd5.setText(MessageFormat.format("包名: {0}", getPackageName()));

        binding.tvSha1.setOnClickListener(this);
        binding.tvMd5.setOnClickListener(this);
        binding.tvSha265.setOnClickListener(this);
        binding.tvChild.setOnClickListener(this);
        binding.btnGetCurrentTime.setOnClickListener(this);
        binding.btnBranchThread.setOnClickListener(this);
        binding.btnShowDialog.setOnClickListener(this);
        binding.close.setOnClickListener(this);
        Log.d(TAG, "包名: " + getPackageName());
        pointDate();
        binding.btnLaptop.setOnClickListener(this);
        binding.btnShowStr.setOnClickListener(v -> showStr("你好"));
        binding.btnStartNew.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditTextDemo.class);
            startActivity(intent);
        });

        // 打开百度网址
        Uri uri = Uri.parse("https://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void pointDate() {
        int[] array = new int[10];
        List<String> list = new ArrayList();
        Map<Integer, String> map = new HashMap<>(1);
        Map<String, Person> map2 = new HashMap<>(1);
        for (int i = 0; i < 10; i++) {
            array[i] = i;
            list.add(IntToChineseNumUtil.int2chineseNum(i));
            map.put(i, IntToChineseNumUtil.int2chineseNum(i));
            map2.put(IntToChineseNumUtil.int2chineseNum(i), new Person("武", i, false));
        }
        LogUtil.showArray("打印数据", array);
        LogUtil.showList("打印数据", list);
        LogUtil.showList("打印数据", map);
        LogUtil.showList("打印数据", map2);
    }

    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        binding.tvCurrentTime.setText("Calendar获取当前日期" + year + "年" + month + "月" + day + "日");
        new Thread(() -> {
            try {
                URL url = new URL("https://www.baidu.cn");//取得资源对象
                URLConnection uc = url.openConnection();//生成连接对象
                uc.connect(); //发出连接
                long ld = uc.getDate(); //取得网站日期时间
                Date date = new Date(ld); //转换为标准时间对象
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(date);
                //分别取得时间中的小时，分钟和秒，并输出
                Log.d(TAG, calendar1.get(Calendar.YEAR) + "年" + (calendar1.get(Calendar.MONTH) + 1) + "月" + calendar1.get(Calendar.DAY_OF_MONTH) + "日");
                Log.d(TAG, calendar1.get(Calendar.HOUR) + "时" + calendar1.get(Calendar.MINUTE) + "分" + calendar1.get(Calendar.SECOND) + "秒");
            } catch (Exception e) {
                Log.d(TAG, "getCurrentTime: 获取网络时间异常" + e.getMessage());
            }
        }).start();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_sha1) {
            ToastUtils.shortToast(this, "网络是否可用---");
        } else if (id == R.id.tv_md5) {
            ToastUtils.longToast(this, "长时间显示toast");
        } else if (id == R.id.tv_sha265) {
            ToastUtils.timerToast(this, "toast显示3秒", 3000);
        } else if (id == R.id.tv_child) {
            Toast.makeText(this, "点击了子view", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btn_getCurrentTime) {
            getCurrentTime();
            binding.tvCurrentDateTime.setText(Utils.getDateAndTime("MM/dd/yyyy"));
            binding.tvByFormatCurrentTime.setText(Utils.getDateAndTime("yyyy-MM-dd HH:mm:ss E a"));
        } else if (id == R.id.btn_branchThread) {
            //Android11中不能居中显示了，改用snackBar
            ToastUtils.timerToast(this, "手机IP===", 5000);
        } else if (id == R.id.btn_show_dialog) {
            DefaultVerificationDialog defaultVerificationDialog = DefaultVerificationDialog.newInstance();
            defaultVerificationDialog.show(getSupportFragmentManager(), "同意退款");
        } else if (id == R.id.close) {
            setResult(Activity.RESULT_OK, new Intent().putExtra("extra", "info返回的数据"));
            finish();
        } else if (id == R.id.btn_laptop) {
            ToastUtils.shortToast(this, laptopBean.getScreenSize() + "");
        }
    }
}