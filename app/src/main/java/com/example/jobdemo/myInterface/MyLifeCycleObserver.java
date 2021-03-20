package com.example.jobdemo.myInterface;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.jobdemo.util.LogUtil;

/**
 * 继承LifecycleObserver，自己写方法加上对应的注解（@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)），fragmentActivity直接添加这个监听者就可以了
 * activity要自己实现以下
 */
public class MyLifeCycleObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onActivityResume() {
        LogUtil.showD("调用了onActivityResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onActivityPause() {
        LogUtil.showD("调用了onActivityPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onActivityStop() {
        LogUtil.showD("调用了onActivityStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onActivityDestroy() {
        LogUtil.showD("调用了onActivityDestroy");
    }
}
