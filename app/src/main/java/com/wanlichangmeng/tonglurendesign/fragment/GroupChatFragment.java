package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.activity.ContactsActivity;
import com.wanlichangmeng.tonglurendesign.activity.SearchActivity;
import com.wanlichangmeng.tonglurendesign.activity.TripActivity;
import com.wanlichangmeng.tonglurendesign.adapter.ContactsAdapter;
import com.wanlichangmeng.tonglurendesign.adapter.GroupAdapter;
import com.wanlichangmeng.tonglurendesign.data.Contacts;
import com.wanlichangmeng.tonglurendesign.data.Group;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GroupChatFragment extends Fragment {

    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;
    @BindView(R.id.cl_head_left)
    ImageView activity_contacts_head_left;
    @BindView(R.id.cl_head_right)
    ImageView activity_contacts_head_right;

    @BindView(R.id.rv)
    RecyclerView mRv;

    private SwipeDelMenuAdapter1 mAdapter;
    private LinearLayoutManager mManager;
    private List<Group> mDatas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_group_chat, container, false);
        ButterKnife.bind(this,rootView);
        //返回键监听
        activity_contacts_head_left.setOnClickListener(mGoBack);
        activity_contacts_head_right.setOnClickListener(mToNew);

        mRv.setLayoutManager(mManager = new LinearLayoutManager(getActivity()));

        mAdapter = new SwipeDelMenuAdapter1(getActivity(), mDatas);
        mRv.setAdapter(mAdapter);

        mRv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        //模拟线上加载数据
        initDatas(getResources().getStringArray(R.array.provinces));
        return rootView;
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                //微信的头部 也是可以右侧IndexBar导航索引的，
                // 但是它不需要被ItemDecoration设一个标题titile
                mDatas.add((Group) new Group("我们去泰山"));
                mDatas.add((Group) new Group("神山一日游"));
                mDatas.add((Group) new Group("刷街大军"));

                mAdapter.setDatas(mDatas);
                mAdapter.notifyDataSetChanged();

            }
        }, 500);
    }


    /**
     *
     * 返回键监听函数
     *
     *
     * */
    public View.OnClickListener mGoBack = new View.OnClickListener() {
        public void onClick(View v) {
            getActivity().finish();
        }
    };
    /**
     *
     * 新建按钮监听
     *
     *
     * */
    public View.OnClickListener mToNew = new View.OnClickListener() {
        int i = 0;
        Bundle bundle = new Bundle();
        public void onClick(View v) {
            bundle.putString("list_type","application_to_other");
            Intent intent = new Intent(getActivity(), TripActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {

        ActivityUtils.initStateInFragment(this,ll_bar4);



        super.onResume();
    }

    public static  GroupChatFragment newInstance(String type){
        GroupChatFragment fragmentOne = new GroupChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }

    /**
     * 和CityAdapter 一模一样，只是修改了 Item的布局
     * Created by zhangxutong .
     * Date: 16/08/28
     */

    private class SwipeDelMenuAdapter1 extends GroupAdapter {

        public SwipeDelMenuAdapter1(Context mContext, List<Group> mDatas) {
            super(mContext, mDatas);
        }

        @Override
        public SwipeDelMenuAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.item_group, parent, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            holder.itemView.findViewById(R.id.btnDel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((SwipeMenuLayout) holder.itemView).quickClose();
                    mDatas.remove(holder.getAdapterPosition());

                    notifyDataSetChanged();
                }
            });
        }
    }

}
