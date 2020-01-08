# BeijingWisdom

zhbj

# 敏捷开发 #

>敏捷开发(Agile Development)是一种以人为核心、迭代、循序渐进的开发方法。

1. 以人为核心：注重团队成员之间面对面的交流，尽量减少文档
2. 迭代：把一个复杂且开发周期很长的开发任务，分解成很多小周期可完成的任务，每个小周期都能生产或开发出一个可交付的软件产品, 每个小周期就是一次迭代

## Scrum开发流程 ##

敏捷是一种思想，Scrum是一种敏捷开发的具体方式，Scrum英文是橄榄球中“争球”的意思。

Scrum开发流程中的三大角色:

1. 产品负责人（Product Owner)：确定产品的功能和达到要求的标准，指定软件发布的日期和交付的内容，同时又权利接受和拒绝开发团队的工作成果。（Team Leader）
2. 流程管理员（Scrum Master）: 负责整个scrum流程在项目中的实施和进行，负责与客户(其他团队)进行沟通（外交官）。
3. 开发团队（Scrum Team): 5个，两个安卓，两个iOS，一个后台

![](https://github.com/nangongyibin/Android_BeiJingWisdom/blob/master/picture/4.png?raw=true)

Sprint: 短距离赛跑的意思，这里指的是一次迭代，通常是四个星期。1个星期做详细计划(Sprint burn down), 
2个星期编码，1个星期测试，修bug

### 站会 ###

![](https://github.com/nangongyibin/Android_BeiJingWisdom/blob/master/picture/6.png?raw=true)

### 任务看板 ###

![](https://github.com/nangongyibin/Android_BeiJingWisdom/blob/master/picture/7.png?raw=true)

# 服务器搭建 #

将zhbj文件夹放到Tomcat里面的webapps/ROOT目录下，使用浏览器或者Postman测试是否正常工作。

# 项目初始化 #

* 基类的创建
* ButterKnife的集成
* 包名的创建
* Git的初始化(导入Git管理的项目到AS还得开启Git)

## 基类的创建 ##

基类的作用就是封装公共的功能和方法，既能极少代码量，又有利于项目扩展。举个栗子：如果我需要给每个Activity都添加摇一摇的功能，如果不使用基类，那么每个activity都得加一段摇摇的代码
，既增加的代码量又不利于项目维护。如果把摇一摇的功能封装在基类中，那么所有的activity都能摇一摇了。

### BaseActivity ###
	
    public abstract class BaseActivity extends AppCompatActivity {

	    @Override
	    protected void onCreate(@Nullable Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(getLayoutResId());
	        ButterKnife.bind(this);//ButterKnife的绑定
	        init();
	    }
	
	    //初始化
	    protected void init() {
	        //初始化公共功能,摇一摇,方便做功能扩展
	    }
	
	    /**
	     * @return 子类必须实现该方法, 返回一个布局资源id
	     */
	    abstract int getLayoutResId();
	
	    /**
	     * @param activity 跳转到对应activity
	     */
	    protected void navigateTo(Class activity) {
	        Intent intent = new Intent(this, activity);
	        startActivity(intent);
	        finish();
	    }
	}

## Butterkinfe集成 ##

[Github](https://github.com/JakeWharton/butterknife)

### 添加依赖 ###

	dependencies {
	  compile 'com.jakewharton:butterknife:8.6.1'
	  annotationProcessor 'com.jakewharton:butterknife-compiler:8.6.1'
	}

# 第一个里程碑：欢迎界面 #

> 需求分析

1. Splash界面 旋转 缩放 透明度动画
2. 动画结束之后，进入向导界面
3. 向导页面的切换
4. 滑动最后一页出现立即体验按钮
5. 点击“立即体验”进入到主界面
6. 再次进入app，会直接跳转到主界面
7. ViewPager的指示器


## Splash界面 ##

### 启动动画 ###

            AnimationSet animationSet = new AnimationSet(false);
	        animationSet.setAnimationListener(animationListener);
	        //旋转
	        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
	                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
	                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
	        rotateAnimation.setDuration(DURATION);
	        animationSet.addAnimation(rotateAnimation);
	        //缩放
	        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f
	                , ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
	        scaleAnimation.setDuration(DURATION);
	        animationSet.addAnimation(scaleAnimation);
	        //透明度
	        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
	        alphaAnimation.setDuration(DURATION);
	        animationSet.addAnimation(alphaAnimation);
	        //启动动画
	        splashImage.startAnimation(animationSet);

### 动画监听 ###

        private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
	        @Override
	        public void onAnimationStart(Animation animation) {
	
	        }
	
	        @Override
	        public void onAnimationEnd(Animation animation) {
	            //动画结束
	            if (SPUtils.getBoolean(SplashActivity.this, Constant.SP_KEY_STARTED)) {
	                navigateTo(MainActivity.class);
	            } else {
	                navigateTo(TutorialActivity.class);
	            }
	        }
	
	        @Override
	        public void onAnimationRepeat(Animation animation) {
	
	        }
	    };

### 全屏配置 ###

        <!-- Base application theme. -->
	    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
	        <!-- Customize your theme here. -->
	        <item name="colorPrimary">@color/colorPrimary</item>
	        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
	        <item name="colorAccent">@color/colorAccent</item>
	    </style>
	
	    <style name="AppTheme.FullScreen" parent="AppTheme">
	        <item name="android:windowFullscreen">true</item>
	    </style>




## 向导界面 ##

### ViewPager页面切换 ###

    `    private PagerAdapter pagerAdapter = new PagerAdapter() {
	        @Override
	        public int getCount() {
	            return images.length;
	        }
	
	        /**
	         * 防止给ViewPager乱加孩子
	         * @param view ViewPager里面的孩子,在这里是ImageView
	         * @param object 当作孩子的标记,在这里标记就是ImageView
	         * @return ViewPager的孩子是否被标记
	         */
	        @Override
	        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
	            return view == object;
	        }
	
	        /**
	         * @param container  ViewPager
	         * @param position
	         * @return
	         */
	        @NonNull
	        @Override
	        public Object instantiateItem(@NonNull ViewGroup container, int position) {
	            //创建对应的position位置ImageView
	            ImageView imageView = new ImageView(TutorialActivity.this);
	            imageView.setImageResource(images[position]);
	            //缩放
	            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//x,y轴拉伸填充满ImageView
	            container.addView(imageView);
	            //返回一个标记
	            return imageView;
	        }
	
	        @Override
	        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
	            container.removeView((View) object);
	        }
	    };`

### 立即体验的显示和隐藏 ###

        private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
	        @Override
	        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	
	        }
	
	        @Override
	        public void onPageSelected(int position) {
	            if (position == images.length - 1) {
	                //显示立即体验按钮
	                start.setVisibility(View.VISIBLE);
	            } else {
	                start.setVisibility(View.GONE);
	            }
	        }
	
	        @Override
	        public void onPageScrollStateChanged(int state) {
	
	        }
	    };

### 跳转到主界面 ###

        @OnClick(R.id.start)
	    public void onViewClicked() {
	        navigateTo(MainActivity.class);
	        //记录已经进入过主界面
	        SPUtils.saveBoolean(this, Constant.SP_KEY_STARTED, true);
	    }

### ViewPagerIndicator ###

ViewPagerIndicator即ViewPager的页面指示器,如果要你自己写一个ViewPager的指示器，你会怎么去写？ 

* 自定义一个view, 重写onDraw方法更加页面的个数来绘制几个点，监听ViewPager的滚动来重新绘制动点。 
* 自定义一个ViewGroup, 重写onLayout的方法来布局几个点(View)， 监听ViewPager的滚动来重新布局动点。

#### ViewPagerIndicator Demo展示 ####

![](https://github.com/nangongyibin/Android_BeiJingWisdom/blob/master/picture/9.png?raw=true)

* [Eclipse版本](https://github.com/JakeWharton/ViewPagerIndicator)
* [Android Stuido版本](https://github.com/uncleleonfan/ViewPagerIndicator)

#### CirclePagerIndicator的集成 ####

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        compile 'com.github.uncleleonfan:ViewPagerIndicator:1.0.0'
	}

		//activity_tutorial.xml
        <com.viewpagerindicator.CirclePageIndicator
	        android:id="@+id/indicator"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:layout_alignParentBottom="true"
	        android:layout_marginBottom="60dp"
	        android:padding="10dip"
	        app:fillColor="#ff0000"
	        app:pageColor="@android:color/darker_gray"
	        app:radius="5dp" />

			//TutorialActivity
            //关联ViewPager
        	indicator.setViewPager(viewPager);


# 第二个里程碑：主界面框架 #

>  需求分析

1. 侧滑菜单
2. 主界面
	1. 5个Tab按钮
	2. 一个Tab按钮对应一个页面
	3. 点击切换页面
	4. 页面的初始化（标题，首页和设置中心没有标题栏的菜单按钮，默认首页）
	5. 首页和设置中心拉不出侧滑菜单
	6. 点击标题栏按钮，打开或者关闭侧滑菜单

3. 菜单列表
	1. 只实现新闻中心的菜单列表
	2. 点击菜单选项，关闭侧滑菜单，切换新闻中心里面的页面

## 侧滑菜单 ##

侧滑菜单的实现有很多方式，可以自定义控件，或者使用老牌的SlidingMenu，或者使用谷歌亲生的DrawerLayout。


### SlidingMenu ###

* [Eclipse版](https://github.com/jfeinstein10/SlidingMenu)

* [Android Studio版](https://github.com/uncleleonfan/SlidingMenu)

#### SlidingMenu Demo的展示 ####

![](https://github.com/nangongyibin/Android_BeiJingWisdom/blob/master/picture/5.png?raw=true)


#### SlindingMenu集成 ####

	//项目下的build.gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	//app模块下的build.gradle
	dependencies {
	        compile 'com.github.uncleleonfan:SlidingMenu:1.0.0'
	}


        private void initContentView() {
	        //中间视图配置
	        setContentView(R.layout.content_frame);
	        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment).commit();
	    }
	
	    private void initSlidingMenu() {
	        SlidingMenu sm = getSlidingMenu();
	        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//拉出侧滑后右边内容显示的宽度200dp
	        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置边缘可拉出侧滑菜单
	        sm.setMode(SlidingMenu.LEFT);//从左边拉出侧滑菜单
	    }
	
	    private void initLeftMenu() {
	        //set the Behind View
	        setBehindContentView(R.layout.menu_frame);//设置左边菜单视图
	        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
	        fragmentTransaction.replace(R.id.menu_frame, leftMenuFragment);
	        fragmentTransaction.commit();
	    }


## 主界面 ##

![](https://github.com/nangongyibin/Android_BeiJingWisdom/blob/master/picture/2.png?raw=true)

### Tab按钮实现 ###

    <?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical">
	
	    <FrameLayout
	        android:id="@+id/tab_page_container"
	        android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"></FrameLayout>
	    <!--五个Tab按钮-->
	    <RadioGroup
	        android:id="@+id/radio_group"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:background="@mipmap/bottom_tab_bg"
	        android:orientation="horizontal">
	        <!--首页-->
	        <RadioButton
	            android:id="@+id/tab_home"
	            style="@style/SmartBJTabStyle"
	            android:drawableTop="@drawable/tab_home_selector"
	            android:text="首页" />
	
	        <RadioButton
	            android:id="@+id/tab_news_center"
	            style="@style/SmartBJTabStyle"
	            android:drawableTop="@drawable/tab_news_center_selector"
	            android:text="新闻中心" />
	
	        <RadioButton
	            android:id="@+id/tab_smart_service"
	            style="@style/SmartBJTabStyle"
	            android:drawableTop="@drawable/tab_smart_service_selector"
	            android:text="智慧服务" />
	
	        <RadioButton
	            android:id="@+id/tab_gov_affairs"
	            style="@style/SmartBJTabStyle"
	            android:drawableTop="@drawable/tab_gov_affairs_selector"
	            android:text="政务" />
	
	        <RadioButton
	            android:id="@+id/tab_settings"
	            style="@style/SmartBJTabStyle"
	            android:drawableTop="@drawable/tab_settings_selector"
	            android:text="设置中心" />
	    </RadioGroup>
	</LinearLayout>

> 注意要给每个RadioButton一个id，否则点击切换时状态不更新。


#### 样式 ####

        <style name="SmartBJTabStyle">
	        <item name="android:layout_width">0dp</item>
	        <item name="android:layout_height">wrap_content</item>
	        <item name="android:layout_weight">1</item>
	        <item name="android:padding">8dp</item>
	        <item name="android:textColor">@color/tab_text_selector</item>
	        <item name="android:gravity">center_horizontal</item>
	        <item name="android:drawablePadding">8dp</item>
	        <item name="android:button">@null</item>
	    </style>

## Tab页面实现 ##

由于首页，新闻中心，智慧服务，政务，设置中心都具有标题栏，所以可以封装一个基类，子类去实现各自的内容布局。另外，由于每个页面都能要加载数据，所以抽取一个方法`loadDataFromServer`,子类该方法去加载各自的数据。

### 抽取公共布局 ###

            //View.inflate();
	        //传null与不传null的区别
	        //如果root不为null,将view_base_tab_page解析出来,添加到root里面,如果为null,则不糊添加,看不到布局
	        View view = LayoutInflater.from(context).inflate(R.layout.view_base_tab_page, this);
	        //传null与不传null的区别
	        //如果root不为null,inflate返回的就是这个root
	        //如果root为null,返回view_base_tab_page根节点,在这里就是Linearlayout
	        //1.自定义组合控件,不传null,传this
	        //2.Fragment里面,传null
	        //3.ListView里面,传null
	//        ButterKnife.bind(this,this);
	        ButterKnife.bind(this, view);

### 加载数据 ###

        /**
	     * 由子类来实现加载各自的数据
	     */
	    public void loadDataFromServer() {
	
	    }

### Tab页面的创建 ###

        @NonNull
	    private BaseTabPage createTabPage(@IdRes int checkedId) {
	        BaseTabPage baseTabPage = null;
	        switch (checkedId) {
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
	                break;
	            case R.id.tab_gov_affairs:
	                baseTabPage = new GovAffairsTabPage(getContext());
	                if (baseTabPage != null) {
	                    baseTabPage.setTitle("政务");
	                }
	                break;
	            case R.id.tab_settings:
	                baseTabPage = new SettingsTabPage(getContext());
	                if (baseTabPage != null) {
	                    baseTabPage.setTitle("设置中心");
	                }
	                baseTabPage.hideMenu();
	                break;
	        }
	        //设置监听BaseTabPage里面的事件
	        baseTabPage.setOnTabPageChangeListener(onTabPageChangeListener);
	        //获取对应页面的数据
	        baseTabPage.loadDataFromServer();
	        return baseTabPage;
	    }


### Tab页面的切换 ###

        private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
	        @Override
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
	            BaseTabPage baseTabPage;
	            //内存优化
	            //是否存在缓存,如果有,从缓存里面获取
	            if (tabPageCache.get(checkedId) != null) {
	                baseTabPage = tabPageCache.get(checkedId);
	                Log.d(TAG, "onCheckedChanged: 从缓存获取");
	            } else {
	                //创建一个TabPage,添加到FrameLayout里面
	                baseTabPage = createTabPage(checkedId);
	                //将创建的页面缓存起来
	                tabPageCache.put(checkedId, baseTabPage);
	                Log.e(TAG, "onCheckedChanged: 创建TabPage");
	            }
	            //布局优化:添加TabPage之前,先清空布局里面的TabPage,FrameLayout只保留一个孩子
	            tabPageContainer.removeAllViews();
	            tabPageContainer.addView(baseTabPage);
	            //step4在事件发生的地方,通知监听器
	            if (onHomeChangeListener != null) {
	                //onHomeChangeListener就是MainActivity里面的onHomeChangeListener
	                onHomeChangeListener.onTabSwitch(checkedId);//调用的MainActivity里面的监听器的方法
	            }
	        }
	    };

### 默认选中首页 ###
   
            //默认选中首页
        	radioGroup.check(R.id.tab_home);//会触发onCheckedChangeListener调用

### 首页和设置中心拉不出侧滑菜单 ###

当Tab切换到首页或者设置中心时，我们需要通知MainActivity不能拉出侧滑菜单。这里使用接口回调的方式。

        //step1定义接口
	    public interface OnHomeChangeListener {
	        //step2定义事件接口方法
	        void onTabSwitch(int checkId);
	
	        void onTabPageMenuClick();
	    }
	
	    //step3提供设置监听器的方法,谁想监听,谁就调用该方法设置监听器
	    public void setOnHomeChangeListener(OnHomeChangeListener listener) {
	        onHomeChangeListener = listener;
	    }

        private void initListener() {
		        //要监听homeFragment里面事件
		        homeFragment.setOnHomeChangeListener(onHomeChangeListener);
		        leftMenuFragment.setOnLeftMenuChangeListener(onLeftMenuChangeListener);
		    }
	        private HomeFragment.OnHomeChangeListener onHomeChangeListener = new HomeFragment.OnHomeChangeListener() {
	        @Override
	        public void onTabSwitch(int checkId) {
	            //首页和设置中心拉不出侧滑菜单
	            if (checkId == R.id.tab_home || checkId == R.id.tab_settings) {
	                getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
	            } else {
	                getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	            }
	        }
	
	        @Override
	        public void onTabPageMenuClick() {
	            Log.e(TAG, "onTabPageMenuClick:事件从HomeFragment传到MainActivity ");
	            getSlidingMenu().toggle();//关闭或者打开侧滑菜单
	        }
	    };


### 点击标题栏按钮，打开或者关闭侧滑菜单 ###

这是一个连环回调，事件在TabPage中发生，要传到MainActivity,因为只有MainActivity才能控制侧滑菜单的打开或者关闭。所以事件先从TabPage传递到HomeFragment，再从HomeFragment传递到MainActivity。

#### 点击事件从TabPage传递到HomeFragment ####

        public interface OnLeftMenuChangeListener {
	        /**
	         * @param position 点击的位置
	         * @param isSwitch 是否真的发生了切换
	         */
	        void onMenuSwitch(int position, boolean isSwitch);
	    }
	
	    public void setOnLeftMenuChangeListener(OnLeftMenuChangeListener listener) {
	        onLeftMenuChangeListener = listener;
	    }


        private LeftMenuFragment.OnLeftMenuChangeListener onLeftMenuChangeListener = new LeftMenuFragment.OnLeftMenuChangeListener() {
	        @Override
	        public void onMenuSwitch(int position, boolean isSwitch) {
	            Log.e(TAG, "onMenuSwitch: 事件从LeftMenuFragment传递到MainActivity");
	            //关闭侧滑菜单
	            getSlidingMenu().toggle();
	            //如果发生了切换,才将事件传递给HomeFragment
	            if (isSwitch) {
	                homeFragment.onMenuSwitch(position);
	            }
	        }
	    };

#### 点击事件从HomeFragment传递到MainActivity ####

        private BaseTabPage.OnTabPageChangeListener onTabPageChangeListener = new BaseTabPage.OnTabPageChangeListener() {
	        @Override
	        public void onTabPageMenuClick() {
	            Log.e(TAG, "onTabPageMenuClick:事件从base传递到home ");
	            //将事件通知homeFragemnt的监听器
	            if (onHomeChangeListener != null) {
	                onHomeChangeListener.onTabPageMenuClick();
	            }
	        }
	    };

        //step1定义接口
	    public interface OnHomeChangeListener {
	        //step2定义事件接口方法
	        void onTabSwitch(int checkId);
	
	        void onTabPageMenuClick();
	    }
	
	    //step3提供设置监听器的方法,谁想监听,谁就调用该方法设置监听器
	    public void setOnHomeChangeListener(OnHomeChangeListener listener) {
	        onHomeChangeListener = listener;
	    }

## 菜单列表 ##

### 将菜单选项切换事件传递给MainActivity ###

        private LeftMenuFragment.OnLeftMenuChangeListener onLeftMenuChangeListener = new LeftMenuFragment.OnLeftMenuChangeListener() {
	        @Override
	        public void onMenuSwitch(int position, boolean isSwitch) {
	            Log.e(TAG, "onMenuSwitch: 事件从LeftMenuFragment传递到MainActivity");
	            //关闭侧滑菜单
	            getSlidingMenu().toggle();
	            //如果发生了切换,才将事件传递给HomeFragment
	            if (isSwitch) {
	                homeFragment.onMenuSwitch(position);
	            }
	        }
	    };

        public void onMenuSwitch(int position) {
	        Log.e(TAG, "onMenuSwitch: 事件从main传递到home" + position);
	        //将事件传递到当前的baseTabPage
	        //从FrameLayout里面获取BaseTabPage
	        BaseTabPage currentTabPage = (BaseTabPage) tabPageContainer.getChildAt(0);
	        currentTabPage.onMenuSwitch(position);
	    }

### MainActivity将菜单选项切换事件传递给HomeFragment ###

    
        private LeftMenuFragment.OnLeftMenuChangeListener onLeftMenuChangeListener = new LeftMenuFragment.OnLeftMenuChangeListener() {
	        @Override
	        public void onMenuSwitch(int position, boolean isSwitch) {
	            Log.e(TAG, "onMenuSwitch: 事件从LeftMenuFragment传递到MainActivity");
	            //关闭侧滑菜单
	            getSlidingMenu().toggle();
	            //如果发生了切换,才将事件传递给HomeFragment
	            if (isSwitch) {
	                homeFragment.onMenuSwitch(position);
	            }
	        }
	    };


### HomeFragment将事件传递给当前显示的TabPage ###

        @Override
	    public void onMenuSwitch(int position) {
	        //如果点击组图,则显示组图切换按钮,否则隐藏
	        if (position == 2) {
	            photoSwitch.setVisibility(VISIBLE);
	        } else {
	            photoSwitch.setVisibility(GONE);
	        }
	        //切换FrameLayout里面的内容
	        View view = null;
	        switch (position) {
	            case 0:
	                //要把TextView切换成ViewPage+ViewPage的指示器
	                NewsPage newsPage = new NewsPage(getContext());
	                //将data里面小标为0的数据设置给NewsPage
	                newsPage.setData(data.getData().get(0));
	                view = newsPage;
	                break;
	            case 1:
	                TextView subject = new TextView(getContext());
	                view = subject;
	                subject.setText("专题");
	                break;
	            case 2:
	                //ListView+GridView
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
	

# 第三个里程碑: 新闻页面UI实现 #

> 功能需求

1. ViewPager + ViewPager的指示器
2. ViewPager里面每个页面
	1. List列表
	2. 新闻轮播图
	3. 下拉刷新和上拉加载更多


## 新闻页面NewsPage ##

新闻页面总体结构为一个ViewPager和一个Tab类型的ViewPager指示器。

### 布局 ###


    <?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical">
	    <!--指示器-->
	    <com.viewpagerindicator.TabPageIndicator
	        android:id="@+id/indicator"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"></com.viewpagerindicator.TabPageIndicator>
	    <!--ViewPager-->
	    <android.support.v4.view.ViewPager
	        android:id="@+id/view_pager"
	        android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"></android.support.v4.view.ViewPager>
	</LinearLayout>

### 配置TabPageIndicator的样式 ###

        <style name="StyledIndicators" parent="@style/AppTheme">
	        <item name="vpiTabPageIndicatorStyle">@style/CustomTabPageIndicator</item>
	    </style>
	
	    <style name="CustomTabPageIndicator" parent="Widget.TabPageIndicator">
	        <item name="android:background">@drawable/custom_tab_indicator</item>
	        <item name="android:textColor">@color/tab_indicator_text_selector</item>
	        <item name="android:textSize">16sp</item>
	        <item name="android:paddingLeft">8dp</item>
	        <item name="android:paddingRight">8dp</item>
	        <item name="android:fadingEdge">horizontal</item>
	        <item name="android:fadingEdgeLength">8dp</item>
	    </style>

## 新闻页面子页面 ##

新闻页面子页面是一个列表，但同时又具有下列刷新和加载更多的功能，还有一个轮播图。

### 下拉刷新第三方组件 ###
  * [SwipeRefreshLayout](https://developer.android.com/training/swipe/add-swipe-interface.html)
  * [Android-PullToRefresh](https://github.com/chrisbanes/Android-PullToRefresh)
  * [Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh)
  * [Phoenix](https://github.com/Yalantis/Phoenix)
  * [Android-PullToRefresh的Android Studio版本](https://github.com/open-android/PullToRefresh)

### 集成PullToRefreshListView ###

	//在项目下的build.gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	//在app模块下的build.gradle
	dependencies {
	        compile 'com.github.open-android:PullToRefresh:v1.0'
	}

### 新闻下拉刷新列表NewsPullToRefreshListView ###

在原第三方组件PullToRefreshListView进行扩展。

            setAdapter(baseAdapter);
        	setMode(Mode.BOTH);//既能上拉又能下拉

### 轮播图第三方组件 ###

* [Android-ConvenientBanner](https://github.com/saiwu-bigkoo/Android-ConvenientBanner)
* [LoopViewPager](https://github.com/open-android/LoopViewPager)
* [FunBanner](https://github.com/uncleleonfan/FunBanner)

### 集成FunBanner ###

	//项目下的build.gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	//module下的build.gradle
	dependencies {
	    compile 'com.github.uncleleonfan:FunBanner:1.2.0'
	}

            //创建一个轮播图
	        FunBanner.Builder builder = new FunBanner.Builder(getContext());
	        funBanner = builder.setImageResIds(images)
	                .setDotSelectedColor(Color.RED)
	                .setHeightWidthRatio(0.5f)
	                .setEnableAutoLoop(true)
	                .setIndicatorBackgroundColor(R.color.colorBackTransparent)
	                .setLoopInterval(5000)
	                .setDotNormalColor(Color.WHITE)
	                .build();
	        //添加轮播图的头,getRefreshableView()获取内部的ListView
	        getRefreshableView().addHeaderView(funBanner);


# 第四个里程碑： 网络框架的搭建 #

## 网络库介绍 ##

### HttpURLConnection ###

API简单，体积较小，因而非常适用于Android项目，但是在android 2.2及以下版本中HttpUrlConnection存在着一些bug，所以建议在android 2.3以后使用HttpUrlConnection，之前使用HttpClient。

### Apache HttpClient ###

高效稳定，但是维护成本高昂，故android 开发团队不愿意维护该库更青睐轻便的HttpUrlConnection。Android 5.0后已废弃该库。

### OKHttp ###

Square公司产品，OkHttp相比HttpURLConnection和HttpClient功能更加强大。

### Volley ###

Volley是在2013年Google I/O大会上推出了一个新的网络通信框架，内部封装了HttpURLConnection和HttpClient, 解决了网络数据解析和线程切换的问题。

### Retrofit ###

Square公司产品，内部封装了OKhttp, 解决了网络数据解析和线程切换的问题。

## Volley介绍 ##

[VolleyDemo](https://github.com/uncleleonfan/VolleyDemo)

# Volley介绍 #

Volley 是 Goole I/O 2013上发布的网络通信库，使网络通信更快、更简单、更健壮。
适用于数据不大但通信频繁的场景，不适合大文件下载。

# Volley功能 #

* Json，图像等异步下载
* 网络请求的优先级处理
* 缓存
* 取消请求

# Volley工作流程 #

![](https://github.com/nangongyibin/Android_BeiJingWisdom/blob/master/picture/8.png?raw=true)

1. 线程管理
2. 缓存的管理
3. 发送网络请求过程
	1. 在主线程把请求加入请求队列
	2. 缓存线程查询请求是否有缓存，如果有缓存，则从缓存中获取数据解析返回给主线程，如果没有缓存，把请求分发给网络线程
	3. 网络线程发送请求，从服务器获取数据，解析后返回给主线程



# Volley使用 #

* [Github](https://github.com/mcxiaoke/android-volley)
* [基本用法](http://blog.csdn.net/guolin_blog/article/details/17482095)
* [加载图片](http://blog.csdn.net/guolin_blog/article/details/17482165)
* [自定义请求](http://blog.csdn.net/guolin_blog/article/details/17612763)

## 1. 添加网络权限 ##

    <uses-permission android:name="android.permission.INTERNET"/>

## 2. 添加volley依赖 ##

    compile 'com.android.volley:volley:1.0.0'

## 3. 字符串请求 ##

    public void onStartStringRequest(View view) {
        String url = "http://gank.io/api/data/Android/10/1";
        StringRequest stringRequest = new StringRequest(url, mStringListener, mErrorListener);
        Volley.newRequestQueue(this).add(stringRequest);

    }

    private Response.Listener<String> mStringListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

## 4. JsonObject请求 ##

    public void onStartJsonObjectRequest(View view) {
        String url = "http://gank.io/api/data/Android/10/1";
        //Get请求第二个参数传null
        //Post请求第二个参数传JsonObject对象
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, mJSONObjectListener, mErrorListener);
        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

    private Response.Listener<JSONObject> mJSONObjectListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                //获取网络响应中results数组中第一个元素的"desc"字段
                String desc = response.getJSONArray("results").getJSONObject(0).getString("desc");
                Toast.makeText(MainActivity.this, desc, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

## 5. JsonArray请求 ##

    public void onStartJsonArrayRequest(View view) {
        String url = "https://api.github.com/users/octocat/repos";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, mJSONArrayListener, mErrorListener);
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private Response.Listener<JSONArray> mJSONArrayListener = new Response.Listener<JSONArray>() {

        @Override
        public void onResponse(JSONArray response) {
            try {
                //获取数组中第一个元素的"name"字段
                String name = response.getJSONObject(0).getString("name");
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };



## 6. 图片请求 ##

    public void onStartImageRequest(View view) {
        String url  = "https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg";
        //第三第四个参数分别用于指定允许图片最大的宽度和高度，如果指定的网络图片的宽度或高度大于这里的最大值，
        //则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。
        ImageRequest request = new ImageRequest(url,
                mBitmapListener,
                0,
                0,
                Bitmap.Config.RGB_565,
                mErrorListener);
        Volley.newRequestQueue(this).add(request);
    }

    private Response.Listener<Bitmap> mBitmapListener = new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap response) {
            mImageView.setImageBitmap(response);
        }
    };

# Volley的封装 #

## 1. 请求队列的封装 ##

一个应用只需一个RequestQueue, 不必每次发请求都创建一个请求队列。

    public class NetworkManager {
	    private static NetworkManager sNetworkManager;
	    private RequestQueue mRequestQueue;//不仅仅属于某个Activity,属于整个应用,全局的请求队列
	    private ImageLoader mImageLoader;//全局的ImageLoader图片加载器
	    private int MAX_CACHE_SIZE = 5 * 1024 * 1024;
	
	    private NetworkManager() {
	
	    }
	
	    /**
	     * @return 两个非空 一个加锁
	     */
	    public static NetworkManager getInstance() {
	        if (sNetworkManager == null) {
	            synchronized (NetworkManager.class) {
	                if (sNetworkManager == null) {
	                    sNetworkManager = new NetworkManager();
	                }
	            }
	        }
	        return sNetworkManager;
	    }
	
	    public void init(Context context) {
	        mRequestQueue = Volley.newRequestQueue(context);
	        mImageLoader = new ImageLoader(mRequestQueue, new ImageLruCache(MAX_CACHE_SIZE));
	    }
	
	    public void setRequest(Request request) {
	        mRequestQueue.add(request);
	    }


## 2. 回调的封装 ##

    public class NetworkListener<T> implements Response.Listener<T>, Response.ErrorListener {
	    @Override
	    public void onErrorResponse(VolleyError error) {
	
	    }
	
	    @Override
	    public void onResponse(T response) {
	
	    }
	}


## 3. 自定义Gson请求 ##

### 添加Gson依赖 ###

    compile 'com.google.code.gson:gson:2.8.0'

### 创建Gson请求 ###
	
	public class GsonRequest<T> extends JsonRequest<T> {
	    
	    public GsonRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
	        super(method, url, requestBody, listener, errorListener);
	    }
	}

### 将网络请求结果转换成字符串 ###

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsedString;
        try {
            //将网络响应的字节数组转换成字符串
            parsedString = new String(response.data, PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
        }

    }


### 将字符串转换成JavaBean ###

    //将字符串转换成java bean
    T result = mGson.fromJson(parsedString, mClass);


### 返回解析后的结果 ###

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        //返回解析后的结果，使用Response对象包装
        return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
    }


### 发送请求 ###

    public void onStartGsonRequest(View view) {
        String url = "http://gank.io/api/data/Android/10/1";
        GsonRequest<GankBean> request = new GsonRequest<GankBean>(GankBean.class, url, mGankBeanNetworkListener);
        NetworkManager.getInstance().sendRequest(request);
    }


## 4. ImageLoader的封装 ###

### NetworkImageView的使用 ###

    mNetworkImageView = (NetworkImageView) findViewById(R.id.network_image_view);
    mNetworkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
    String url  = "https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg";
    NetworkImageView.setImageUrl(url, NetworkManager.getInstance().getImageLoader());

### ImageLoader的创建 ###


ImageLoader是加载和缓存网络图片的工具。由于它也要用到RequestQueue, 一个应用也只需要一个ImageLoader,所以同样的封装到NetworkManager中。

        public void init(Context context) {
	        mRequestQueue = Volley.newRequestQueue(context);
	        mImageLoader = new ImageLoader(mRequestQueue, new ImageLruCache(MAX_CACHE_SIZE));
	    }


### LRU原理 ###

Least Recent Used。当访问一条数据时，数据会放在表头，当缓存超过最大值时，会删除表尾的数据。


        public static class ImageLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

	        /**
	         * @param maxSize for caches that do not override {@link #sizeOf}, this is
	         *                the maximum number of entries in the cache. For all other caches,
	         *                this is the maximum sum of the sizes of the entries in this cache.
	         *                缓存的最大值
	         */
	        public ImageLruCache(int maxSize) {
	            super(maxSize);
	        }
	
	        /**
	         * 返回缓存数据的字节数
	         *
	         * @param key
	         * @param value
	         * @return
	         */
	        @Override
	        protected int sizeOf(String key, Bitmap value) {
	            return value.getByteCount();
	        }
	
	        /**
	         * 从LRU缓存中获取图片缓存
	         *
	         * @param url
	         * @return
	         */
	        @Override
	        public Bitmap getBitmap(String url) {
	            return get(url);
	        }
	
	        /**
	         * 将图片存入到LruCache
	         *
	         * @param url
	         * @param bitmap
	         */
	        @Override
	        public void putBitmap(String url, Bitmap bitmap) {
	            put(url, bitmap);
	        }
	    }


### 图片的三级缓存 ###


# Volley的封装层级 #

Volley的封装级别类似Retrofit，[FunHttp](https://github.com/uncleleonfan/FunHttp)。 Retrofit，FunHttp都是对OKhttp的一层封装，解决了数据转换和线程切换等问题。
Volley内部使用HttpClient或者HttpURLConnection完成网络请求，由于Volley的良好扩展性，还可以配置使用Okhttp进行网络请求。
可以看出HttpClient，HttpURLConnection，Okhttp属于同一层级，Retrofit，Volley，FunHttp属于同一层级。



# Volley的源码分析 #

## 不要纠结细节，看主要逻辑和框架 ##

[如何阅读源码](http://www.jianshu.com/p/be86e5678252)

[源码分析大全](http://www.codekk.com/open-source-project-analysis)

## 请求队列的初始化 ##

1. 磁盘缓存的初始化（DiskBasedCache）mCache
2. 执行网络请求对象（Network）的创建 mNetwork
3. 初始化网络请求的线程池mDispatchers = new NetworkDispatcher[threadPoolSize];，默认大小是4.
4. 创建网络请求响应和错误的分发器mDelivery=new ExecutorDelivery(new Handler(Looper.getMainLooper()))


## 请求队列的启动 ##

1. 创建缓存分发器，启动该线程，执行run方法，run方法里面初始化磁盘缓存（把缓存文件的头读取出来，存入集合）
	    
		mCacheDispatcher = new CacheDispatcher(mCacheQueue, mNetworkQueue, mCache, mDelivery);
        mCacheDispatcher.start();


2. 创建网络分发器并且启动

        // Create network dispatchers (and corresponding threads) up to the pool size.
        for (int i = 0; i < mDispatchers.length; i++) {
            NetworkDispatcher networkDispatcher = new NetworkDispatcher(mNetworkQueue, mNetwork,
                    mCache, mDelivery);
            mDispatchers[i] = networkDispatcher;
            networkDispatcher.start();
        }

## 发送请求 ##

1. 首先网络请求添加到缓存请求队列mCacheQueue，CacheDispatcher的run方法里面的监控mCacheQueue，如果mCacheQueue有请求，则拿出来，查看是否有缓存，如果有并且没有过期，则解析网络缓存的结果，分发到主线程
2. 请求加入到网络请求队列mNetworkQueue,NetworkDispatcher的run方法里面监控mNetworkQueue,如果有请求，则拿出来发送网络请求，获取到结果后解析，然后存入缓存，最后分发到主线程。




## 网络数据与UI界面的对应关系 ##

* categories.json对应新闻中心数据结构
* 每个新闻频道对应一个list.json

 
## Volley集成 ##

>  需求分析

1. 刷新TabPagerIndicator
2. 刷新新闻列表里面标题和时间
3. 刷新新闻列表里的图片
4. 刷新新闻轮播图
5. 下拉刷新
6. 上拉加载更多

### 发送请求获取Categories.json ###

        /**
	     * 加载新闻中心的数据
	     */
	    @Override
	    public void loadDataFromServer() {
	        String url = "http://47.105.71.243:8080/resource/zhbj/categories.json";
	        GsonRequest<CategoryBean> request = new GsonRequest<>(url, CategoryBean.class, categoryBeanNetworkListener);
	        NetworkManager.getInstance().setRequest(request);
	    }
	
	    private NetworkListener<CategoryBean> categoryBeanNetworkListener = new NetworkListener<CategoryBean>() {
	        @Override
	        public void onResponse(CategoryBean response) {
	            data = response;//保存数据
	            //网络成功后,切换到新闻页面
	            onMenuSwitch(0);
	        }
	
	        @Override
	        public void onErrorResponse(VolleyError error) {
	            super.onErrorResponse(error);
	        }
	    };

        public void setData(CategoryBean.DataBean data) {
	        this.data = data;
	        //通知界面刷新
	        indicator.notifyDataSetChanged();//指示器刷新
	        //刷新ViewPager
	        pagerAdapter.notifyDataSetChanged();
	    }


### 刷新新闻列表 ###

        public void setUrl(String url) {
	        this.url = url;
	        GsonRequest<NewsListBean> request = new GsonRequest<NewsListBean>(url, NewsListBean.class, newsListBeanNetworkListener);
	        NetworkManager.getInstance().setRequest(request);
	    }
	
	    private NetworkListener<NewsListBean> newsListBeanNetworkListener = new NetworkListener<NewsListBean>() {
	        @Override
	        public void onResponse(NewsListBean response) {
	            news = response.getData().getNews();
	            baseAdapter.notifyDataSetChanged();//通知刷新
	        }
	    };

### 刷新轮播图 ###

        private NetworkListener<NewsListBean> newsListBeanNetworkListener = new NetworkListener<NewsListBean>() {
	        @Override
	        public void onResponse(NewsListBean response) {
	            Log.e(TAG, "onResponse: " + response.getData().getNews().get(0).getTitle());
	            news = response.getData().getNews();//保存新闻列表数据
	            //刷新新闻列表
	            baseAdapter.notifyDataSetChanged();
	            //保存more字段
	            more = response.getData().getMore();
	            List<NewsListBean.DataBean.TopnewsBean> topnews = response.getData().getTopnews();//轮播图数据
	            //构建图片的url的集合以及标题的集合
	            ArrayList<String> listUrls = new ArrayList<>();
	            ArrayList<String> listTitles = new ArrayList<>();
	            for (int i = 0; i < topnews.size(); i++) {
	                listUrls.add(topnews.get(i).getTopimage());
	                listTitles.add(topnews.get(i).getTitle());
	            }
	            //刷新轮播图
	            funBanner.setImageUrlsAndTitles(listUrls, listTitles);//设置图片的url集合和标题集合
	        }
	
	        @Override
	        public void onErrorResponse(VolleyError error) {
	            super.onErrorResponse(error);
	        }
	    };


### 下拉刷新 ###

        private NetworkListener<NewsListBean> moreListener = new NetworkListener<NewsListBean>() {
	        @Override
	        public void onResponse(NewsListBean response) {
	            Toast.makeText(getContext(), "加载更多数据成功", Toast.LENGTH_SHORT).show();
	            //更新more字段
	            more = response.getData().getMore();
	            //将获取到的更多新闻数据加入当前的新闻列表中
	            news.addAll(response.getData().getNews());
	            baseAdapter.notifyDataSetChanged();
	            onRefreshComplete();//通知加载完成
	        }
	    };

### 上拉加载更多 ###

        private OnRefreshListener2<ListView> onRefreshListener = new OnRefreshListener2<ListView>() {
	
	        @Override
	        public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
	            //下拉刷新
	            //发送网络请求,获取新闻列表的数据
	            GsonRequest<NewsListBean> request = new GsonRequest<NewsListBean>(url, NewsListBean.class, refreshListener);
	            NetworkManager.getInstance().setRequest(request);
	        }
	
	        @Override
	        public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
	            //加载更多数据
	            //用more字段发送网路请求
	            if (more.length() > 0) {
	                //more字段有效,表示还有更多数据
	                String url = Constant.BASE_URL + more;
	                GsonRequest<NewsListBean> request = new GsonRequest<NewsListBean>(url, NewsListBean.class, moreListener);
	                NetworkManager.getInstance().setRequest(request);
	            } else {
	                //more字段无效,没有更多数据
	                Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
	                //延时隐藏
	                postDelayed(new Runnable() {
	                    @Override
	                    public void run() {
	                        onRefreshComplete();//隐藏脚
	                    }
	                }, 1000);
	            }
	        }
	    };



# 第五个里程碑：新闻详情和组图实现 #

## 新闻详情 ##

> 需求分析

1. 点击新闻列表，进入新闻详情
2. 已读新闻变灰
3. 显示新闻详情
4. 返回和字体大小的调整

### 点击进入新闻详情 ###

        private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
	        /**
	         * @param parent
	         * @param view
	         * @param position 会把ListView头计算在内
	         * @param id
	         */
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            position = position - 2;//调整位置
	            //获取点击位置新闻的标识id  将id保存sp
	            int newsId = news.get(position).getId();
	            SPUtils.saveBoolean(getContext(), String.valueOf(newsId), true);//true标识新闻已读
	            //刷新列表,将已读新闻变灰
	            baseAdapter.notifyDataSetChanged();
	            Intent intent = new Intent(getContext(), NewsDetailActivity.class);
	            intent.putExtra("url", news.get(position).getUrl());
	            getContext().startActivity(intent);
	        }
	    };

### 已读新闻变灰 ###

        private BaseAdapter baseAdapter = new BaseAdapter() {
	        @Override
	        public int getCount() {
	            if (news == null) {
	                return 0;
	            }
	            return news.size();
	        }
	
	        @Override
	        public Object getItem(int position) {
	            return null;
	        }
	
	        @Override
	        public long getItemId(int position) {
	            return 0;
	        }
	
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            ViewHolder viewHolder = null;
	            if (convertView == null) {
	                convertView = View.inflate(getContext(), R.layout.view_news_list_item, null);
	                viewHolder = new ViewHolder(convertView);
	                convertView.setTag(viewHolder);
	            } else {
	                viewHolder = (ViewHolder) convertView.getTag();
	            }
	            NewsListBean.DataBean.NewsBean newsBean = news.get(position);
	            viewHolder.newsTitle.setText(newsBean.getTitle());//刷新新闻标题
	            viewHolder.newsPublishTime.setText(newsBean.getPubdate());//刷新发布时间
	            //如果新闻已读,则变灰
	            if (SPUtils.getBoolean(getContext(), String.valueOf(newsBean.getId()))) {
	                viewHolder.newsTitle.setTextColor(Color.GRAY);
	            } else {
	                //设置回来,因为listview有回收机制
	                viewHolder.newsTitle.setTextColor(Color.BLACK);
	            }
	            //刷新新闻图片
	            //Glide FreSco Picassio   封装图片的三级缓存
	            Glide.with(getContext()).load(newsBean.getListimage()).into(viewHolder.newsImage);
	            return convertView;
	        }
	    };


### 加载新闻详情 ###

            //开启js支持
	        settings = webView.getSettings();
	        settings.setJavaScriptEnabled(true);
	        String url = getIntent().getStringExtra("url");
	        webView.loadUrl(url);


### 选择字体大小 ###

        private void showTextSizeDialog() {
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setTitle("字体大小")
	                .setSingleChoiceItems(TEXT_SIZE_CHOICES, checkItem, new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                        dialog.dismiss();
	                        checkItem = which;//更新选中位置
	                        changeTextSize();
	                    }
	                }).show();
	    }
	
	    private void changeTextSize() {
	        switch (checkItem) {
	            case 0:
	                settings.setTextSize(WebSettings.TextSize.SMALLEST);
	                break;
	            case 1:
	                settings.setTextSize(WebSettings.TextSize.SMALLER);
	                break;
	            case 2:
	                settings.setTextSize(WebSettings.TextSize.NORMAL);
	                break;
	            case 3:
	                settings.setTextSize(WebSettings.TextSize.LARGER);
	                break;
	            case 4:
	                settings.setTextSize(WebSettings.TextSize.LARGEST);
	                break;
	        }
	    }


## 组图实现 ##

> 需求分析

1. ListView和GridView
2. 点击切换ListView和GridView


### 组图页面PhotoPage的创建 ###


    <?xml version="1.0" encoding="utf-8"?>
	<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent">
	
	    <ListView
	        android:id="@+id/list_view"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:visibility="visible"></ListView>
	
	    <GridView
	        android:id="@+id/grid_view"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:horizontalSpacing="8dp"
	        android:numColumns="2"
	        android:verticalSpacing="8dp"
	        android:visibility="gone"></GridView>
	</FrameLayout>


### 加载组图数据 ###

        public void setUrl(String url) {
	        this.url = url;
	        GsonRequest<NewsListBean> request = new GsonRequest<NewsListBean>(url, NewsListBean.class, newsListBeanNetworkListener);
	        NetworkManager.getInstance().setRequest(request);
	    }
	
	    private NetworkListener<NewsListBean> newsListBeanNetworkListener = new NetworkListener<NewsListBean>() {
	        @Override
	        public void onResponse(NewsListBean response) {
	            news = response.getData().getNews();
	            baseAdapter.notifyDataSetChanged();//通知刷新
	        }
	    };

### ListView和GridView的切换 ###

        public void onSwitchPhoto(boolean isList) {
	        if (isList) {
	            //切换到listView
	            listView.setVisibility(VISIBLE);
	            gridView.setVisibility(GONE);
	        } else {
	            //切换到gridView
	            listView.setVisibility(GONE);
	            gridView.setVisibility(VISIBLE);
	        }
	    }

效果图:


![](https://github.com/nangongyibin/Android_BeiJingWisdom/blob/master/picture/3.gif?raw=true)

