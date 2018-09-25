package com.bawei.chenxiaoxu.chenxiaoxu20180925.model;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/25.
 */

public interface IModel {
    interface CallBack{
        void Success(String string);
    }
    void getReData(String urlString,CallBack callBack);
}
