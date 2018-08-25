package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.adapter.UpdatingAdapter;
import com.wanlichangmeng.tonglurendesign.data.SampleCoverVideo;
import com.wanlichangmeng.tonglurendesign.data.Updating;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class UpdatingContentFragment extends Fragment {

    public static final String TAG = "ListNormalAdapter22";

    @BindView(R.id.profile)
    protected ImageView profile;
    @BindView(R.id.rv)
    protected RecyclerView mRv;
    @BindView(R.id.vv)
    protected SampleCoverVideo mVv;
    @BindView(R.id.head3_ll_bar4)
    LinearLayout ll_bar4;

    Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_updating_content, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        Bundle bundle = getActivity().getIntent().getExtras();
        Updating updating = (Updating)bundle.getSerializable("data");

        InitView(updating);

        return rootView;
    }

    private void InitView(Updating data){
        Glide.with(getActivity())
                .load(data.getAvatar()).apply(new RequestOptions().placeholder(R.color.colorAccent)).into(profile);
        if(data.isVideo()){
            mRv.setVisibility(View.GONE);
            mVv.setVisibility(View.VISIBLE);

            String url =  data.getVideo() ;
            mVv.loadCoverImage(url, R.mipmap.xxx1);

            mVv.setUpLazy(url, true, null, null, "这是title");
            //增加title
            mVv.getTitleTextView().setVisibility(View.GONE);
            //设置返回键
            mVv.getBackButton().setVisibility(View.GONE);
            //设置全屏按键功能
            mVv.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVv.startWindowFullscreen(getActivity(), false, true);
                }
            });
            //防止错位设置
            mVv.setPlayTag(TAG);
            mVv.setPlayPosition(12);
            //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
            mVv.setAutoFullWithSize(true);
            //音频焦点冲突时是否释放
            mVv.setReleaseWhenLossAudio(false);
            //全屏动画
            mVv.setShowFullAnimation(true);
            //小屏时不触摸滑动
            mVv.setIsTouchWiget(false);
            //全屏是否需要lock功能
        }else{

            mRv.setVisibility(View.VISIBLE);
            mVv.setVisibility(View.GONE);

            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());
            layoutManager.setFlexWrap(FlexWrap.WRAP);
            layoutManager.setFlexDirection(FlexDirection.ROW);
            layoutManager.setAlignItems(AlignItems.STRETCH);
            layoutManager.setJustifyContent(JustifyContent.FLEX_START);
            mRv.setLayoutManager(layoutManager);



            mRv.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.item_flexbox, data.getImage())
            {
                @Override
                protected void convert(ViewHolder holder, String s, int position1) {


                    //Log.e("wokao1","lailemeiyou");
                    RequestOptions options = new RequestOptions().override(320,450).placeholder(R.drawable.ic_panorama_black_48dp);
                    ImageView mImageView = (ImageView)holder.getView(R.id.imageview);

                    Glide.with(getActivity())
                            .load(s).apply(options).into(mImageView);
                }
            });

        }
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
        super.onResume();
    }

    public static  UpdatingContentFragment newInstance(Updating whatever){
        UpdatingContentFragment fragmentOne = new UpdatingContentFragment();
        Bundle bundle = new Bundle();
        //bundle.putString("data", whatever);
        bundle.putSerializable("data",whatever);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }
}
