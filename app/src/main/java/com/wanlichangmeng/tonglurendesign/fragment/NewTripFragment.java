package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewTripFragment extends Fragment {

    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;
    @BindView(R.id.button1_fragment_new_trip)
    Button nextstep;


    TripDetailSetFragment fragment2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_trip, container, false);
        ButterKnife.bind(this,view);

        nextstep.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                fragment2=new TripDetailSetFragment();
                //获取fragment管理器
                FragmentManager fm = getFragmentManager();
                //打开事务
                FragmentTransaction ft = fm.beginTransaction();
                //把内容显示至帧布局
                ft.replace(R.id.fragment1_activity_trip, fragment2);
                //将当前的事务添加到了回退栈
                ft.addToBackStack(null);
                //提交
                ft.commit();
            }
        });
        return view;
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
