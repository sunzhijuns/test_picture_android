package com.szh.testpic;

import android.app.Application;

import com.itheima.retrofitutils.ItheimaHttp;

/**
 * Created by sunzhijun on 2017/12/18.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ItheimaHttp.init(this, Urls.getBaseUrl());
    }
    private static class Urls{

        public static String getBaseUrl() {
            return "http://www.oschina.net";
        }
    }
}
