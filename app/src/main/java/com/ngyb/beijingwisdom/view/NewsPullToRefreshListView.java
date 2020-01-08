package com.ngyb.beijingwisdom.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.leon.loopviewpagerlib.FunBanner;
import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.activity.NewsDetailActivity;
import com.ngyb.beijingwisdom.bean.NewsListBean;
import com.ngyb.beijingwisdom.constant.Constant;
import com.ngyb.beijingwisdom.net.GsonRequest;
import com.ngyb.beijingwisdom.net.NetworkListener;
import com.ngyb.beijingwisdom.net.NetworkManager;
import com.ngyb.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/8 13:39
 */
class NewsPullToRefreshListView extends PullToRefreshListView {
    private static final int[] images = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d};
    private String url;
    private List<NewsListBean.DataBean.NewsBean> news;
    private String more;
    private FunBanner funBanner;
    private SharedPreferencesUtils sp;

    public NewsPullToRefreshListView(Context context) {
        this(context, null);
    }

    public NewsPullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        sp = new SharedPreferencesUtils(getContext());
        funBanner = new FunBanner.Builder(getContext())
                .setImageResIds(images)
                .setDotSelectedColor(Color.RED)
                .setHeightWidthRatio(0.5f)
                .setEnableAutoLoop(true)
                .setIndicatorBackgroundColor(R.color.c_30000000)
                .setLoopInterval(5000)
                .setDotNormalColor(Color.WHITE)
                .build();
        getRefreshableView().addHeaderView(funBanner);
        setAdapter(baseAdapter);
        setMode(Mode.BOTH);
        setOnRefreshListener(onRefreshListener);
        setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            i = i - 2;
            int newsid = news.get(i).getId();
            sp.setBoolean(String.valueOf(newsid), true);
            baseAdapter.notifyDataSetChanged();
            Intent intent = new Intent(getContext(), NewsDetailActivity.class);
            intent.putExtra(Constant.url, news.get(i).getUrl());
            getContext().startActivity(intent);
        }
    };

    private OnRefreshListener2<ListView> onRefreshListener = new OnRefreshListener2<ListView>() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
            GsonRequest<NewsListBean> request = new GsonRequest<>(url, NewsListBean.class, refreshListener);
            NetworkManager.getInstance().setRequest(request);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
            if (more.length() > 0) {
                String url = Constant.BASE_URL + more;
                GsonRequest<NewsListBean> request = new GsonRequest<>(url, NewsListBean.class, moreListener);
                NetworkManager.getInstance().setRequest(request);
            } else {
                Toasty.info(getContext(), "没有更多数据", Toast.LENGTH_LONG).show();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onRefreshComplete();
                    }
                }, 1000);
            }
        }
    };
    private NetworkListener<NewsListBean> moreListener = new NetworkListener<NewsListBean>() {
        @Override
        public void onResponse(NewsListBean response) {
            Toasty.success(getContext(), "加载更多数据成功", Toast.LENGTH_LONG).show();
            more = response.getData().getMore();
            news.addAll(response.getData().getNews());
            baseAdapter.notifyDataSetChanged();
            onRefreshComplete();
        }
    };

    private NetworkListener<NewsListBean> refreshListener = new NetworkListener<NewsListBean>() {
        @Override
        public void onResponse(NewsListBean response) {
            onRefreshComplete();
            more = response.getData().getMore();
            news = response.getData().getNews();
            baseAdapter.notifyDataSetChanged();
            List<NewsListBean.DataBean.TopnewsBean> topnews = response.getData().getTopnews();
            ArrayList<String> listUrl = new ArrayList<>();
            ArrayList<String> listTitles = new ArrayList<>();
            for (int i = 0; i < topnews.size(); i++) {
                listUrl.add(Constant.BASE_URL+topnews.get(i).getTopimage());
                listTitles.add(topnews.get(i).getTitle());
            }
            funBanner.setImageUrlsAndTitles(listUrl, listTitles);
        }
    };

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
                view = View.inflate(getContext(), R.layout.view_news_list_item, null);
                vh = new ViewHolder(view);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            NewsListBean.DataBean.NewsBean newsBean = news.get(i);
            vh.newsTitle.setText(newsBean.getTitle());
            vh.newsPublishTime.setText(newsBean.getPubdate());
            if (sp.getBoolean(String.valueOf(newsBean.getId()), false)) {
                vh.newsTitle.setTextColor(Color.GRAY);
            } else {
                vh.newsTitle.setTextColor(Color.BLACK);
            }
            Glide.with(getContext()).load(Constant.BASE_URL+newsBean.getListimage()).into(vh.newsImage);
            return view;
        }

        class ViewHolder {

            private final ImageView newsImage;
            private final TextView newsPublishTime;
            private final TextView newsTitle;

            ViewHolder(View root) {
                newsImage = root.findViewById(R.id.news_image);
                newsPublishTime = root.findViewById(R.id.news_publish_time);
                newsTitle = root.findViewById(R.id.news_title);
            }
        }
    };

    public void setUrl(String url) {
        this.url = url;
        GsonRequest<NewsListBean> request = new GsonRequest<>(url, NewsListBean.class, newsListBeanNetworkListener);
        NetworkManager.getInstance().setRequest(request);
    }

    private NetworkListener<NewsListBean> newsListBeanNetworkListener = new NetworkListener<NewsListBean>() {
        @Override
        public void onResponse(NewsListBean response) {
            more = response.getData().getMore();
            news = response.getData().getNews();
            baseAdapter.notifyDataSetChanged();
            List<NewsListBean.DataBean.TopnewsBean> topnews = response.getData().getTopnews();
            ArrayList<String> listUrl = new ArrayList<>();
            ArrayList<String> listTitles = new ArrayList<>();
            for (int i = 0; i < topnews.size(); i++) {
                listUrl.add(Constant.BASE_URL+topnews.get(i).getTopimage());
                listTitles.add(topnews.get(i).getTitle());
            }
            funBanner.setImageUrlsAndTitles(listUrl, listTitles);

        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);
        }
    };
}
