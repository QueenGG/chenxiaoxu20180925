package com.bawei.chenxiaoxu.chenxiaoxu20180925.witget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.chenxiaoxu.chenxiaoxu20180925.R;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/25.
 */

public class MyJiajian extends LinearLayout {

    private TextView jia;
    private TextView jian;
    private EditText numet;
    private int num=1;

    public MyJiajian(Context context) {
        this(context,null);
    }

    public MyJiajian(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyJiajian(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.jia_jian, this, true);
        jia = inflate.findViewById(R.id.jia);
        jian = inflate.findViewById(R.id.jian);
        numet = inflate.findViewById(R.id.numet);

        numet.setText(num+"");

        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                numet.setText(num+"");
                if (jiaJianLiener!=null){
                    jiaJianLiener.getNum(num);
                }
            }
        });
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if(num<=0){
                    Toast.makeText(context, "数量不能小于1", Toast.LENGTH_SHORT).show();
                    num=1;
                }
                numet.setText(num+"");
                if (jiaJianLiener!=null){
                    jiaJianLiener.getNum(num);
                }
            }
        });

    }
    public void setNumEt(int n){
        numet.setText(n+"");
        num=Integer.parseInt(numet.getText().toString());
    }
    private JiaJianLiener jiaJianLiener;

    public void setJiaJianLiener(JiaJianLiener jiaJianLiener) {
        this.jiaJianLiener = jiaJianLiener;
    }

    public interface JiaJianLiener{
        void getNum(int num);
    }
}
