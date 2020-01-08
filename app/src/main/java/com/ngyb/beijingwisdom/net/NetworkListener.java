package com.ngyb.beijingwisdom.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/8 10:32
 */
public class NetworkListener<T> implements Response.Listener<T>, Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(T response) {

    }
}
