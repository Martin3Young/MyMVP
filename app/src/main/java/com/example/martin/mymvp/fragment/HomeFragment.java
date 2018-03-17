package com.example.martin.mymvp.fragment;

import android.widget.LinearLayout;

import com.example.martin.mymvp.R;
import com.example.martin.mymvp.base.BaseFragment;
import com.example.martin.mymvp.base.BasePresenter;

/**
 * Created by Martin on 2018/3/17.
 *
 * @新浪微博: http://weibo.com/2603687001
 * @GitHub: https://github.com/Martin3Young
 * @CSDN: http://blog.csdn.net/qq_32346021
 * @简书: http://www.jianshu.com/u/6d64225b1910
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment getInStance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return null;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
