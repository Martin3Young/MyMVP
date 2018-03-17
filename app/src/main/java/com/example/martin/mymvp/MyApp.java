package com.example.martin.mymvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;

/**
 * Created by admin on 2017/12/18.
 */

public class MyApp extends Application {
    private static MyApp application;
    public ArrayList<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        activityList = new ArrayList<>();
        application = this;
        //        内存泄漏检测
        initLeakCanary();
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public static MyApp getInstance() {
        return application;
    }

    public void removeAll(){
        for(Activity activity:activityList){
            activity.finish();
        }
    }
    public static Context getAppContext() {
        return application.getApplicationContext();
    }
}
