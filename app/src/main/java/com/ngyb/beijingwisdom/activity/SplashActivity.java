package com.ngyb.beijingwisdom.activity;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.constant.Constant;
import com.ngyb.utils.SharedPreferencesUtils;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/6 20:22
 */
public class SplashActivity extends BaseLocalActivity implements Animation.AnimationListener {
    public static final int DURATION = 2000;
    private ImageView splashImage;
    private SharedPreferencesUtils sp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        initView();
        initClass();
        initAnimation();
    }

    private void initClass() {
        sp = new SharedPreferencesUtils(mContext);
    }

    private void initAnimation() {
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setAnimationListener(this);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(DURATION);
        animationSet.addAnimation(rotateAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(DURATION);
        animationSet.addAnimation(scaleAnimation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(DURATION);
        animationSet.addAnimation(alphaAnimation);

        splashImage.startAnimation(animationSet);
    }

    private void initView() {
        splashImage = findViewById(R.id.splash_image);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (sp.getBoolean(Constant.SP_KEY_STARTED,false)){
            navigateTo(MainActivity.class);
        }else{
            navigateTo(TutorialActivity.class);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
