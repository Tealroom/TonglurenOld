package com.wanlichangmeng.tonglurendesign.fragment;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.amap.api.maps.MapView;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.activity.CameraActivity;
import com.wanlichangmeng.tonglurendesign.activity.ListActivity;
import com.wanlichangmeng.tonglurendesign.activity.UpdatingActivity;
import com.wanlichangmeng.tonglurendesign.adapter.TabFragmentAdapter;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：发现
 */
public class CommunityFragment extends Fragment  implements View.OnClickListener {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.tab_viewpager)
    ViewPager tabViewpager;
    Unbinder unbinder;
    @BindView(R.id.iv_new_updating)
    ImageView mIvNewUpdating;
    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;

    private List<Fragment> mFragmentArrays = new ArrayList<>();
    private List<String> mTabs = new ArrayList<>();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //解决点击“我的”回来方法二，首页空白的问题，推荐的方法
        if (view != null) {
            unbinder = ButterKnife.bind(this, view);//必须加，不然报ButterKnife的异常
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }

        view = inflater.inflate(R.layout.fragment_community, container, false);

        unbinder = ButterKnife.bind(this, view);//这里也得有，不然报ButterKnife的异常

        initView(view);
        return view;
    }

    private void initView(View view) {
        mIvNewUpdating.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                bundle.putString("new_or_content","new");
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tablayout.removeAllTabs();
        tabViewpager.removeAllViews();
        if (mFragmentArrays != null) {
            mFragmentArrays.clear();
            mTabs.clear();
        }
        //替换成从服务器接口请求数据就成动态了
        mTabs.add("热门");
        mTabs.add("周边");
        mTabs.add("好友");

        //动态添加Fragment
        Fragment fragment1 = new TabFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("position", 0);
        fragment1.setArguments(bundle1);
        mFragmentArrays.add(fragment1);

        //动态添加Fragment
        Fragment fragment2 = new TabFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("position", 1);
        fragment2.setArguments(bundle2);
        mFragmentArrays.add(fragment2);

        //动态添加Fragment
        Fragment fragment3 = new TabFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("position", 2);
        fragment3.setArguments(bundle3);
        mFragmentArrays.add(fragment3);
        for (int i = 0; i < mTabs.size(); i++) {


        }

        tabViewpager.setAdapter(new TabFragmentAdapter(getFragmentManager(), mFragmentArrays, mTabs));


        tablayout.setupWithViewPager(tabViewpager);

    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_new_updating:
//                startPopuwindows(view);
//                break;
//        }
    }

    private void startPopuwindows(View view1) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.layout_main_popuwindows,null);
        RecyclerView recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
        //RecycleViewGridAdapter gridAdapter=new RecycleViewGridAdapter(R.layout.item_gride_fenlei,mTabs);
        //recyclerView.setAdapter(gridAdapter);

        final PopupWindow popupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.showAsDropDown(view1);

//        gridAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(getActivity(),"点击了"+mTabs.get(position),Toast.LENGTH_SHORT).show();
//                tabViewpager.setCurrentItem(position);
//                popupWindow.dismiss();
//            }
//        });
//        gridAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                tabViewpager.setCurrentItem(position);
//                popupWindow.dismiss();
//            }
//        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
