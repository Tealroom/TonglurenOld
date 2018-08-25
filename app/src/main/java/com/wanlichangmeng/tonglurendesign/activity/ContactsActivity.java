package com.wanlichangmeng.tonglurendesign.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.wanlichangmeng.indexlib.IndexBar.widget.IndexBar;
import com.wanlichangmeng.indexlib.suspension.SuspensionDecoration;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.adapter.ContactsAdapter;
import com.wanlichangmeng.tonglurendesign.data.Contacts;
import com.wanlichangmeng.tonglurendesign.decoration.DividerItemDecoration;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity {

    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;
    @BindView(R.id.activity_contacts_head_left)
    ImageView activity_contacts_head_left;
    @BindView(R.id.activity_contacts_head_right)
    ImageView activity_contacts_head_right;


    private static final String TAG = "zxt";
    private static final String INDEX_STRING_TOP = "↑";
    private RecyclerView mRv;
    private SwipeDelMenuAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<Contacts> mDatas = new ArrayList<>();

    private SuspensionDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
        ActivityUtils.transparencyBar(this,ll_bar4);

        //返回键监听
        activity_contacts_head_left.setOnClickListener(mGoBack);
        activity_contacts_head_right.setOnClickListener(mToSearch);

        mRv = (RecyclerView) findViewById(R.id.rv);

        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        mAdapter = new SwipeDelMenuAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(ContactsActivity.this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        //模拟线上加载数据
        initDatas(getResources().getStringArray(R.array.provinces));
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                //微信的头部 也是可以右侧IndexBar导航索引的，
                // 但是它不需要被ItemDecoration设一个标题titile
                mDatas.add((Contacts) new Contacts("新的朋友").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((Contacts) new Contacts("我的申请").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((Contacts) new Contacts("同路人助手").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                //mDatas.add((Contacts) new Contacts("公众号").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                for (int i = 0; i < data.length; i++) {
                    Contacts cityBean = new Contacts();
                    cityBean.setCity(data[i]);//设置城市名称
                    mDatas.add(cityBean);
                }
                mAdapter.setDatas(mDatas);
                mAdapter.notifyDataSetChanged();

                mIndexBar.setmSourceDatas(mDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mDatas);
            }
        }, 500);
    }

    /**
     * 更新数据源
     *
     * @param view
     */
    public void updateDatas(View view) {
        for (int i = 0; i < 5; i++) {
            mDatas.add(new Contacts("东京"));
            mDatas.add(new Contacts("大阪"));
        }

        mIndexBar.setmSourceDatas(mDatas)
                .invalidate();
        mAdapter.notifyDataSetChanged();
    }
    /**
     *
     * 返回键监听函数
     *
     *
     * */
    public View.OnClickListener mGoBack = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };
    /**
     *
     * 返回键监听函数
     *
     *
     * */
    public View.OnClickListener mToSearch = new View.OnClickListener() {
        int i = 0;
        Bundle bundle = new Bundle();
        public void onClick(View v) {
            bundle.putString("list_type","application_to_other");
            Intent intent = new Intent(ContactsActivity.this, SearchActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    /**
     * 和CityAdapter 一模一样，只是修改了 Item的布局
     * Created by zhangxutong .
     * Date: 16/08/28
     */

    private class SwipeDelMenuAdapter extends ContactsAdapter {

        public SwipeDelMenuAdapter(Context mContext, List<Contacts> mDatas) {
            super(mContext, mDatas);
        }

        @Override
        public SwipeDelMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.item_city_swipe, parent, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            holder.itemView.findViewById(R.id.btnDel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((SwipeMenuLayout) holder.itemView).quickClose();
                    mDatas.remove(holder.getAdapterPosition());
                    mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                            .setNeedRealIndex(true)//设置需要真实的索引
                            .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                            .setmSourceDatas(mDatas)//设置数据
                            .invalidate();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
