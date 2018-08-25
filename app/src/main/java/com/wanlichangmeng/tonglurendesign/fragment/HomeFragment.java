package com.wanlichangmeng.tonglurendesign.fragment;



import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.ruffian.library.widget.REditText;
import com.ruffian.library.widget.RTextView;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.TravelOverview;
import com.wanlichangmeng.tonglurendesign.activity.ConditionSetActivity;
import com.wanlichangmeng.tonglurendesign.activity.MainActivity;
import com.wanlichangmeng.tonglurendesign.activity.PersonalActivity;
import com.wanlichangmeng.tonglurendesign.activity.SearchActivity;
import com.wanlichangmeng.tonglurendesign.activity.TripActivity;
import com.wanlichangmeng.tonglurendesign.activity.TripDetailActivity;
import com.wanlichangmeng.tonglurendesign.adapter.TabFragmentAdapter;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：首页
 */
public class HomeFragment extends Fragment implements AMap.InfoWindowAdapter,AMap.OnMarkerClickListener {
    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;
    //获取地图控件引用
    @BindView(R.id.map1)
    MapView mMapView;

    @BindView(R.id.fragment_home_btn_search)
    REditText btn_search;

    @BindView(R.id.fragment_home_btn_set)
    Button btn_set;

    @BindView(R.id.fragment_home_info_btn_retract)
    Button btn_retract;



    @BindView(R.id.fragment_home_info)
    LinearLayout info_window;


    @BindView(R.id.fragment_home_info_tripdetail)
    LinearLayout fragment_home_info_tripdetail;

    @BindView(R.id.fragment_home_info_avatar)
    ImageView infoAvatar;
    @BindView(R.id.fragment_home_info_username)
    TextView infoUsername;
    @BindView(R.id.fragment_home_info_title)
    TextView infoTitle;
    @BindView(R.id.fragment_home_info_travelMode)
    ImageView infoMode;
    @BindView(R.id.fragment_home_info_travelType)
    TextView infoType;
    @BindView(R.id.fragment_home_info_description)
    TextView infoDescription;


//    //获取地图控件引用
//    @BindView(R.id.main1)
//    LinearLayout relative;

    List<Marker> markerlst;
    List<TravelOverview> travelList = new ArrayList<TravelOverview>();
    List<Bitmap> avatarList = new ArrayList<Bitmap>();
    private UiSettings mUiSettings;
    private AMap aMap;

    //private LatLng latlng11 = new LatLng(34.341568, 108.940174);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
            aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(false);
            addMarkersToMap();// 往地图上添加marker

        }
