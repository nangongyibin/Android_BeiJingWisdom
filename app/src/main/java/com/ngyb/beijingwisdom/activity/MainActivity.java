package com.ngyb.beijingwisdom.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.event.TabPageMenuClickEvent;
import com.ngyb.beijingwisdom.fragment.HomeFragment;
import com.ngyb.beijingwisdom.fragment.LeftMenuFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends SlidingFragmentActivity implements HomeFragment.OnHomeChangeListener, LeftMenuFragment.OnLeftMenuChangeListener {
    private static final String TAG = "MainActivity";
    private HomeFragment homeFragment;
    private LeftMenuFragment leftMenuFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        hideBottomUIMenu();
        initClass();
        initListener();
        initLeftMenu();
        initSlidingMenu();
        initContentView();
        initEventBus();
    }

    private void initEventBus() {
        EventBus.getDefault().register(this);
    }

    private void initContentView() {
        setContentView(R.layout.content_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment).commit();
//        setContentView(R.layout.menu_frame);
//        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, leftMenuFragment).commit();
    }

    private void initSlidingMenu() {
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setBehindOffsetRes(R.dimen.dp_200);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setMode(SlidingMenu.LEFT);
    }

    private void initLeftMenu() {
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, leftMenuFragment).commit();
    }

    private void initListener() {
        homeFragment.setOnHomeChangelistener(this);
        leftMenuFragment.setOnLeftMenuChangeListener(this);
    }

    private void initClass() {
        homeFragment = new HomeFragment();
        leftMenuFragment = new LeftMenuFragment();
    }

    private void hideBottomUIMenu() {
        int flags;
        int curApiVersion = Build.VERSION.SDK_INT;
        if (curApiVersion >= Build.VERSION_CODES.KITKAT) {
            flags = View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        } else {
            flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        }
        getWindow().getDecorView().setSystemUiVisibility(flags);
    }

    @Override
    public void onTabSwitch(int checkId) {
        if (checkId == R.id.tab_home || checkId == R.id.tab_settings ||
                checkId == R.id.tab_gov_affairs || checkId == R.id.tab_smart_service) {
            getSlidingMenu()
                    .setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        } else {
            getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        }
    }

    @Override
    public void onTabPageMenuClick() {
        getSlidingMenu().toggle();
    }

    @Override
    public void onMenuSwitch(int position, boolean isSwitch) {
        getSlidingMenu().toggle();
        if (isSwitch) {
            homeFragment.onMenuSwitch(position);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostingEvent(TabPageMenuClickEvent event) {
        Log.e(TAG, "onPostingEvent: 打开侧滑" );
        getSlidingMenu().toggle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
