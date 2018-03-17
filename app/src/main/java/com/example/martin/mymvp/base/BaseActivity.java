package com.example.martin.mymvp.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.martin.mymvp.MyApp;
import com.example.martin.mymvp.untils.LoadingUtils;
import com.example.martin.mymvp.untils.StatusBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/12/19.
 */

public abstract class BaseActivity<V,T extends BasePresenter> extends AppCompatActivity {
    protected T mPresenter;
    private Unbinder unbinder;
    //表单请求
//    private Call okHttpCall;
    private LoadingUtils mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getWindow().setBackgroundDrawable(null);
        mPresenter = initPresenter();
        if (mPresenter!=null) {
            mPresenter.attachView((V) this);
        }
        unbinder = ButterKnife.bind(this);
        if (getTopView() != null) {
            StatusBar.init(this, getTopView());
        }
        MyApp.getInstance().activityList.add(this);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter!=null) {
            mPresenter.detachView();
        }
        MyApp.getInstance().activityList.remove(this);
    }

    /**
     * 获取布局文件id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化P层
     */
    protected abstract T initPresenter();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取沉浸式状态栏顶部占位栏
     *
     * @return
     */
    protected abstract LinearLayout getTopView();

    public Dialog showLoading(String message){
        if(mLoading==null){
            mLoading=new LoadingUtils(this);
        }
        mLoading.show(message);
        return mLoading;
    }

    public void dismissDialog(){
        if(mLoading!=null && mLoading.isShowing()){
            mLoading.dismiss();
        }
    }

}
