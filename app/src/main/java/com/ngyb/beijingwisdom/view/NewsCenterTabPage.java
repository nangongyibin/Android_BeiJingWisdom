package com.ngyb.beijingwisdom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.util.LogTime;
import com.ngyb.beijingwisdom.bean.CategoryBean;
import com.ngyb.beijingwisdom.constant.Constant;
import com.ngyb.beijingwisdom.net.GsonRequest;
import com.ngyb.beijingwisdom.net.NetworkListener;
import com.ngyb.beijingwisdom.net.NetworkManager;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/8 11:03
 */
public class NewsCenterTabPage extends BaseTabPage {
    private CategoryBean data;
    private static final String TAG = "NewsCenterTabPage";
    public NewsCenterTabPage(Context context) {
        super(context);
    }

    public NewsCenterTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMenuSwitch(int position) {
        if (position == 2) {
            photoSwitch.setVisibility(VISIBLE);
        } else {
            photoSwitch.setVisibility(GONE);
        }
        View view = null;
        switch (position) {
            case 0:
                NewsPage newsPage = new NewsPage(getContext());
                newsPage.setData(data.getData().get(0));
                view = newsPage;
                break;
            case 1:
                TextView subject = new TextView(getContext());
                view = subject;
                subject.setText("专题");
                break;
            case 2:
                PhotosPage photosPage = new PhotosPage(getContext());
                String url = data.getData().get(2).getUrl();
                photosPage.setUrl(Constant.BASE_URL + url);
                view = photosPage;
                break;
            case 3:
                TextView interact = new TextView(getContext());
                view = interact;
                interact.setText("互动");
                break;
        }
        tabPageContent.removeAllViews();
        tabPageContent.addView(view);
    }

    @Override
    public void loadDataFromServer() {
        String url = Constant.BASE_URL + "categories.json";
        Log.e(TAG, "loadDataFromServer: "+url );
        GsonRequest<CategoryBean> request = new GsonRequest<>(url, CategoryBean.class, categoryBeanNetworkListener);
        NetworkManager.getInstance().setRequest(request);
    }

    private NetworkListener<CategoryBean> categoryBeanNetworkListener = new NetworkListener<CategoryBean>() {
        @Override
        public void onResponse(CategoryBean response) {
            Log.e(TAG, "onResponse: " );
            data = response;
            onMenuSwitch(0);
//            super.onResponse(response);
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "onErrorResponse: " );
            super.onErrorResponse(error);
        }
    };

    @Override
    public void onSwitchPhoto(boolean isList) {
        PhotosPage photosPage = (PhotosPage) tabPageContent.getChildAt(0);
        photosPage.onSwitchPhoto(isList);
    }
}