//        Drawable drawable=getResources().getDrawable(R.drawable.zoom46);
//        drawable.setBounds(0,0,40,40);
//        mNextButton.setCompoundDrawables(,,drawable,);

        btn_search.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        //绑定点击事件监听（这里用的是匿名内部类创建监听）
        btn_retract.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            public void onClick(View v) {
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(),"你点击了"+(++i)+"次", Toast.LENGTH_LONG);//提示被点击了

                //toast.show();
                info_window.setVisibility(View.GONE);
            }
        });
        //绑定点击事件监听（这里用的是匿名内部类创建监听）
        btn_set.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            public void onClick(View v) {
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(),"你点击了"+(++i)+"次", Toast.LENGTH_LONG);//提示被点击了

                //toast.show();
                Intent intent = new Intent(getActivity(), ConditionSetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        //绑定点击事件监听（这里用的是匿名内部类创建监听）
        infoAvatar.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            public void onClick(View v) {
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(),"你点击了"+(++i)+"次", Toast.LENGTH_LONG);//提示被点击了

                //toast.show();
                Intent intent = new Intent(getActivity(), PersonalActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        fragment_home_info_tripdetail.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            public void onClick(View v) {
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(),"你点击了"+(++i)+"次", Toast.LENGTH_LONG);//提示被点击了

                //toast.show();
                Intent intent = new Intent(getActivity(), TripDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {


        initData();

        ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();



        for (int i=0;i<10;i++){

            markerOptionlst.add(new MarkerOptions()
                    .draggable(true));



            setGeniusIcon(travelList.get(i).getAvatar(),i);
            markerOptionlst.get(i).position(travelList.get(i).getPositon()).icon(BitmapDescriptorFactory.fromResource(R.drawable.user12));
            //Log.i("Window",markerOptionlst.get(i).getTitle());
        }
        markerlst = aMap.addMarkers(markerOptionlst, true);
        for (int i=0;i<markerlst.size();i++){

            Log.i("Window",markerlst.get(i).getId());
        }
    }
    public void setGeniusIcon(String url,int i) {
        Bitmap bitmap = null;
        RequestOptions options = new RequestOptions().circleCrop().override(100,100);

        if(i==0){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(0).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(0,resource);
                        }
                    }
            );

        }else if(i==1){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(1).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(1,resource);
                        }
                    }
            );

        }else if(i==2){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(2).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(2,resource);
                        }
                    }
            );

        }else if(i==3){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(3).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(3,resource);
                        }
                    }
            );

        }else if(i==4){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(4).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(4,resource);
                        }
                    }
            );

        }else if(i==5){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(5).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(5,resource);
                        }
                    }
            );

        }else if(i==6){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(6).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(6,resource);
                        }
                    }
            );

        }else if(i==7){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(7).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(7,resource);
                        }
                    }
            );

        }else if(i==8){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(8).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(8,resource);
                        }
                    }
            );

        }else if(i==9){
            Glide.with(this).asBitmap().apply(options).load(url).into(
                    new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            markerlst.get(9).setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                            avatarList.set(9,resource);
                        }
                    }
            );

        }


    }

    private void initData(){
        TravelOverview one1 = new TravelOverview();
        TravelOverview one2 = new TravelOverview();
        TravelOverview one3 = new TravelOverview();
        TravelOverview one4 = new TravelOverview();
        TravelOverview one5 = new TravelOverview();
        TravelOverview one6 = new TravelOverview();
        TravelOverview one7 = new TravelOverview();
        TravelOverview one8 = new TravelOverview();
        TravelOverview one9 = new TravelOverview();
        TravelOverview one10 = new TravelOverview();
        one1.setAvatar("http://img.besoo.com/file/201705/16/0940316445908.jpg");
        one2.setAvatar("http://img4.duitang.com/uploads/item/201511/26/20151126112617_vUaQf.jpeg");
        one3.setAvatar("http://tx.haiqq.com/uploads/allimg/160812/102GJ358-9.jpg");
        one4.setAvatar("http://img4.duitang.com/uploads/item/201504/10/20150410H1256_QAULP.jpeg");
        one5.setAvatar("http://img5.duitang.com/uploads/item/201506/07/20150607110911_kY5cP.jpeg");
        one6.setAvatar("http://imgsrc.baidu.com/forum/w=580/sign=1588b7c5d739b6004dce0fbfd9503526/7bec54e736d12f2eb97e1a464dc2d56285356898.jpg");
        one7.setAvatar("http://img3.duitang.com/uploads/item/201505/26/20150526002859_c2yKG.thumb.700_0.jpeg");
        one8.setAvatar("http://img3.duitang.com/uploads/item/201412/23/20141223203715_QrNTy.jpeg");
        one9.setAvatar("http://cdn2.image.apk.gfan.com/asdf/PImages/2014/7/9/ldpi_880509_9d49efc4-9763-48f3-a8f2-d6f40db6d957_icon.png");
        one9.setAvatar("http://img4.duitang.com/uploads/item/201602/07/20160207151120_zj5ES.jpeg");
        one1.setDescription("望着测验魔石碑上面闪亮得甚至有些刺眼的五个大字，少年面无表情，唇角有着一抹自嘲，紧握的手掌，因为大力，而导致略微尖锐的指甲深深的刺进了掌心之中，带来一阵阵钻心的疼痛…");
        one2.setDescription("萧炎，斗之力，三段！级别：低级！”测验魔石碑之旁，一位中年男子，看了一眼碑上所显示出来的信息，语气漠然的将之公布了出来…");
        one3.setDescription("少年缓缓抬起头来，露出一张有些清秀的稚嫩脸庞，漆黑的眸子木然的在周围那些嘲讽的同龄人身上扫过，少年嘴角的自嘲，似乎变得更加苦涩了。");
        one4.setDescription("萧炎，斗之力，三段！级别：低级！”测验魔石碑之旁，一位中年男子，看了一眼碑上所显示出来的信息，语气漠然的将之公布了出来…");
        one5.setDescription("萧炎，斗之力，三段！级别：低级！”测验魔石碑之旁，一位中年男子，看了一眼碑上所显示出来的信息，语气漠然的将之公布了出来…");
        one6.setDescription("萧炎，斗之力，三段！级别：低级！”测验魔石碑之旁，一位中年男子，看了一眼碑上所显示出来的信息，语气漠然的将之公布了出来…");
        one7.setDescription("萧炎，斗之力，三段！级别：低级！”测验魔石碑之旁，一位中年男子，看了一眼碑上所显示出来的信息，语气漠然的将之公布了出来…");
        one8.setDescription("萧炎，斗之力，三段！级别：低级！”测验魔石碑之旁，一位中年男子，看了一眼碑上所显示出来的信息，语气漠然的将之公布了出来…");
        one9.setDescription("萧炎，斗之力，三段！级别：低级！”测验魔石碑之旁，一位中年男子，看了一眼碑上所显示出来的信息，语气漠然的将之公布了出来…");
        one10.setDescription("萧炎，斗之力，三段！级别：低级！”测验魔石碑之旁，一位中年男子，看了一眼碑上所显示出来的信息，语气漠然的将之公布了出来…");
        one1.setPositon(new LatLng(39.91746, 117.397481));
        one2.setPositon(new LatLng(39.92746, 116.396481));
        one3.setPositon(new LatLng(39.88250, 116.409468));
        one4.setPositon(new LatLng(39.87814, 116.191765));
        one5.setPositon(new LatLng(39.73481, 116.307089));
        one6.setPositon(new LatLng(39.78416, 116.399999));
        one7.setPositon(new LatLng(40.00779, 116.304431));
        one8.setPositon(new LatLng(40.01384, 116.396730));
        one9.setPositon(new LatLng(39.98383, 116.497937));
        one10.setPositon(new LatLng(39.98746, 116.366481));
        one1.setTitle("suifdsja");
        one2.setTitle("gfdsgfdsgdfs");
        one3.setTitle("htrwgrewgerws");
        one4.setTitle("bvxcbdbxcv");
        one5.setTitle("trewtrewterwt");
        one6.setTitle("trewterqgfdsgfds");
        one7.setTitle("bvcxbgdfsgfdsg");
        one8.setTitle("hgfrsgreager");
        one9.setTitle("gftrwgreqfqe");
        one10.setTitle("bvcxbcvxbcvxb");
        one1.setUsername("suifdsja");
        one2.setUsername("gfdsgfdsgdfs");
        one3.setUsername("htrwgrewgerws");
        one4.setUsername("bvxcbdbxcv");
        one5.setUsername("trewtrewterwt");
        one6.setUsername("trewterqgfdsgfds");
        one7.setUsername("bvcxbgdfsgfdsg");
        one8.setUsername("hgfrsgreager");
        one9.setUsername("gftrwgreqfqe");
        one10.setUsername("bvcxbcvxbcvxb");
        one1.setTravelMode(1);
        one2.setTravelMode(2);
        one3.setTravelMode(3);
        one4.setTravelMode(1);
        one5.setTravelMode(2);
        one6.setTravelMode(3);
        one7.setTravelMode(1);
        one8.setTravelMode(2);
        one9.setTravelMode(3);
        one10.setTravelMode(1);
        one1.setTravelType(1);
        one2.setTravelType(2);
        one3.setTravelType(3);
        one4.setTravelType(1);
        one5.setTravelType(2);
        one6.setTravelType(3);
        one7.setTravelType(1);
        one8.setTravelType(2);
        one9.setTravelType(3);
        one10.setTravelType(1);

        travelList.add(one1);
        travelList.add(one2);
        travelList.add(one3);
        travelList.add(one4);
        travelList.add(one5);
        travelList.add(one6);
        travelList.add(one7);
        travelList.add(one8);
        travelList.add(one9);
        travelList.add(one10);
        Resources res = getResources();
        Bitmap    bmp = BitmapFactory.decodeResource(res, R.drawable.user22);
        for (int i=0;i<10;i++){
            avatarList.add(bmp);
        }





    }

    /**
     * 监听自定义infowindow窗口的infocontents事件回调,暂时没有用。没有调用过。这里主要是点击marker弹出的infowindow
     */
    @Override
    public View getInfoContents(Marker marker) {

        View infoContent = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);
        Log.i("Contents","11111111111111111111111111111111111");
        render(marker, infoContent);
        return infoContent;
    }

    /**
     * 监听自定义infowindow窗口的infowindow事件回调,暂时没有用。没有调用过。这里主要是点击marker弹出的infowindow
     */
    @Override
    public View getInfoWindow(Marker marker) {

        View infoWindow = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);
        Log.i("Window","2222222222222222222222222222222222");
        render(marker, infoWindow);
        return infoWindow;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {

    }

    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        info_window.setVisibility(View.VISIBLE);

        Integer io=marker.getId().length();

        int i =Integer.parseInt(marker.getId().substring(6))-1;


       // headDetail.setImageResource(imageId.get(Integer.parseInt(marker.getId().substring(6))-1));
        infoAvatar.setImageBitmap(avatarList.get(i));
        infoDescription.setText(travelList.get(i).getDescription());
        infoUsername.setText(travelList.get(i).getUsername());
        infoTitle.setText(travelList.get(i).getTitle());
        if(travelList.get(i).getTravelType()==1){
            infoType.setText("双人出行");
        }else if(travelList.get(i).getTravelType()==2){
            infoType.setText("小队出行");
        }


        if (aMap != null) {
//            ViewGroup.LayoutParams params=relative.getLayoutParams();
//            params.height =180;
//            relative.setLayoutParams(params);
//            Log.i("jinlaile",Integer.toString(params.height));
        }


        return false;
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
