package com.ngyb.beijingwisdom.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.constant.Constant;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/7 16:47
 */
public class NewsDetailActivity extends BaseLocalActivity implements View.OnClickListener {
    private static String[] TEXT_SIZE_CHOICES = {"最小", "较小", "正常", "较大", "最大"};
    private ImageView back;
    private ImageView iconShare;
    private ImageView textSize;
    private WebView webView;
    private String url;
    private int checkItem = 2;
    private WebSettings settings;
    private static final String TAG = "NewsDetailActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void init() {
        initView();
        initJs();
        initIntent();
        initData();
        initListener();
    }

    private void initListener() {
        back.setOnClickListener(this);
        iconShare.setOnClickListener(this);
        textSize.setOnClickListener(this);
    }

    private void initData() {
        webView.loadUrl(Constant.BASE_URL+url);
    }

    private void initIntent() {
        url = getIntent().getStringExtra(Constant.url);
        Log.e(TAG, "initIntent: " + url);
    }

    private void initJs() {
        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }


    private void initView() {
        back = findViewById(R.id.back);
        iconShare = findViewById(R.id.icon_share);
        textSize = findViewById(R.id.text_size);
        webView = findViewById(R.id.web_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.icon_share:
                break;
            case R.id.text_size:
                showTextSizeDialog();
                break;
        }
    }

    private void showTextSizeDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("字体大小")
                .setSingleChoiceItems(TEXT_SIZE_CHOICES, checkItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        checkItem = i;
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
}
