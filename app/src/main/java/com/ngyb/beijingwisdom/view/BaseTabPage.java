package com.ngyb.beijingwisdom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.event.TabPageMenuClickEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/8 09:56
 */
public class BaseTabPage extends RelativeLayout implements View.OnClickListener {

    public TextView tabPageTitle;
    public ImageView tabPageMenu;
    public ImageView photoSwitch;
    public FrameLayout tabPageContent;
    private boolean isList = true;
    private OnTabPageChangeListener onTabPageChangeListener;

    public BaseTabPage(Context context) {
        this(context, null);
    }

    public BaseTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_base_tab_page, this);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initListener();
    }

    private void initListener() {
        tabPageMenu.setOnClickListener(this);
        photoSwitch.setOnClickListener(this);
    }

    private void initView(View view) {
        tabPageTitle = view.findViewById(R.id.tab_page_title);
        tabPageMenu = view.findViewById(R.id.tab_page_menu);
        photoSwitch = view.findViewById(R.id.photo_switch);
        tabPageContent = view.findViewById(R.id.tab_page_content);
    }

    public void setTitle(String title) {
        if (tabPageTitle != null) {
            tabPageTitle.setText(title);
        }
    }

    public void hideMenu() {
        tabPageMenu.setVisibility(GONE);
    }

    public void onMenuSwitch(int position) {

    }

    public void loadDataFromServer() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_page_menu:
                EventBus.getDefault().post(new TabPageMenuClickEvent());
                break;
            case R.id.photo_switch:
                isList =!isList;
                if (isList){
                    photoSwitch.setImageResource(R.mipmap.icon_pic_grid_type);
                }else{
                    photoSwitch.setImageResource(R.mipmap.icon_pic_list_type);
                }
                onSwitchPhoto(isList);
                break;
        }
    }

    public  void onSwitchPhoto(boolean isList) {

    }

    public interface OnTabPageChangeListener{
        void onTabPageMenuClick();
    }

    public void setOnTabPageChangeListener(OnTabPageChangeListener listener){
        onTabPageChangeListener  = listener;
    }
}
