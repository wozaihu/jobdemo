package com.example.jobdemo.notification;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.view.LooperLayoutManager;
import com.example.jobdemo.view.SlidingIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendNotification extends BaseActivity {
    @BindView(R.id.iv_http)
    ImageView ivHttp;
    @BindView(R.id.btn_show_picture)
    Button btnShowPicture;
    @BindView(R.id.rv_loop_picture)
    RecyclerView rvLoopPicture;
    String[] pictures = {"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3047715767,2472265054&fm=26&gp=0.jpg"
            , "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1635852385,1329179566&fm=26&gp=0.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587410298519&di=4537e60e8822350ddb9748aa5560dfc0&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1209%2F07%2Fc0%2F13694092_1346987078266.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587410298519&di=b0414e1b60124c54392284f87bf1c4e7&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Ffaf2b2119313b07e399c16920cd7912397dd8c03.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587410362405&di=011dc9c4d7c829f68c2598d738be3261&imgtype=0&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D4048229144%2C4287653608%26fm%3D214%26gp%3D0.jpg"

    };
    @BindView(R.id.vp_picture)
    ViewPager vpPicture;
    @BindView(R.id.si_dot)
    SlidingIndicator siDot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        Log.d("点击测试", "onCreate: ===SendNotification");
        LooperLayoutManager layoutManager = new LooperLayoutManager();
        layoutManager.setLooperEnable(true);
        rvLoopPicture.setLayoutManager(layoutManager);
        rvLoopPicture.setAdapter(new LoopAdapter());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("点击测试", "onRestart: ===SendNotification");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("点击测试", "onResume: ===SendNotification");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("点击测试", "onPause: ===SendNotification");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("点击测试", "onStop: ===SendNotification");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("点击测试", "onDestroy: ===SendNotification");
    }

    @OnClick({R.id.iv_http, R.id.btn_show_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_http:
                break;
            case R.id.btn_show_picture:
                break;
        }
    }

    public class LoopAdapter extends RecyclerView.Adapter<LoopViewholder> {

        @NonNull
        @Override
        public LoopViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(SendNotification.this).inflate(R.layout.item_picture, parent, false);
            return new LoopViewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LoopViewholder holder, int position) {
            Glide.with(SendNotification.this).load(pictures[position]).into(holder.iv_picture);
        }

        @Override
        public int getItemCount() {
            return pictures.length;
        }
    }

    public class LoopViewholder extends RecyclerView.ViewHolder {
        ImageView iv_picture;

        public LoopViewholder(@NonNull View itemView) {
            super(itemView);
            iv_picture = itemView.findViewById(R.id.iv_picture);
        }
    }
}
