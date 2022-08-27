package com.example.jobdemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.base.RetrofitInterface;
import com.example.jobdemo.bean.PictureBean;
import com.example.jobdemo.constants.Api;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.MyRetrofitUtil;
import com.example.jobdemo.util.SPUtil;
import com.example.jobdemo.util.ToastUtils;
import com.example.jobdemo.view.LineGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDemoActivity extends BaseActivity {
    @BindView(R.id.gv)
    LineGridView gv;
    @BindView(R.id.btn_addPicture)
    Button btnAddPicture;
    private List<PictureBean> beanList = new ArrayList<>();
    private RetrofitDemoActivity.gvAdapter gvAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofitdemoactivity);
        ButterKnife.bind(this);
        gvAdapter = new gvAdapter();
        gv.setAdapter(gvAdapter);
    }


    /**
     * 获得一直新图片
     */
    private void getPicture() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_PICTURE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<PictureBean> getPicture = retrofitInterface.getPicture();
        getPicture.enqueue(new Callback<PictureBean>() {
            @Override
            public void onResponse(Call<PictureBean> call, Response<PictureBean> response) {
                PictureBean bean = response.body();
                beanList.add(bean);
                savePictureBean(bean, "pictureList");
                gvAdapter.notifyDataSetChanged();
                LogUtil.showD("线程ID===" + Looper.myLooper().getThread().getId());
            }

            @Override
            public void onFailure(Call<PictureBean> call, Throwable t) {
                LogUtil.showD("请求失败:" + t.getMessage());
            }
        });
    }

    /**
     * @return 获取保存的图片集合
     */
    private List<PictureBean> getListPictureBean(String paramName) {
        String pictureStr = (String) SPUtil.getInstance().getParam(paramName, "");
        if (TextUtils.isEmpty(pictureStr)) {
            return null;
        }
        Type type = new TypeToken<List<PictureBean>>() {
        }.getType();
        List<PictureBean> pictureBeanList = new Gson().fromJson(pictureStr, type);
        return pictureBeanList;
    }

    /**
     * @param bean 保存对象集合
     */
    private void savePictureBean(PictureBean bean, String paramName) {
        List<PictureBean> pictureBeanList = getListPictureBean(paramName);
        if (pictureBeanList == null) {
            pictureBeanList = new ArrayList<>();
        }
        pictureBeanList.add(bean);
        SPUtil.getInstance().setObjectToString(paramName, pictureBeanList);
    }

    @OnClick({R.id.btn_addPicture, R.id.btn_getCachePicture, R.id.btn_cycleInternetGetPicture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_addPicture:
                getPicture();
                break;
            case R.id.btn_getCachePicture:
                List<PictureBean> bean = getListPictureBean("pictureList");
                if (bean != null) {
                    beanList.clear();
                    beanList.addAll(bean);
                    gvAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.shortToast(this,"没有缓存的图片");
                }
                break;
            case R.id.btn_cycleInternetGetPicture:
                cycleGetPicture();
                break;
        }
    }

    /**
     * 定时循环得到图片
     */
    private void cycleGetPicture() {
        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        // 该例子发送的事件特点：延迟2s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）

        /*
         * 步骤2：每次发送数字前发送1次网络请求（doOnNext（）在执行Next事件前调用）
         * 即每隔1秒产生1个数字前，就发送1次网络请求，从而实现轮询需求
         **/
        Observable.intervalRange(1, 3, 0, 3, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtil.showD(TAG, "第 " + aLong + " 次轮询");
                        RetrofitInterface retrofitInterface = MyRetrofitUtil.INSTANCE.getRetrofitInterface();
                        Observable<PictureBean> gqbzPicture = retrofitInterface.getGQBZPicture();
                        gqbzPicture.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PictureBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(PictureBean pictureBean) {
                                LogUtil.showD("图片地址==" + pictureBean.getImgurl());
                                savePictureBean(pictureBean, "GQBZPicture");
                                String substring = pictureBean.getImgurl().substring(0, 6);
                                if (!TextUtils.isEmpty(substring) && !substring.equals("https")) {
                                    StringBuilder stringBuilder = new StringBuilder(pictureBean.getImgurl());
                                    stringBuilder.insert(4, "s");
                                    pictureBean.setImgurl(stringBuilder.toString());
                                    LogUtil.showD("新地址==" + pictureBean.getImgurl());
                                }
                                beanList.add(pictureBean);
                                gvAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtil.showD(TAG, "请求失败" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                                LogUtil.showD(TAG, "网络事件完成了");
                            }
                        });
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtil.showD(TAG, "订阅启动");
            }

            @Override
            public void onNext(Long aLong) {
                LogUtil.showD(TAG, "收到了事件==" + aLong);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.showD(TAG, "对Error事件作出响应" + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtil.showD(TAG, "对Complete事件作出响应");
            }
        });


    }

    public class gvAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return beanList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(RetrofitDemoActivity.this).inflate(R.layout.item_picture, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Glide.with(convertView.getContext()).load(beanList.get(position).getImgurl()).into(holder.ivPicture);
            return convertView;
        }


        class ViewHolder {
            @BindView(R.id.iv_picture)
            ImageView ivPicture;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
