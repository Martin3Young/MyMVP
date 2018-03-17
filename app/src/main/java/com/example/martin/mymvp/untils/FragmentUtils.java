package com.example.martin.mymvp.untils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.martin.mymvp.R;
import com.example.martin.mymvp.config.Parms;
import com.example.martin.mymvp.fragment.DiscoveryFragment;
import com.example.martin.mymvp.fragment.HomeFragment;
import com.example.martin.mymvp.fragment.MySelfFragment;


/**
 * fragment工具类
 */

public class FragmentUtils {

    private Fragment lastFragment = null;

    public void showFragment(String tag, FragmentManager fragmentManager) {
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);
        if (fragmentByTag == null) {
            fragmentByTag = creatFragmentByTag(tag);
            addFragment(fragmentByTag, tag, fragmentManager);
        } else {
            showMyFragment(fragmentByTag, fragmentManager);
        }
        lastFragment = fragmentByTag;
    }

    private void showMyFragment(Fragment fragmentByTag, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (lastFragment != null) {
            fragmentTransaction.hide(lastFragment);
        }
        fragmentTransaction.show(fragmentByTag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void addFragment(Fragment fragment, String tag, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (lastFragment != null) {
            transaction.hide(lastFragment);
        }
        transaction.add(R.id.frame, fragment, tag);
        transaction.commitAllowingStateLoss();
    }


    private Fragment creatFragmentByTag(String tag) {
        switch (tag) {
            case Parms.FragmentTag.HOME_FRAGMENT:
                return HomeFragment.getInStance();
            case Parms.FragmentTag.MY_FRAGMENT:
                return MySelfFragment.getInStance();
            case Parms.FragmentTag.DISCOVERY_FRAGMENT:
                return DiscoveryFragment.getInStance();
        }
        return HomeFragment.getInStance();
    }
}
