package com.example.martin.mymvp.presenter;

import android.support.v4.app.FragmentManager;

import com.example.martin.mymvp.base.BasePresenter;
import com.example.martin.mymvp.contract.MainContract;
import com.example.martin.mymvp.untils.FragmentUtils;


/**
 * Created by admin on 2017/12/20.
 */

public class MainPresenter extends BasePresenter implements MainContract.Presenter {

    FragmentUtils fragmentUtils;

    public MainPresenter() {
        fragmentUtils = new FragmentUtils();
    }

    @Override
    public void changeFragment(String tag, FragmentManager fragmentManager) {
        fragmentUtils.showFragment(tag, fragmentManager);
    }
}
