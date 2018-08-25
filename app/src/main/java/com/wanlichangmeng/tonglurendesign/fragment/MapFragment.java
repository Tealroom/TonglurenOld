package com.wanlichangmeng.tonglurendesign.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.Poi;
import com.wanlichangmeng.tonglurendesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MapFragment extends Fragment implements AMap.OnPOIClickListener,
        AMap.OnMarkerClickListener {
    //获取地图控件引用
    @BindView(R.id.map_util)
    MapView mMapView;

    String destination;
    private UiSettings mUiSettings;
    private AMap aMap;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this,view);
        mMapView.onCreate(savedInstanceState);
        Init();
        return view;
    }

    private void Init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
            aMap.setOnPOIClickListener(this);
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(false);
           // addMarkersToMap();// 往地图上添加marker

        }
    }








    /**
     *  底图poi点击回调
     */
    @Override
    public void onPOIClick(Poi poi) {
        aMap.clear();
        //Log.i("MY", poi.getPoiId()+poi.getName());
        MarkerOptions markOptiopns = new MarkerOptions();
        markOptiopns.position(poi.getCoordinate());
        TextView textView = new TextView(getContext());
        textView.setText("到"+poi.getName()+"去");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundResource(R.drawable.button_orange);
        markOptiopns.icon(BitmapDescriptorFactory.fromView(textView));
        aMap.addMarker(markOptiopns);

        destination = poi.getName();

    }

    /**
     * Marker 点击回调
     * @param marker
     * @return
     */
    @Override
    public boolean onMarkerClick(Marker marker) {



        //这里应该存在数据库里面，然后弹出的时候在上一个fragment的resume里面刷新数据

        aMap.clear();
        return false;
    }




}
