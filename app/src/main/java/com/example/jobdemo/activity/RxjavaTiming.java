package com.example.jobdemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.PictureBean;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.MyRetrofitUtil;
import com.example.jobdemo.util.ToastUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

/**
 * 使用Rxjava做一个定时器
 */
public class RxjavaTiming extends BaseActivity {
    @BindView(R.id.et_timingNumber)
    EditText etTimingNumber;
    @BindView(R.id.tv_showTiming)
    TextView tvShowTiming;
    @BindView(R.id.btn_startTiming)
    Button btnStartTiming;
    @BindView(R.id.tv_showCycleNumber)
    TextView tvShowCycleNumber;
    int i = 0;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.et_gender)
    EditText etGender;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.tv_showInfo)
    TextView tvShowInfo;
    private Observable<CharSequence> observableName;
    private Observable<CharSequence> observableAge;
    private Observable<CharSequence> observableGender;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjavatiming);
        ButterKnife.bind(this);
        etTimingNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && String.valueOf(s.charAt(0)).equals("0")) {
                    etTimingNumber.setText("");
                }
            }
        });
        /*
         * 为每个EditText设置被观察者，用于发送监听事件
         * 说明：
         * 1. 此处采用了RxBinding：RxTextView.textChanges(name) = 对对控件数据变更进行监听（功能类似TextWatcher），需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
         * 2. 传入EditText控件，点击任1个EditText撰写时，都会发送数据事件 = Function3（）的返回值（下面会详细说明）
         * 3. 采用skip(1)原因：跳过 一开始EditText无任何输入时的空值
         **/
        observableName = RxTextView.textChanges(etName).skip(1);
        observableAge = RxTextView.textChanges(etAge).skip(1);
        observableGender = RxTextView.textChanges(etGender).skip(1);
        combineJudge();
    }

    @OnClick({R.id.btn_startTiming, R.id.btn_when, R.id.btn_nestRequest, R.id.btn_mergeRequest, R.id.btn_commit, R.id.btn_reTryWhen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_startTiming:
                if (TextUtils.isEmpty(etTimingNumber.getText().toString())) {
                    ToastUtils.shortToast(this,getResources().getString(R.string.inputTimingNumber_Please));
                } else {
                    setTiming(etTimingNumber.getText().toString());
                }
                break;
            case R.id.btn_when:
                whenCycle();
                break;
            case R.id.btn_nestRequest:
                nestRequest();
                break;
            case R.id.btn_mergeRequest:
                mergeRequest();
                break;
            case R.id.btn_commit:
                break;
            case R.id.btn_reTryWhen:
                reTryWhen();
                break;

        }

    }

    // 设置变量
    // 可重试次数
    private int maxConnectCount = 5;
    // 当前已重试次数
    private int currentRetryCount = 0;
    // 重试等待时间
    private int waitRetryTime = 0;

    /**
     * 网络请求出错时重新连接
     */
    @SuppressLint("CheckResult")
    private void reTryWhen() {
        Observable<PictureBean> observable = MyRetrofitUtil.INSTANCE.getRetrofitInterface().ObservableGetPicture();
        // 注：主要异常才会回调retryWhen（）进行重试
        observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        LogUtil.showD(TAG, "发生异常 ==" + throwable.getMessage());
                        /**
                         * 需求1：根据异常类型选择是否重试
                         * 即，当发生的异常 = 网络异常 = IO异常 才选择重试
                         */
                        if (throwable instanceof IOException) {

                            LogUtil.showD(TAG, "属于IO异常，需重试");

                            /**
                             * 需求2：限制重试次数
                             * 即，当已重试次数 < 设置的重试次数，才选择重试
                             */
                            if (currentRetryCount < maxConnectCount) {

                                // 记录重试次数
                                currentRetryCount++;
                                LogUtil.showD(TAG, "重试次数 = " + currentRetryCount);

                                /**
                                 * 需求2：实现重试
                                 * 通过返回的Observable发送的事件 = Next事件，从而使得retryWhen（）重订阅，最终实现重试功能
                                 *
                                 * 需求3：延迟1段时间再重试
                                 * 采用delay操作符 = 延迟一段时间发送，以实现重试间隔设置
                                 *
                                 * 需求4：遇到的异常越多，时间越长
                                 * 在delay操作符的等待时间内设置 = 每重试1次，增多延迟重试时间1s
                                 */
                                // 设置等待时间
                                waitRetryTime = 1000 + currentRetryCount * 1000;
                                LogUtil.showD(TAG, "等待时间 =" + waitRetryTime);
                                return Observable.just(1).delay(waitRetryTime, TimeUnit.MILLISECONDS);


                            } else {
                                // 若重试次数已 > 设置重试次数，则不重试
                                // 通过发送error来停止重试（可在观察者的onError（）中获取信息）
                                return Observable.error(new Throwable("重试次数已超过设置次数 = " + currentRetryCount + "，即 不再重试"));

                            }
                        }

                        // 若发生的异常不属于I/O异常，则不重试
                        // 通过返回的Observable发送的事件 = Error事件 实现（可在观察者的onError（）中获取信息）
                        else {
                            return Observable.error(new Throwable("发生了非网络异常（非I/O异常）"));
                        }

                    }
                });
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PictureBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PictureBean pictureBean) {
                        LogUtil.showD(TAG, "成功的URL==" + pictureBean.getImgurl());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 获取停止重试的信息
                        LogUtil.showD(TAG, "停止重试：" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 联合判断
     */
    private void combineJudge() {
        Observable.combineLatest(observableName, observableAge, observableGender, new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) throws Exception {
                boolean isNameValid = !TextUtils.isEmpty(etName.getText());
                boolean isAgeValid = !TextUtils.isEmpty(etAge.getText());
                boolean isGenderValid = !TextUtils.isEmpty(etGender.getText());
                return isNameValid & isAgeValid & isGenderValid;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                ToastUtils.shortToast(RxjavaTiming.this,"" + aBoolean);
                btnCommit.setEnabled(aBoolean);
            }
        });
    }


    /**
     * 合并请求网络
     */
    private void mergeRequest() {
        Observable<PictureBean> request1 = MyRetrofitUtil.INSTANCE.getRetrofitInterface().ObservableGetPicture().subscribeOn(Schedulers.io());
        Observable<PictureBean> request2 = MyRetrofitUtil.INSTANCE.getRetrofitInterface().getSinePicPicture().subscribeOn(Schedulers.io());
        Observable.zip(request1, request2, new BiFunction<PictureBean, PictureBean, String>() {
            @Override
            public String apply(PictureBean pictureBean, PictureBean pictureBean2) throws Exception {
                return "第一个地址：" + pictureBean.getImgurl() + "\\\n第二个地址：" + pictureBean2.getImgurl();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtil.showD(TAG, s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.showD(TAG, "合并请求失败==" + throwable.getMessage());
                    }
                });
    }

    /**
     * 嵌套请求网络
     */
    private void nestRequest() {
        Observable<PictureBean> request1 = MyRetrofitUtil.INSTANCE.getRetrofitInterface().ObservableGetPicture();
        Observable<PictureBean> request2 = MyRetrofitUtil.INSTANCE.getRetrofitInterface().getSinePicPicture();
        request1.subscribeOn(Schedulers.io()) // （初始被观察者）切换到IO线程进行网络请求1
                .subscribeOn(AndroidSchedulers.mainThread())  // （新观察者）切换到主线程 处理网络请求1的结果
                .doOnNext(new Consumer<PictureBean>() {
                    @Override
                    public void accept(PictureBean pictureBean) throws Exception {
                        LogUtil.showD(TAG, "第1次请求URL==" + pictureBean.getImgurl());
                    }
                }).observeOn(Schedulers.io())
                // （新被观察者，同时也是新观察者）切换到IO线程去发起登录请求
                // 特别注意：因为flatMap是对初始被观察者作变换，所以对于旧被观察者，它是新观察者，所以通过observeOn切换线程
                // 但对于初始观察者，它则是新的被观察者
                .flatMap(new Function<PictureBean, ObservableSource<PictureBean>>() {
                    @Override
                    public ObservableSource<PictureBean> apply(PictureBean pictureBean) throws Exception {
                        // 将网络请求1转换成网络请求2，即发送网络请求2
                        return request2;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())  // （初始观察者）切换到主线程 处理网络请求2的结果
                .subscribe(new Consumer<PictureBean>() {
                    @Override
                    public void accept(PictureBean pictureBean) throws Exception {
                        LogUtil.showD(TAG, "第2次请求URL==" + pictureBean.getImgurl());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.showD(TAG, "第2次请求失败==" + throwable.getMessage());
                    }
                });

    }

    /**
     * 有条件轮询
     */
    private void whenCycle() {
        Observable<PictureBean> observable = MyRetrofitUtil.INSTANCE.getRetrofitInterface().getSinePicPicture();
        observable.repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {

                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        // 加入判断条件：当轮询次数 = 5次后，就停止轮询
                        if (i > 3) {
                            // 此处选择发送onError事件以结束轮询，因为可触发下游观察者的onError（）方法回调
                            return Observable.error(new Throwable("轮询结束"));
                        }
                        // 若轮询次数＜4次，则发送1Next事件以继续轮询
                        // 注：此处加入了delay操作符，作用 = 延迟一段时间发送（此处设置 = 2s），以实现轮询间间隔设置
                        return Observable.just(1).delay(2000, TimeUnit.MILLISECONDS);
                    }
                });
            }
        }).subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())// 切换回到主线程 处理请求结果
                .subscribe(new Observer<PictureBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PictureBean pictureBean) {
                        tvShowCycleNumber.setText("轮询次数：" + i);
                        LogUtil.showD(TAG, "第" + i + "次收到图片====" + pictureBean.getImgurl());
                        i++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 获取轮询结束信息
                        LogUtil.showD(TAG, e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 使用RXJava倒计时
     */
    private void setTiming(String timing) {
        int i = Integer.parseInt(timing);
        LogUtil.showD(TAG, "倒计时" + i + "秒");
        Observable.intervalRange(0, i + 1, 0, 1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtil.showD(TAG, "开始接收事件");
            }

            @Override
            public void onNext(Long o) {
                LogUtil.showD(TAG, "onNext====" + o);
                tvShowTiming.setText(String.format(getResources().getString(R.string.timingNumbers), String.valueOf(i - o.intValue())));
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.showD(TAG, "接收异常" + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtil.showD(TAG, "接收完成");
                tvShowTiming.setText("倒计时结束");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
