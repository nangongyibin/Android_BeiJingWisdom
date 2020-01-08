package com.ngyb.beijingwisdom.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.view.BaseTabPage;
import com.ngyb.beijingwisdom.view.GovAffairsTabPage;
import com.ngyb.beijingwisdom.view.HomeTabPage;
import com.ngyb.beijingwisdom.view.NewsCenterTabPage;
import com.ngyb.beijingwisdom.view.SettingsTabPage;
import com.ngyb.beijingwisdom.view.SmartServicePage;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/7 15:56
 */
public class HomeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, BaseTabPage.OnTabPageChangeListener {
    private OnHomeChangeListener onHomeChangeListener;
    private FrameLayout tabPageContainer;
    private RadioGroup radioGroup;
    private SparseArray<BaseTabPage> tabPageCache = new SparseArray<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, null);
        init(root);
        return root;
    }

    private void init(View root) {
        initView(root);
    initlistener();
    initBaseData();
}

    private void initBaseData() {
        radioGroup.check(R.id.tab_home);
    }

    private void initlistener() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initView(View root) {
        tabPageContainer = root.findViewById(R.id.tab_page_container);
        radioGroup = root.findViewById(R.id.radio_group);
    }

    public void onMenuSwitch(int position) {
        BaseTabPage currentTabPage = (BaseTabPage) tabPageContainer.getChildAt(0);
        currentTabPage.onMenuSwitch(position);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        BaseTabPage baseTabPage;
        if (tabPageCache.get(i) != null) {
            baseTabPage = tabPageCache.get(i);
        } else {
            baseTabPage = createTabPage(i);
            tabPageCache.put(i, baseTabPage);
        }
        tabPageContainer.removeAllViews();
        tabPageContainer.addView(baseTabPage);
        if (onHomeChangeListener != null) {
            onHomeChangeListener.onTabSwitch(i);
        }
    }

    private BaseTabPage createTabPage(int i) {
        BaseTabPage baseTabPage = null;
        switch (i) {
            case R.id.tab_home:
                baseTabPage = new HomeTabPage(getContext());
                if (baseTabPage != null) {
                    baseTabPage.setTitle("首页");
                }
                baseTabPage.hideMenu();
                break;
            case R.id.tab_news_center:
                baseTabPage = new NewsCenterTabPage(getContext());
                if (baseTabPage != null) {
                    baseTabPage.setTitle("新闻中心");
                }
                break;
            case R.id.tab_smart_service:
                baseTabPage = new SmartServicePage(getContext());
                if (baseTabPage != null) {
                    baseTabPage.setTitle("智慧服务");
                }
                baseTabPage.hideMenu();
                break;
            case R.id.tab_gov_affairs:
                baseTabPage = new GovAffairsTabPage(getContext());
                if (baseTabPage != null) {
                    baseTabPage.setTitle("政务");
                }
                baseTabPage.hideMenu();
                break;
            case R.id.tab_settings:
                baseTabPage = new SettingsTabPage(getContext());
                if (baseTabPage != null) {
                    baseTabPage.setTitle("设置中心");
                }
                baseTabPage.hideMenu();
                break;
        }
        baseTabPage.setOnTabPageChangeListener(this);
        baseTabPage.loadDataFromServer();
        return baseTabPage;
    }

    public interface OnHomeChangeListener {

        void onTabSwitch(int checkId);

        void onTabPageMenuClick();
    }

    public void setOnHomeChangelistener(OnHomeChangeListener onHomeChangelistener) {
        this.onHomeChangeListener = onHomeChangelistener;
    }

    @Override
    public void onTabPageMenuClick() {
        if (onHomeChangeListener != null) {
            onHomeChangeListener.onTabPageMenuClick();
        }
    }
}
