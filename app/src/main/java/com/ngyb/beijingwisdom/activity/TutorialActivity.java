package com.ngyb.beijingwisdom.activity;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.constant.Constant;
import com.ngyb.utils.SharedPreferencesUtils;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/6 20:38
 */
public class TutorialActivity extends BaseLocalActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private CirclePageIndicator indicator;
    private Button start;
    private static int[] images = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private SharedPreferencesUtils sp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tutorial;
    }

    @Override
    public void init() {
        initView();
        initClass();
        initListener();
        initViewPager();
    }

    private void initClass() {
        sp = new SharedPreferencesUtils(getApplicationContext());
    }

    private void initViewPager() {
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        indicator.setViewPager(viewPager);
    }

    private void initListener() {
        start.setOnClickListener(this);
    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        indicator = findViewById(R.id.indicator);
        start = findViewById(R.id.start);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                sp.setBoolean(Constant.SP_KEY_STARTED,true);
                navigateTo(MainActivity.class);
                break;
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            if (i == images.length - 1) {
                start.setVisibility(View.VISIBLE);
            } else {
                start.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(TutorialActivity.this);
            imageView.setImageResource(images[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    };
}
