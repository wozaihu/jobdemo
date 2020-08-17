package com.example.jobdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobdemo.activity.Activity_Privacy_Tip;
import com.example.jobdemo.activity.BiuldVariantDetails;
import com.example.jobdemo.activity.CameraDemo;
import com.example.jobdemo.activity.DialogDemo;
import com.example.jobdemo.activity.InfoShow;
import com.example.jobdemo.activity.PopupWindowDemo;
import com.example.jobdemo.activity.WidgetViewDemo;
import com.example.jobdemo.animation.GroupAnimation;
import com.example.jobdemo.animation.MyAlpha;
import com.example.jobdemo.animation.MyFrameAnimatoin;
import com.example.jobdemo.animation.MyInterpolator;
import com.example.jobdemo.animation.MyObjectAnimator;
import com.example.jobdemo.animation.MyRotate;
import com.example.jobdemo.animation.MyScaleAnimation;
import com.example.jobdemo.animation.MyTranslateAnimation;
import com.example.jobdemo.animation.MyValueAnimation;
import com.example.jobdemo.animation.VeiwGroupAnimation;
import com.example.jobdemo.baidu_map.BaiduMapDemo;
import com.example.jobdemo.bean.MainOnDestroy;
import com.example.jobdemo.exercise.RecyclerView_Demo;
import com.example.jobdemo.notification.SendNotification;
import com.example.jobdemo.util.ChannelUtil;
import com.example.jobdemo.view_demo.DemoExpandableListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.rv_demo_instance)
    RecyclerView rvDemoInstance;
    private static String[] demoName = {
            "信息显示"
            , "Notification加按钮"
            , "TranslateAnimation"
            , "ScaleAnimation"
            , "RotateAnimation"
            , "AlphaAnimation透明动画"
            , "ViewGroupAnimation"
            , "GroupAnimation"
            , "ValueAnimation"
            , "ObjectAnimator动画"
            , "Interpolator"
            , "FrameAnimation"
            , "ExpandableListView"
            , "recyclerView瀑布流"
            , "集成百度地图展示"
            , "调用相机"
            , "dialogDEMO"
            , "自定义viewDemo"
            , "PopupWindow使用"
            , "产品变种==" + BuildConfig.FLAVOR + ";----编译模式==" + BuildConfig.BUILD_TYPE
            , MyApplication.getApplication().getString(R.string.webview)
    };
    private List<Class> activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonTest();
        ButterKnife.bind(this);
        getListActivity();
        MainActivityAdapter adapter = new MainActivityAdapter(demoName, activityList);
        rvDemoInstance.setLayoutManager(new LinearLayoutManager(this));
        rvDemoInstance.setAdapter(adapter);
        EventBus.getDefault().register(this);
        String channel = ChannelUtil.getChannel(getApplicationContext());
        Toast.makeText(getApplicationContext(), "当前渠道：" + channel, Toast.LENGTH_SHORT).show();
        checkLOCATIONPermission();
        Log.d(TAG, "编译版本是不是dubug: " + BuildConfig.DEBUG);
        if (false) {
            return;
        }
        Toast.makeText(this, "编译版本是不是dubug: " + BuildConfig.DEBUG, Toast.LENGTH_LONG).show();
    }

    private void jsonTest() {
        String json = "{\"status\":\"1\",\"message\":\"获取成功!\",\"userInfo\":[{\"JewelryType\":1,\"ProductId\":193,\"ProductName\":\"投资了\",\"ProductDes\":\"哦了呜呜呜我\",\"IsPutaway\":1,\"ProductTypeId\":20,\"ProductTypeName\":\"黄金\",\"CommodityId\":68,\"CommodityName\":\"手链\",\"ProductSn\":\"\",\"MainProductSn\":\"\",\"MainProductImg\":\"http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Product/Attachment/80156891/20200723/Product202007231150229790.png\",\"SellerRemark\":\"\",\"Status\":1,\"UserId\":\"80156891\",\"VideoId\":\"\",\"CoverURL\":\"http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Product/Attachment/80156891/20200723/Product202007231150229790.png\",\"CraftEmploy\":\"\",\"Key\":\"\",\"FittingName\":\"\",\"FittingQuantity\":0,\"FittingTotalPrice\":\"0\",\"FittingWeight\":\"0\",\"OrderModelCount\":0,\"CashCommodityModelCount\":1,\"AppraiseCount\":0,\"ProductImgList\":[{\"ID\":1871,\"Path\":\"http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Product/Attachment/80156891/20200723/Product202007231150229790.png\",\"Sort\":1}],\"TryOnImgList\":[],\"BrowseCount\":7,\"CollectCount\":0,\"TagList\":[\"现货一口价\"],\"Typelist\":[{\"Type\":1,\"TypeName\":\"一口价\",\"TypeDes\":\"¥0.01\"}],\"SpecificationDes\":\"现货 足金990\",\"CartCount\":1,\"DynamicProperty\":[{\"产品类别\":\"黄金\"},{\"产品品名\":\"手链\"},{\"材质\":\"足金990\"}],\"ReviewProductList\":[],\"HeadPath\":\"http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Center/Attachment/DefaultBoyImg1.png\",\"Nickname\":\"虚拟录取\",\"DescribeScore\":\"10\",\"LogisticScore\":\"10\",\"ServiceAttitudeScore\":\"10\",\"SynthesiScore\":\"10\",\"DescribeScoreValue\":\"高\",\"LogisticScoreValue\":\"高\",\"ServiceAttitudeScoreValue\":\"高\",\"IsCollectProduct\":0,\"IsCollectBIZ\":1,\"YouLikeList\":[],\"ServiceTel\":\"075525509436\"}]}";
        try {
            JSONObject object = new JSONObject(json);
            if (object.has("message")) {

                Log.d(TAG, "message==== " + object.get("message"));
            }
            if (object.has("ProductName")) {
                JSONArray userInfo = object.getJSONArray("userInfo");
                JSONObject jsonObject = userInfo.getJSONObject(0);
                Log.d(TAG, "ProductName==== " + jsonObject.get("ProductName"));
                Log.d(TAG, "直接取ProductName==== " + object.get("ProductName"));

            }

        } catch (Exception e) {
            Log.d(TAG, "解析JSon异常: " + e.getMessage());
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setStarteEndAnimation(String s) {
        Log.d(TAG, "setStarteEndAnimation:----- " + s);
//        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
//        overridePendingTransition(R.anim.out_right, R.anim.in_left);
    }

    private void getListActivity() {
        activityList = new ArrayList<>();
        activityList.add(InfoShow.class);
        activityList.add(SendNotification.class);
        activityList.add(MyTranslateAnimation.class);
        activityList.add(MyScaleAnimation.class);
        activityList.add(MyRotate.class);
        activityList.add(MyAlpha.class);
        activityList.add(VeiwGroupAnimation.class);
        activityList.add(GroupAnimation.class);
        activityList.add(MyValueAnimation.class);
        activityList.add(MyObjectAnimator.class);
        activityList.add(MyInterpolator.class);
        activityList.add(MyFrameAnimatoin.class);
        activityList.add(DemoExpandableListView.class);
        activityList.add(RecyclerView_Demo.class);
        activityList.add(BaiduMapDemo.class);
        activityList.add(CameraDemo.class);
        activityList.add(DialogDemo.class);
        activityList.add(WidgetViewDemo.class);
        activityList.add(PopupWindowDemo.class);
        activityList.add(BiuldVariantDetails.class);
        activityList.add(Activity_Privacy_Tip.class);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new MainOnDestroy());
        EventBus.getDefault().unregister(this);
        Log.d(MyApplication.TAG, "onDestroy: MainActivity");
    }


    private void checkLOCATIONPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 001);
            }
        }
    }
}
