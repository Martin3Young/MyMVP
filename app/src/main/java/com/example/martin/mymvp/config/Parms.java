package com.example.martin.mymvp.config;

import android.support.annotation.StringDef;

/**
 * Created by admin on 2017/12/19.
 */

public interface Parms {

    /**
     * fragment的别名
     */
    @StringDef(value = {FragmentTag.HOME_FRAGMENT, FragmentTag.MY_FRAGMENT})
    @interface FragmentTag{
        String HOME_FRAGMENT="HOME";
        String MY_FRAGMENT="MY";
        String DISCOVERY_FRAGMENT="DIS";
    }

}
