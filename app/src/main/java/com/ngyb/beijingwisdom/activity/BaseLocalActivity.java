package com.ngyb.beijingwisdom.activity;

import android.content.Intent;

import com.ngyb.mvpbase.BaseActivity;
import com.ngyb.mvpbase.BaseMvpActivity;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/6 20:14
 */
public abstract class BaseLocalActivity extends BaseActivity {

    /**
     * @param activity 跳转到对应的activity
     */
    protected void navigateTo(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }
}
