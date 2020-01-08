package com.ngyb.beijingwisdom.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.bean.CategoryBean;
import com.ngyb.beijingwisdom.constant.Constant;
import com.viewpagerindicator.TabPageIndicator;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/8 13:27
 */
public class NewsPage extends RelativeLayout {
    public static final String[] TITLES = {"北京", "中国", "国际", "体育", "生活", "旅游", "科技", "军事", "时尚"};
    private CategoryBean.DataBean data;
    private TabPageIndicator indicator;
    private ViewPager viewPager;

    public NewsPage(Context context) {
        this(context, null);
    }

    public NewsPage(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public NewsPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View root = LayoutInflater.from(context).inflate(R.layout.view_news_page, this);
        init(root);
    }

    private void init(View root) {
        initView(root);
        initAdapter();
    }

    private void initAdapter() {
        viewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(viewPager);
    }

    private void initView(View root) {
        indicator = root.findViewById(R.id.indicator);
        viewPager = root.findViewById(R.id.view_pager);
    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            if (data == null) {
                return 0;
            }
            return data.getChildren().size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            NewsPullToRefreshListView pullToRefreshListView = new NewsPullToRefreshListView(getContext());
            String url = data.getChildren().get(position).getUrl();
            pullToRefreshListView.setUrl(Constant.BASE_URL + url);
            container.addView(pullToRefreshListView);
            return pullToRefreshListView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return data.getChildren().get(position).getTitle();
        }
    };


    public void setData(CategoryBean.DataBean dataBean) {
        this.data = dataBean;
        indicator.notifyDataSetChanged();
        pagerAdapter.notifyDataSetChanged();
    }
}
