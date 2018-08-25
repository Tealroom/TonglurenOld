package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.wanlichangmeng.tonglurendesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TripDetailFragment extends Fragment {

    //获取地图控件引用
    @BindView(R.id.fragment_trip_map)
    MapView mMapView;

    private UiSettings mUiSettings;
    private AMap aMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip_detail, container, false);
        ButterKnife.bind(this,view);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图



        mMapView.onCreate(savedInstanceState);
        init();
        return view;
    }
    /**
     * 初始化AMap对象
     */
    private void init() {

        if (aMap == null) {
            aMap = mMapView.getMap();
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(false);

        }
    }

    public static  TripDetailFragment newInstance(String type){
        TripDetailFragment fragmentOne = new TripDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }
}
