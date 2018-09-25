package com.bawei.chenxiaoxu.chenxiaoxu20180925.model;

import com.bawei.chenxiaoxu.chenxiaoxu20180925.utils.Oktttputils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/25.
 */

public class IModelImp implements IModel {
    @Override
    public void getReData(String urlString, final CallBack callBack) {
        FormBody body = new FormBody.Builder().add("uid", "71").build();

        Oktttputils.getIntance().post(urlString, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                callBack.Success(string);
            }
        });
    }
}
