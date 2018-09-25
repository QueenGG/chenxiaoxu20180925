package com.bawei.chenxiaoxu.chenxiaoxu20180925.presenter;

import com.bawei.chenxiaoxu.chenxiaoxu20180925.activity.ShowActivity;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.model.IModel;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.model.IModelImp;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.view.IView;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/25.
 */

public class IPresenterImp implements IPresenter {
    IView iView;
    private final IModelImp iModel;

    public IPresenterImp(IView iView) {
        this.iView=iView;
        iModel = new IModelImp();
    }

    @Override
    public void getData(String urlString) {
        iModel.getReData(urlString, new IModel.CallBack() {
            @Override
            public void Success(String string) {
                iView.Show(string);
            }
        });
    }
    //解绑解决mvp内存泄漏
    public void destoy(){
        iView=null;
    }
}
