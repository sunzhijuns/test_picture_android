package com.szh.testpic;

import android.app.Application;
import android.content.Context;

/**
 * Created by sunzhijun on 2017/12/19.
 */

public class MyApplication extends Application {
    private static MyApplication sContext;

    public static Context getApplication() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
