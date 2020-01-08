package com.ngyb.beijingwisdom.net;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/8 10:25
 */
public class GsonRequest<T> extends JsonRequest<T> {
    private Gson gson = new Gson();
    private Class<T> mClass;

    public GsonRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public GsonRequest(String url, Class<T> classz, NetworkListener networkListener) {
        this(Method.GET, url, null, networkListener, networkListener);
        mClass = classz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String parseString = new String(response.data, "utf-8");
            T result = gson.fromJson(parseString, mClass);
            Cache.Entry entry = HttpHeaderParser.parseCacheHeaders(response);
            entry.ttl = System.currentTimeMillis() +5*60*1000;
            return Response.success(result,entry);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
