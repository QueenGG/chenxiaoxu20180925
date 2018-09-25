package com.bawei.chenxiaoxu.chenxiaoxu20180925.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.chenxiaoxu.chenxiaoxu20180925.R;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.adapter.CartAdapter;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.adapter.CartAllCheckboxListener;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.bean.CartBean;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.presenter.IPresenterImp;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.view.IView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity implements IView,CartAllCheckboxListener{
    private int page=1;
    private CheckBox allCheck;
    private TextView toPrice;
    public static final String URL_STRING="https://www.zhaoapi.cn/product/getCarts";
    private XRecyclerView xcart_list;
    private IPresenterImp iPresenter;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                String string= (String) msg.obj;
                Gson gson = new Gson();
                CartBean cartBean = gson.fromJson(string, CartBean.class);
                showCart(cartBean);
            }
        }
    };
    private List<CartBean.DataBean> list;
    private CartAdapter cartAdapter;

    private void showCart(CartBean cartBean) {
        List<CartBean.DataBean> data = cartBean.getData();
        if(cartBean != null && data !=null ){
            if(page==1){
                list=data;
                cartAdapter = new CartAdapter(ShowActivity.this, list);
                xcart_list.setAdapter(cartAdapter);
            }else{
                if(cartAdapter!=null){
                    cartAdapter.addPageData(cartBean.getData());
                }
                xcart_list.loadMoreComplete();
            }
            cartAdapter.setAllCheckboxListener(this);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();
        loadData();
    }
    //初始化控件
    private void init() {

        list = new ArrayList<>();
        allCheck = findViewById(R.id.allCheck);
        toPrice = findViewById(R.id.toPrice);
        xcart_list = findViewById(R.id.xcart_list);

        xcart_list.setLayoutManager(new LinearLayoutManager(this));
        //加载更多
        xcart_list.setLoadingMoreEnabled(true);
        xcart_list.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                page++;
                loadData();
            }
        });

        allCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allCheck.isChecked()){
                    if(list!=null&&list.size()>0){
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(true);
                            for (int j = 0; j < list.get(i).getList().size(); j++) {
                                list.get(i).getList().get(j).setSelected(true);
                            }
                        }
                    }
                }else{
                    if(list!=null&&list.size()>0){
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(false);
                            for (int j = 0; j < list.get(i).getList().size(); j++) {
                                list.get(i).getList().get(j).setSelected(false);
                            }
                        }
                    }
                }
                cartAdapter.notifyDataSetChanged();
                //刷新总价
                totalPrice();
            }
        });

    }
    private void loadData() {
        iPresenter = new IPresenterImp(this);
        iPresenter.getData(URL_STRING);
    }

    @Override
    public void Show(final String string) {
        Message message = new Message();
        message.what=0;
        message.obj=string;
        handler.sendMessage(message);
    }

    @Override
    public void notifyAllCheckboxStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        if(cartAdapter!=null){
            for (int i = 0; i < cartAdapter.getCartlist().size(); i++) {
                stringBuilder.append(cartAdapter.getCartlist().get(i).isSelected());
                for (int j = 0; j < cartAdapter.getCartlist().get(i).getList().size(); j++) {
                    stringBuilder.append(cartAdapter.getCartlist().get(i).getList().get(j).isSelected());

                }
            }
        }
        if(stringBuilder.toString().contains("false")){
            allCheck.setChecked(false);
        }else{
            allCheck.setChecked(true);
        }
        totalPrice();
    }

    private void totalPrice() {
        double toprice=0;
        for (int i = 0; i < cartAdapter.getCartlist().size(); i++) {
            for (int j = 0; j < cartAdapter.getCartlist().get(i).getList().size(); j++) {
                if(cartAdapter.getCartlist().get(i).getList().get(j).isSelected()){
                    CartBean.DataBean.ListBean listBean = cartAdapter.getCartlist().get(i).getList().get(j);
                    toprice+=listBean.getToPri()*listBean.getBargainPrice();
                }
            }
        }
        toPrice.setText("总价：¥"+toprice);
    }
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
