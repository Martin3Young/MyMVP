package com.example.martin.mymvp.net;

import android.util.Log;

import com.example.martin.mymvp.base.BaseView;

import rx.Subscriber;

/**
 * 事件接受者
 */

public abstract class SimpleSubscribe<T> extends Subscriber<T> {

    private BaseView baseView;

    public SimpleSubscribe(BaseView baseView){
        this.baseView=baseView;
    }

    public SimpleSubscribe(){}
    @Override
    public void onStart() {
        onRequestStart();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.i("code","错误信息："+e.getMessage());
        if(baseView!=null){
            baseView.getError(e);
            baseView.dismissDialog();
        }
    }

    @Override
    public void onNext(T t) {
        if(baseView!=null){
            baseView.dismissDialog();
        }
        success(t);
    }

    public abstract void onRequestStart();
    public abstract void success(T t);
}
