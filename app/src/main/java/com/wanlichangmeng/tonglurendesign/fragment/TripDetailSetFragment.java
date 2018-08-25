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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TripDetailSetFragment extends Fragment {

    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;

    @BindView(R.id.fragment_set_trip_back_button)
    ImageView btn_bcak;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip_detail_set, container, false);
        ButterKnife.bind(this,view);
        Init();
        return view;
    }
    public void Init(){
        btn_bcak.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                //回退到Fragment1
                FragmentManager fm = getFragmentManager();
                //将当前的事务退出回退栈
                fm.popBackStack();
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
