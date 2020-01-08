package com.ngyb.beijingwisdom.app;

import android.app.Application;

import com.ngyb.beijingwisdom.net.NetworkManager;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/7 17:07
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.getInstance().init(this);
    }
}
