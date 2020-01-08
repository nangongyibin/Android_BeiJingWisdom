package com.ngyb.beijingwisdom.view;

import android.content.Context;
import android.net.Network;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.bean.NewsListBean;
import com.ngyb.beijingwisdom.constant.Constant;
import com.ngyb.beijingwisdom.net.GsonRequest;
import com.ngyb.beijingwisdom.net.NetworkListener;
import com.ngyb.beijingwisdom.net.NetworkManager;

import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/8 11:20
 */
public class PhotosPage extends RelativeLayout {

    private ListView listView;
    private GridView gridView;
    private List<NewsListBean.DataBean.NewsBean> news;
    private String url;

    public PhotosPage(Context context) {
        this(context, null);
    }

    public PhotosPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_photo_page, this);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initAdapter();
    }

    private void initAdapter() {
        listView.setAdapter(baseAdapter);
        gridView.setAdapter(baseAdapter);
    }

    private void initView(View view) {
        listView = view.findViewById(R.id.list_view);
        gridView = view.findViewById(R.id.grid_view);
    }

    private BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (news == null) {
                return 0;
            }
            return news.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder vh = null;
            if (view == null) {
                view = View.inflate(getContext(), R.layout.view_photos_item, null);
                vh = new ViewHolder(view);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            NewsListBean.DataBean.NewsBean newsBean = news.get(i);
            vh.networkImageView.setImageUrl(Constant.BASE_URL+newsBean.getListimage(), NetworkManager.getInstance().getImageLoader());
            vh.title.setText(newsBean.getTitle());
            return view;
        }

        class ViewHolder {

            private final NetworkImageView networkImageView;
            private final TextView title;

            ViewHolder(View root) {
                networkImageView = root.findViewById(R.id.network_image_view);
                title = root.findViewById(R.id.title);
            }
        }
    };

    public void onSwitchPhoto(boolean isList) {
        if (isList) {
            listView.setVisibility(VISIBLE);
            gridView.setVisibility(GONE);
        } else {
            listView.setVisibility(GONE);
            gridView.setVisibility(VISIBLE);
        }
    }

    public void setUrl(String url) {
        this.url = url;
        GsonRequest<NewsListBean> request = new GsonRequest<>(url, NewsListBean.class, newsListBeanNetworkListener);
        NetworkManager.getInstance().setRequest(request);
    }

    private NetworkListener<NewsListBean> newsListBeanNetworkListener = new NetworkListener<NewsListBean>() {
        @Override
        public void onResponse(NewsListBean response) {
            news = response.getData().getNews();
            baseAdapter.notifyDataSetChanged();
        }
    };
}
