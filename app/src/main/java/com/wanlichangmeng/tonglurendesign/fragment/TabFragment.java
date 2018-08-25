package com.wanlichangmeng.tonglurendesign.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wanlichangmeng.tonglurendesign.base.MyApplication;
import com.wanlichangmeng.tonglurendesign.R;
//import com.wanlichangmeng.tonglurendesign.adapter.TabFragmentListAdapter;
import com.wanlichangmeng.tonglurendesign.adapter.UpdatingAdapter;
import com.wanlichangmeng.tonglurendesign.data.Updating;
import com.wanlichangmeng.tonglurendesign.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
//import com.youth.banner.Banner;
//import com.youth.banner.listener.OnBannerListener;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class TabFragment extends Fragment {


    Unbinder unbinder;
    private List<String> mdata = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();
    int mPosition;
//    private RecycleViewAdapter mAdapter;
//    private Banner mBanner;




    //动态列表
    @BindView(R.id.discover_recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    private UpdatingAdapter adapter;
    private List<Updating> data;
    private RecyclerView.OnScrollListener mOnScrollListener;

    private boolean isLoading;


    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmeny_tab, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mPosition = getArguments().getInt("position");
        initView();
        //initData();

        return rootView;
    }


    private void initView() {

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(layoutManager);




        //动态列表
        data = new ArrayList<>();
        adapter = new UpdatingAdapter(getActivity(), data);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlueStatus);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    data.clear();
                    Log.e("vvv", "was clear"+data.size());
                    getData();
                    swipeRefreshLayout.setRefreshing(false);
                    isLoading = false;
                }
            }
        });

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getInstance());
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        mRecyclerView.setAdapter(adapter);
        //这里是比较重要的刷新和获取地址，这里这个流畅度很烂。应该首先向好友列表学习
        mOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case SCROLL_STATE_IDLE:
                        Glide.with(MyApplication.getInstance()).resumeRequests();
                        break;
                    case SCROLL_STATE_DRAGGING:
                    case SCROLL_STATE_SETTLING:
                        Glide.with(MyApplication.getInstance()).pauseRequests();
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                //Log.e("flash",""+lastVisibleItemPosition);
                //Log.e("all",""+adapter.getItemCount());
                if (lastVisibleItemPosition +1 >= adapter.getItemCount()) {
                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {//这里应该重新加载数据
                        //adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        getData1();
                    }
                }
            }
        };
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        if (!isLoading) {
            isLoading = true;
            getData();
        }

    }

//    private void initBanner() {
//
//        //放图片地址的集合
//        list_path = new ArrayList<>();
//        //放标题的集合
//        list_title = new ArrayList<>();
//
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
//        list_title.add("好好学习");
//        list_title.add("天天向上");
//        list_title.add("热爱劳动");
//        list_title.add("不搞对象");
//        //设置内置样式，共有六种可以点入方法内逐一体验使用。
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//        //设置图片加载器，图片加载器在下方
//        banner.setImageLoader(new GlideImageLoader());
//        //设置图片网址或地址的集合
//        banner.setImages(list_path);
//        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
//        banner.setBannerAnimation(Transformer.Default);
//        //设置轮播图的标题集合
//        banner.setBannerTitles(list_title);
//        //设置轮播间隔时间
//        banner.setDelayTime(3000);
//        //设置是否为自动轮播，默认是“是”。
//        banner.isAutoPlay(true);
//        //设置指示器的位置，小点点，左中右。
//        banner.setIndicatorGravity(BannerConfig.CENTER)
//                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
//                .setOnBannerListener(this)
//                //必须最后调用的方法，启动轮播图。
//                .start();
//
//
//    }
//
//    //轮播图的监听方法
//    @Override
//    public void OnBannerClick(int position) {
//        Log.i("tag", "你点了第"+position+"张轮播图");
//    }

    private void getData() {
        List<String> image = new ArrayList<>();
        image.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531483963749&di=8dce04ff0d39c93e8c604fb828a38e7f&imgtype=0&src=http%3A%2F%2Fimgm.gmw.cn%2Fattachement%2Fjpg%2Fsite2%2F20160322%2Fb8ac6f402e5f185b1c3c55.jpg");
        image.add("https://b-ssl.duitang.com/uploads/item/201312/05/20131205172445_4cznX.thumb.700_0.jpeg");
        image.add("https://b-ssl.duitang.com/uploads/item/201312/05/20131205171922_dVBte.thumb.700_0.jpeg");
        Updating person = new Updating("riv", "http://cdn.duitang.com/uploads/item/201507/08/20150708222949_nhxQk.thumb.224_0.png",false);
        data.add(person);
        person.setImage(image);
        data.add(person);
        String video = "http://120.78.190.184/static/1.mp4";
        Updating person1 = new Updating("woshi另外的", "https://b-ssl.duitang.com/uploads/item/201312/05/20131205171922_dVBte.thumb.700_0.jpeg", true);
        person1.setVideo(video);
        data.add(person);
        isLoading = false;
        adapter.notifyDataSetChanged();

    }

    private void getData1() {
        List<String> image = new ArrayList<>();
        image.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531483963749&di=8dce04ff0d39c93e8c604fb828a38e7f&imgtype=0&src=http%3A%2F%2Fimgm.gmw.cn%2Fattachement%2Fjpg%2Fsite2%2F20160322%2Fb8ac6f402e5f185b1c3c55.jpg");
        image.add("https://b-ssl.duitang.com/uploads/item/201312/05/20131205172445_4cznX.thumb.700_0.jpeg");
        image.add("https://b-ssl.duitang.com/uploads/item/201312/05/20131205171922_dVBte.thumb.700_0.jpeg");
        Updating person = new Updating("riv", "http://cdn.duitang.com/uploads/item/201507/08/20150708222949_nhxQk.thumb.224_0.png",false);
        data.add(person);
        person.setImage(image);
        data.add(person);
        String video = "http://120.78.190.184/static/test.mp4";
        Updating person1 = new Updating("woshi另外的", "https://b-ssl.duitang.com/uploads/item/201312/05/20131205171922_dVBte.thumb.700_0.jpeg", true);
        person1.setVideo(video);
        data.add(person);
        isLoading = false;
        adapter.notifyDataSetChanged();

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mdata.add("TabFragment_" + mPosition + "第" + i + "条数据");
        }
//        mAdapter.setNewData(mdata);//模拟网络请求成功后要调用这个方法刷新数据
        if (mPosition == 0) {
            imageUrl.clear();
            imageUrl.add("http://mpic.tiankong.com/aa4/fd8/aa4fd84a633298f43fe4521ba9a2dcbc/640.jpg");
            imageUrl.add("http://mpic.tiankong.com/34d/ee2/34dee24f36c176651e0b64dbc8f5d170/640.jpg");
            imageUrl.add("http://mpic.tiankong.com/5a4/a2d/5a4a2dc36ad6d42ba95ee8c2afd8e038/640.jpg");
            initBanner(imageUrl);
        } else {
//            mBanner.setVisibility(View.GONE);
        }

    }

    private void initBanner(List<String> imageUrl) {
//        mBanner.setImages(imageUrl)
//                .setImageLoader(new GlideImageLoader())
//                .setDelayTime(3000)
//                .start();
//        mBanner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//
//            }
//        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        //mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        //mBanner.stopAutoPlay();
    }
}
