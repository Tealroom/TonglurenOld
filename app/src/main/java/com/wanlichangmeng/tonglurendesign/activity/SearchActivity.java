package com.wanlichangmeng.tonglurendesign.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruffian.library.widget.REditText;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;
    @BindView(R.id.activity_search_edit)
    REditText ecitText;
    @BindView(R.id.activity_search_btn1)
    Button btn_search;
    @BindView(R.id.recycler_view)
    RecyclerView recycler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        ActivityUtils.transparencyBar(this,ll_bar4);
        Init();
    }

    public void Init(){


        //设置布局管理器
        StaggeredGridLayoutManager layoutManager= new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recycler.setLayoutManager (layoutManager);
        btn_search.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

            }
        });



    }




}
