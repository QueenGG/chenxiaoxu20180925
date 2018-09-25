package com.bawei.chenxiaoxu.chenxiaoxu20180925.utils;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/25.
 */

public class Oktttputils {
    private static OkHttpClient okHttpClient;
    private static Oktttputils oktttputils;

    public Oktttputils() {
        if(oktttputils==null){
            okHttpClient=new OkHttpClient();
        }
    }
    //单例
    public static Oktttputils getIntance(){
        if(oktttputils==null){
            synchronized (Oktttputils.class){
                if (oktttputils==null){
                    oktttputils=new Oktttputils();
                }
            }
        }
        return oktttputils;
    }
    //get请求
    public void get(String url_get, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url(url_get).build();
        okHttpClient.newCall(build).enqueue(callback);
    }
    //post请求
    public void post(String url_post, FormBody formBody,Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request post = new Request.Builder().url(url_post).method("POST", formBody).build();
        okHttpClient.newCall(post).enqueue(callback);
    }
}
