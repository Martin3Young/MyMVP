package com.example.martin.mymvp.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.martin.mymvp.untils.LoadingUtils;
import com.example.martin.mymvp.untils.StatusBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/12/20.
 */

public abstract class BaseFragment<V,T extends BasePresenter> extends Fragment {

    private Unbinder mUnbinder;
    private LoadingUtils mLoading;
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getLayoutId(),container,false);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter = initPresenter();
        if (mPresenter!=null) {
            mPresenter.attachView((V) this);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getLinearLayout()!=null){
            StatusBar.init(getActivity(),getLinearLayout());
        }
        initData();
        initPresenter();
    }

    protected abstract LinearLayout getLinearLayout();

    protected abstract T initPresenter();

    protected abstract void initData();

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
        if (mPresenter!=null) {
            mPresenter.detachView();
        }
    }

    public Dialog showLoading(String message){
        if(mLoading==null){
            mLoading=new LoadingUtils(getActivity());
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
