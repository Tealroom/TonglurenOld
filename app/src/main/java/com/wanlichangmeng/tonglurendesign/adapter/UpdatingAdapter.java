package com.wanlichangmeng.tonglurendesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.activity.UpdatingActivity;
import com.wanlichangmeng.tonglurendesign.data.SampleCoverVideo;
import com.wanlichangmeng.tonglurendesign.data.Updating;
import com.wanlichangmeng.tonglurendesign.utils.GlideImageLoader;
import com.wanlichangmeng.tonglurendesign.utils.UIUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;




import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
//import io.vov.vitamio.MediaPlayer;
//import io.vov.vitamio.widget.MediaController;
//import io.vov.vitamio.widget.VideoView;


public class UpdatingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnBannerListener {

    public static final String TAG = "ListNormalAdapter22";


    private static final int TYPE_HEAD = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<Updating> data;

    private UpdatingAdapter.OnItemClickListener onItemClickListener;

    public UpdatingAdapter(Context context, List<Updating> data) {
        this.context = context;
        if (data == null) {
            this.data = new ArrayList();
        } else {
            this.data = data;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_updating_tab_recycler_head, parent, false);
            return new UpdatingAdapter.HeadViewHolder(view);
        } else if (viewType == TYPE_ITEM) {
            //View view = LayoutInflater.from(context).inflate(R.layout.item_updating_other, parent, false);
            View view = LayoutInflater.from(context).inflate(R.layout.item_updating_image, parent, false);
            return new UpdatingAdapter.ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_updating_tab_recycler_foot, parent, false);
            return new UpdatingAdapter.FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
//
        if (holder instanceof UpdatingAdapter.ItemViewHolder) {
            Glide.with(context)
                    .load(data.get(position).getAvatar()).apply(new RequestOptions().placeholder(R.color.colorAccent))

                    .into(((ItemViewHolder) holder).profile);


            if(data.get(position).isVideo()){
                ((ItemViewHolder) holder).mRv.setVisibility(View.GONE);
                ((ItemViewHolder) holder).mVv.setVisibility(View.VISIBLE);

                String url =  data.get(position).getVideo() ;
                ((ItemViewHolder) holder).mVv.loadCoverImage(url, R.mipmap.xxx1);

                ((ItemViewHolder) holder).mVv.setUpLazy(url, true, null, null, "这是title");
                //增加title
                ((ItemViewHolder) holder).mVv.getTitleTextView().setVisibility(View.GONE);
                //设置返回键
                ((ItemViewHolder) holder).mVv.getBackButton().setVisibility(View.GONE);
                //设置全屏按键功能
                ((ItemViewHolder) holder).mVv.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((ItemViewHolder) holder).mVv.startWindowFullscreen(context, false, true);
                    }
                });
                //防止错位设置
                ((ItemViewHolder) holder).mVv.setPlayTag(TAG);
                ((ItemViewHolder) holder).mVv.setPlayPosition(position);
                //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
                ((ItemViewHolder) holder).mVv.setAutoFullWithSize(true);
                //音频焦点冲突时是否释放
                ((ItemViewHolder) holder).mVv.setReleaseWhenLossAudio(false);
                //全屏动画
                ((ItemViewHolder) holder).mVv.setShowFullAnimation(true);
                //小屏时不触摸滑动
                ((ItemViewHolder) holder).mVv.setIsTouchWiget(false);
                //全屏是否需要lock功能

            }
            else{
                ((ItemViewHolder) holder).mRv.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).mVv.setVisibility(View.GONE);

                FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(context);
                layoutManager.setFlexWrap(FlexWrap.WRAP);
                layoutManager.setFlexDirection(FlexDirection.ROW);
                layoutManager.setAlignItems(AlignItems.STRETCH);
                layoutManager.setJustifyContent(JustifyContent.FLEX_START);
                ((ItemViewHolder) holder).mRv.setLayoutManager(layoutManager);



                ((ItemViewHolder) holder).mRv.setAdapter(new CommonAdapter<String>(context, R.layout.item_flexbox, data.get(position).getImage())
                {
                    @Override
                    protected void convert(ViewHolder holder, String s, int position1) {


                        //Log.e("wokao1","lailemeiyou");
                        RequestOptions options = new RequestOptions().override(300,500).placeholder(R.drawable.ic_panorama_black_48dp);
                        ImageView mImageView = (ImageView)holder.getView(R.id.imageview);

                        Glide.with(context)
                                .load(s).apply(options).into(mImageView);
                    }
                });
            }

            ((ItemViewHolder) holder).updating_cl4.setOnClickListener(new View.OnClickListener(){
                int i = 0;
                Bundle bundle = new Bundle();
                public void onClick(View v) {
                    bundle.putString("new_or_content","content");
                    bundle.putSerializable("data",data.get(position));
                    Intent intent = new Intent(context, UpdatingActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });









//            Glide.with(context).load(data.get(position).getImage()).apply(new RequestOptions().placeholder(R.drawable.ic_launcher))
//                    .into(new SimpleTarget<Drawable>() {
//                        @Override
//                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                            ((UpdatingAdapter.ItemViewHolder) holder).fragment_updating_tab1_itm_3.setBackgroundDrawable(resource);
//                        }
//                    });
//            ((UpdatingAdapter.ItemViewHolder) holder).username.setText(data.get(position).getUsername());
//            ((ItemViewHolder) holder).fragment_updating_tab1_itm_3.setText("我就是那条动态你服不服？");
//            ((ItemViewHolder) holder).fragment_updating_tab1_itm_3.setOnClickListener(new View.OnClickListener(){
//                int i = 0;
//                Bundle bundle = new Bundle();
//                public void onClick(View v) {
//                    bundle.putString("new_or_content","content");
//                    Intent intent = new Intent(context, UpdatingActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                }
//            });

        }
        else if (holder instanceof UpdatingAdapter.HeadViewHolder) {

            //放图片地址的集合
            ArrayList<String> list_path = new ArrayList<>();
            //放标题的集合
            ArrayList<String> list_title = new ArrayList<>();

            list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
            list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
            list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
            list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
            list_title.add("好好学习");
            list_title.add("天天向上");
            list_title.add("热爱劳动");
            list_title.add("不搞对象");
            //设置内置样式，共有六种可以点入方法内逐一体验使用。
            ((HeadViewHolder) holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片加载器，图片加载器在下方
            ((HeadViewHolder) holder).banner.setImageLoader(new GlideImageLoader());
            //设置图片网址或地址的集合
            ((HeadViewHolder) holder).banner.setImages(list_path);
            //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
            ((HeadViewHolder) holder).banner.setBannerAnimation(Transformer.Default);
            //设置轮播图的标题集合
            ((HeadViewHolder) holder).banner.setBannerTitles(list_title);
            //设置轮播间隔时间
            ((HeadViewHolder) holder).banner.setDelayTime(3000);
            //设置是否为自动轮播，默认是“是”。
            ((HeadViewHolder) holder).banner.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            ((HeadViewHolder) holder).banner.setIndicatorGravity(BannerConfig.CENTER)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                    .setOnBannerListener(this)
                    //必须最后调用的方法，启动轮播图。
                    .start();
        }
    }








    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第"+position+"张轮播图");
    }




    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setOnItemClickListener(UpdatingAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        Banner banner;


        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {


//
//        @BindView(R.id.avatar)
//        protected ImageView avatar;
//        @BindView(R.id.fragment_updating_tab1_itm_2)
//        protected ImageView fragment_updating_tab1_itm_2;
//        @BindView(R.id.fragment_updating_tab1_itm_3)
//        protected TextView fragment_updating_tab1_itm_3;
//        @BindView(R.id.username)
//        protected TextView username;
        @BindView(R.id.profile)
        protected ImageView profile;
        @BindView(R.id.rv)
        protected RecyclerView mRv;
        @BindView(R.id.vv)
        protected SampleCoverVideo mVv;
        @BindView(R.id.updating_cl2)
        protected ConstraintLayout updating_cl2;
        @BindView(R.id.updating_cl4)
        protected ConstraintLayout updating_cl4;
//        @BindView(R.id.fragment_updating_tab1_itm_3)
//        protected TextView fragment_updating_tab1_itm_3;
//        @BindView(R.id.username)
//        protected TextView username;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
