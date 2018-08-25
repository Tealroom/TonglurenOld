package com.wanlichangmeng.tonglurendesign.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.wanlichangmeng.tonglurendesign.activity.ContactsActivity;
import com.wanlichangmeng.tonglurendesign.base.MyApplication;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.adapter.ApplicationAdapter;
//import com.wanlichangmeng.tonglurendesign.adapter.TabFragmentListAdapter;
import com.wanlichangmeng.tonglurendesign.data.Application;
import com.wanlichangmeng.tonglurendesign.decoration.DividerItemDecoration;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;


public class MyApplicationFragment extends Fragment {


    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;
    @BindView(R.id.cl_head_left)
    ImageView activity_contacts_head_left;
//    @BindView(R.id.cl_head_right)
//    ImageView activity_contacts_head_right;



    //动态列表
    @BindView(R.id.fragment_my_application_recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.fragment_my_application_swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;


    Unbinder unbinder;
    private List<String> mdata = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();
    int mPosition;



    private ApplicationAdapter adapter;
    private List<Application> data;
    private RecyclerView.OnScrollListener mOnScrollListener;

    private boolean isLoading;
    private String type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_application, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        if(getArguments()!=null){
            type = getArguments().getString("type");
        }
        initView();
        Log.e("进是进来了2:","1111111111111111111111111111111111111111");
        initData();
        return rootView;
    }
    private void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
//        mAdapter = new RecycleViewAdapter(R.layout.home_item_view, mdata);

//        View top = getLayoutInflater().inflate(R.layout.layout_banner, (ViewGroup) mRecyclerView.getParent(), false);
//        mBanner = top.findViewById(R.id.banner);
//        mAdapter.addHeaderView(top);
//        mRecyclerView.setAdapter(mAdapter);



        //动态列表
        data = new ArrayList<>();
        if(type.equals("myself")){
            adapter = new ApplicationAdapter(MyApplication.getInstance(), data,0);
        }else if(type.equals("others")){
            adapter = new ApplicationAdapter(MyApplication.getInstance(), data,1);
        }else{
            Log.e("myApplicationFragment","type wrong");
        }

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
                }
            }
        });

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getInstance());
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        mRecyclerView.setAdapter(adapter);
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
                if (lastVisibleItemPosition + 5 >= adapter.getItemCount()) {
                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        getData();
                    }
                }
            }
        };
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        if (!isLoading) {
            isLoading = true;
            getData();
        }

    }


    private void getData() {
        Application person = new Application("zhoucaizhi", "http://cdn.duitang.com/uploads/item/201507/08/20150708222949_nhxQk.thumb.224_0.png", true,"2018-8-12","山东济南欢迎您");
        for (int i = 0; i < 10; i++) {
            data.add(person);
            if (i == 9) {
                isLoading = false;
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                adapter.notifyItemRemoved(adapter.getItemCount());
                Log.e("vvv","===========total: "+data.size());
            }
        }
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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {

        ActivityUtils.initStateInFragment(this,ll_bar4);



        super.onResume();
    }
    public static  MyApplicationFragment newInstance(String type){
        MyApplicationFragment fragmentOne = new MyApplicationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
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
