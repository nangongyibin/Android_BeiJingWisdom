package com.ngyb.beijingwisdom.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/7 17:09
 */
public class NetworkManager {
    private static NetworkManager sNetworkManager;
    private RequestQueue requestQueue;
    private int MAX_CACHE_SIZE = 5 * 1024 * 1024;
    private ImageLoader imageLoader;

    private NetworkManager() {

    }

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
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLruCache(MAX_CACHE_SIZE));
    }

    public void setRequest(Request request) {
        requestQueue.add(request);
    }

    public static class ImageLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

        public ImageLruCache(int maxSize) {
            super(maxSize);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }

        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            put(url, bitmap);
        }
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
