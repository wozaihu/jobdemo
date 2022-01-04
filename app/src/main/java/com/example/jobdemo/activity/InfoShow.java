package com.example.jobdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobdemo.R;
import com.example.jobdemo.bean.Person;
import com.example.jobdemo.util.AppInfoUtils;
import com.example.jobdemo.util.DefaultVerificationDialog;
import com.example.jobdemo.util.IntToChineseNumUtil;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.MyActivityManager;
import com.example.jobdemo.util.ToastUtils;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoShow extends AppCompatActivity {
    private static final String TAG = "InfoShow";
    @BindView(R.id.tv_sha1)
    TextView tvSha1;
    @BindView(R.id.tv_md5)
    TextView tvMd5;
    @BindView(R.id.tv_sha265)
    TextView tvSha265;
    @BindView(R.id.tv_child)
    TextView tvChild;
    @BindView(R.id.tv_CurrentTime)
    TextView tvCurrentTime;

    public static void start(Context context, String parameter) {
        Intent starter = new Intent(context, InfoShow.class);
        starter.putExtra("parameter", parameter);
        context.startActivity(starter);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        Log.d("点击测试", "onCreate: ===InfoShow");
        if (getIntent().getStringExtra("parameter") != null) {
            String extra = getIntent().getStringExtra("parameter");
            tvSha265.setText("从" + extra + "跳转过来");
        }
        tvSha1.setText(AppInfoUtils.sHA1(this));
        tvMd5.setText("包名: " + getPackageName());
        Log.d(TAG, "包名: " + getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getNavigationBarColor();
        }
        pointDate();
    }

    private void pointDate() {
        int[] array = new int[10];
        List list = new ArrayList();
        Map<Integer, String> map = new HashMap<>();
        Map<String, Person> map2 = new HashMap<>();
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

    @OnClick({R.id.tv_sha1, R.id.tv_md5, R.id.tv_sha265, R.id.tv_child, R.id.btn_getCurrentTime, R.id.btn_branchThread
            , R.id.btn_show_dialog, R.id.close
    })
    public void onViewClicked(View view) {
        // FIXME: 2021/11/16 标识处代码需要修正
        int id = view.getId();
        if (id == R.id.tv_sha1) {
            ToastUtils.shortToast("网络是否可用---");
        } else if (id == R.id.tv_md5) {
            ToastUtils.longToast("长时间显示toast");
        } else if (id == R.id.tv_sha265) {
            ToastUtils.timerToast("toast显示3秒", 3000);
        } else if (id == R.id.tv_child) {
            Toast.makeText(this, "点击了子view", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btn_getCurrentTime) {
            getCurrentTime();
        } else if (id == R.id.btn_branchThread) {
            //Android11中不能居中显示了，改用snackBar
            ToastUtils.timerToast("手机IP==="+ MyActivityManager.getInstance().getCurrentActivity().getClass().getSimpleName(), 5000);
        } else if (id == R.id.btn_show_dialog) {
            DefaultVerificationDialog defaultVerificationDialog = DefaultVerificationDialog.newInstance();
            defaultVerificationDialog.show(getSupportFragmentManager(), "同意退款");
        } else if (id == R.id.close) {
            setResult(Activity.RESULT_OK, new Intent().putExtra("extra", "info返回的数据"));
            finish();
        }
    }

    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        tvCurrentTime.setText("Calendar获取当前日期" + year + "年" + month + "月" + day + "日");
        new Thread(new Runnable() {
            @Override
            public void run() {
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
            }
        }).start();

    }
}