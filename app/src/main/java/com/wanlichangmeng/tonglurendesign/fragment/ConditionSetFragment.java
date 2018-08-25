package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ConditionSetFragment extends Fragment {

    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;
    @BindView(R.id.trip_type)
    NiceSpinner trip_type;
    @BindView(R.id.trip_mode)
    NiceSpinner trip_mode;
    @BindView(R.id.end_place)
    TextView end_place;
    @BindView(R.id.start_place)
    TextView start_place;


    MapFragment fragment2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_condition_set, container, false);
        ButterKnife.bind(this,view);
        Init();
        return view;
    }

    public void Init(){
        List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
        trip_type.attachDataSource(dataset);
        trip_mode.attachDataSource(dataset);

        end_place.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                fragment2=new MapFragment();
                //获取fragment管理器
                FragmentManager fm = getFragmentManager();
                //打开事务
                FragmentTransaction ft = fm.beginTransaction();
                //把内容显示至帧布局
                ft.replace(R.id.fragment1_activity_condition_set, fragment2);
                //将当前的事务添加到了回退栈
                ft.addToBackStack(null);
                //提交
                ft.commit();
//                //回退到Fragment1
//                FragmentManager fm = getFragmentManager();
//                //将当前的事务退出回退栈
//                fm.popBackStack();
            }
        });

        start_place.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                fragment2=new MapFragment();
                //获取fragment管理器
                FragmentManager fm = getFragmentManager();
                //打开事务
                FragmentTransaction ft = fm.beginTransaction();
                //把内容显示至帧布局
                ft.replace(R.id.fragment1_activity_condition_set, fragment2);
                //将当前的事务添加到了回退栈
                ft.addToBackStack(null);
                //提交
                ft.commit();
//                //回退到Fragment1
//                FragmentManager fm = getFragmentManager();
//                //将当前的事务退出回退栈
//                fm.popBackStack();
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {
        ActivityUtils.initStateInFragment(this,ll_bar4);
//        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.colorPrimary);//设置系统状态栏颜色
//        tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.mybgcolor_02));//设置系统状态栏背景图
        super.onResume();
    }

}
