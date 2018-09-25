package com.bawei.chenxiaoxu.chenxiaoxu20180925.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.chenxiaoxu.chenxiaoxu20180925.R;
import com.bawei.chenxiaoxu.chenxiaoxu20180925.witget.CustomView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button myview;
    private Button bt;
    private EditText ed;
    private LayoutInflater layoutInflater;
    private List<String> list;
    private CustomView liu;
    private TextView textas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        list = new ArrayList<>();
        ed = findViewById(R.id.ed);
        bt = findViewById(R.id.bt);
        myview = findViewById(R.id.myview);
        liu = findViewById(R.id.liu);
        layoutInflater = LayoutInflater.from(this);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s = ed.getText().toString();
               // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                View inflate = layoutInflater.inflate(R.layout.as, null);
                textas = inflate.findViewById(R.id.textas);
                list.add(s);
                for (int i = 0; i < list.size(); i++) {
                    textas.setText(list.get(i));
                    textas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                liu.addView(inflate);
            }
        });
        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liu.removeAllViews();
            }
        });
    }
}
