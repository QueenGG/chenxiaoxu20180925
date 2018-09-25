package com.bawei.chenxiaoxu.chenxiaoxu20180925.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.chenxiaoxu.chenxiaoxu20180925.R;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.activity.ShowActivity;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.bean.CartBean;

import java.util.List;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/25.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> implements CartCheckboxListener{
    Context context;
    List<CartBean.DataBean> cartlist;
    private CartAllCheckboxListener allCheckboxListener;
    public CartAdapter(Context context, List<CartBean.DataBean> list) {
        this.context=context;
        this.cartlist=list;
    }
    public  void addPageData(List<CartBean.DataBean> list){
        if(cartlist!=null){
            cartlist.addAll(list);
            notifyDataSetChanged();
        }
    }

    public List<CartBean.DataBean> getCartlist() {
        return cartlist;
    }

    public void setAllCheckboxListener(CartAllCheckboxListener allCheckboxListener) {
        this.allCheckboxListener = allCheckboxListener;
    }

    public void setCartlist(List<CartBean.DataBean> cartlist) {
        this.cartlist = cartlist;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(inflate);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {
        final CartBean.DataBean dataBean = cartlist.get(position);
        holder.text_cartname.setText(dataBean.getSellerName());

        holder.cartCheckbox.setSelected(dataBean.isSelected());

        holder.re_list.setLayoutManager(new LinearLayoutManager(context));

        final PaduAdapter paduAdapter = new PaduAdapter(context,dataBean.getList());

        holder.re_list.setAdapter(paduAdapter);

        paduAdapter.setCartCheckboxListener(this);
        for (int i = 0; i < dataBean.getList().size(); i++) {
            if(!dataBean.getList().get(i).isSelected()){
                holder.cartCheckbox.setChecked(false);
                break;

            }else{
                holder.cartCheckbox.setChecked(true);
            }
        }

        holder.cartCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.cartCheckbox.isChecked()){
                        dataBean.setSelected(true);
                    for (int i = 0; i < dataBean.getList().size(); i++) {
                        dataBean.getList().get(i).setSelected(true);
                    }
                }else{
                    dataBean.setSelected(false);
                    for (int i = 0; i < dataBean.getList().size(); i++) {
                        dataBean.getList().get(i).setSelected(false);
                    }
                }
                notifyDataSetChanged();
                if(allCheckboxListener!=null){
                    allCheckboxListener.notifyAllCheckboxStatus();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    @Override
    public void notifyParent() {
        notifyDataSetChanged();
        if(allCheckboxListener!=null){
            allCheckboxListener.notifyAllCheckboxStatus();
        }
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        private  CheckBox cartCheckbox;
        private  TextView text_cartname;
        private  RecyclerView re_list;

        public CartViewHolder(View itemView) {
            super(itemView);
            cartCheckbox = itemView.findViewById(R.id.cartCheckbox);
            text_cartname = itemView.findViewById(R.id.text_cartname);
            re_list = itemView.findViewById(R.id.re_list);
        }
    }
}
