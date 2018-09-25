package com.bawei.chenxiaoxu.chenxiaoxu20180925.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.chenxiaoxu.chenxiaoxu20180925.R;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.bean.CartBean;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.witget.MyJiajian;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/25.
 */

public class PaduAdapter extends RecyclerView.Adapter<PaduAdapter.PaduViewHolder>{

    Context context;
    List<CartBean.DataBean.ListBean> listBeanList;
    CartAllCheckboxListener cartAllCheckboxListener;
    CartCheckboxListener cartCheckboxListener;

    public void setCartCheckboxListener(CartCheckboxListener cartCheckboxListener) {
        this.cartCheckboxListener = cartCheckboxListener;
    }

    public void setCartAllCheckboxListener(CartAllCheckboxListener cartAllCheckboxListener) {
        this.cartAllCheckboxListener = cartAllCheckboxListener;
    }

    public PaduAdapter(Context context, List<CartBean.DataBean.ListBean> list) {
        this.context=context;
        this.listBeanList=list;
    }

    @NonNull
    @Override
    public PaduViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.padu_item, parent, false);
        PaduViewHolder paduViewHolder = new PaduViewHolder(inflate);

        return paduViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PaduViewHolder holder, int position) {
        final CartBean.DataBean.ListBean listBean = listBeanList.get(position);
        holder.paduName.setText(listBean.getTitle());
        holder.paduprice.setText("优惠价：¥"+listBean.getBargainPrice());

        String[] im = listBean.getImages().split("\\|");
        Glide.with(context).load(im[0]).into(holder.paduicon);

        holder.paduCheckbox.setChecked(listBean.isSelected());
        holder.jiajianqi.setNumEt(listBean.getToPri());

        holder.jiajianqi.setJiaJianLiener(new MyJiajian.JiaJianLiener() {
            @Override
            public void getNum(int num) {
                listBean.setToPri(num);
                if(cartCheckboxListener!=null){
                    cartCheckboxListener.notifyParent();
                }
            }
        });

        holder.paduCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.paduCheckbox.isChecked()){
                        listBean.setSelected(true);
                }else{
                    listBean.setSelected(false);
                }
                if(cartCheckboxListener!=null){
                    cartCheckboxListener.notifyParent();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeanList.size();
    }

    class PaduViewHolder extends RecyclerView.ViewHolder {

        private  CheckBox paduCheckbox;
        private  ImageView paduicon;
        private  TextView paduName;
        private  TextView paduprice;
        private  MyJiajian jiajianqi;

        public PaduViewHolder(View itemView) {
            super(itemView);
            paduCheckbox = itemView.findViewById(R.id.paduCheckbox);
            paduicon = itemView.findViewById(R.id.paduicon);
            paduName = itemView.findViewById(R.id.paduName);
            paduprice = itemView.findViewById(R.id.paduprice);
            jiajianqi = itemView.findViewById(R.id.jiajianqi);
        }
    }
}
