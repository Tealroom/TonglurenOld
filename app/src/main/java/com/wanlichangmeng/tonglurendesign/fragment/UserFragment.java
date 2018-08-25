package com.wanlichangmeng.tonglurendesign.fragment;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.activity.ChatActivity;
import com.wanlichangmeng.tonglurendesign.activity.ListActivity;
import com.wanlichangmeng.tonglurendesign.activity.SearchActivity;
import com.wanlichangmeng.tonglurendesign.data.Label;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;
import com.wanlichangmeng.tonglurendesign.utils.GlideImageLoader;
import com.wanlichangmeng.tonglurendesign.utils.LabelUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.youth.banner.Banner;

import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：我的
 */
public class UserFragment extends Fragment  implements OnBannerListener {

    @BindView(R.id.head1_ll_bar4)
    LinearLayout head1_ll_bar4;
    @BindView(R.id.head2_ll_bar4)
    LinearLayout head2_ll_bar4;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.fragment_user_label)
    FlexboxLayout labelLayout;

    @BindView(R.id.head1)
    View head1;
    @BindView(R.id.head2)
    View head2;
    @BindView(R.id.operation1)
    View operation1;
    @BindView(R.id.operation2)
    View operation2;


    @BindView(R.id.operation1_btn1)
    LinearLayout operation1_btn1;
    @BindView(R.id.operation1_btn2)
    LinearLayout operation1_btn2;
    @BindView(R.id.operation1_btn3)
    LinearLayout operation1_btn3;
    @BindView(R.id.operation1_btn4)
    LinearLayout operation1_btn4;


    @BindView(R.id.operation2_btn1)
    LinearLayout operation2_btn1;
    @BindView(R.id.operation2_btn2)
    LinearLayout operation2_btn2;
    @BindView(R.id.operation2_btn3)
    LinearLayout operation2_btn3;

    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    private String type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this,view);

        if(getArguments()!=null){
            type = getArguments().getString("type");
        }


        initListener();
        initView();
        Log.e("user_fragment_type",type);
        initBanner();
        initLabel();




        return view;
    }

    public void initListener(){

        operation1_btn1.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                bundle.putString("list_type","updating_myself");
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        operation1_btn2.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                bundle.putString("list_type","application_to_me");
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        operation1_btn3.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                bundle.putString("list_type","application_to_other");
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        operation1_btn4.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                bundle.putString("list_type","plan_myself");
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        operation2_btn1.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                bundle.putString("list_type","updating_others");
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        operation2_btn2.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                bundle.putString("list_type","plan_others");
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        operation2_btn3.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                //这里应该先判断两者是不是好友。实际到底传的是什么参数倒是无所谓。这里的参数应该和message里面的ChatItemAdapter一致
                bundle.putString("user","user1");
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    private void initView(){
        if(type=="others"){
            head1.setVisibility(View.GONE);
            head2.setVisibility(View.VISIBLE);
            operation1.setVisibility(View.GONE);
            operation2.setVisibility(View.VISIBLE);

        }
    }
    private void initBanner() {

        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();


    }
    public void initLabel(){
        String[] tags = {"婚姻育儿", "散文", "设计", "上班这点事儿", "影视天堂", "大学生活", "美人说", "运动和健身", "工具癖", "生活家", "程序员", "想法", "短篇小说", "美食", "教育", "心理", "奇思妙想", "美食", "摄影"};

        for (int i = 0; i < tags.length; i++) {
            Label model = new Label();
            model.setId(i);
            model.setName(tags[i]);
            labelLayout.addView(createNewFlexItemTextView(model));
        }


    }
    /**
     * 动态创建TextView
     * @param label
     * @return
     */
    private TextView createNewFlexItemTextView(final Label label) {
        TextView textView = new TextView(this.getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setText(label.getName());
        textView.setTextSize(12);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        textView.setBackgroundResource(R.drawable.button_orange);
        textView.setTag(label.getId());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("you has a click", label.getName());
            }
        });
        int padding = LabelUtils.dpToPixel(this.getActivity(), 4);
        int paddingLeftAndRight = LabelUtils.dpToPixel(this.getActivity(), 8);
        ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = LabelUtils.dpToPixel(this.getActivity(), 6);
        int marginTop = LabelUtils.dpToPixel(this.getActivity(), 16);
        layoutParams.setMargins(margin, marginTop, margin, 0);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第"+position+"张轮播图");
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {

        if(type=="others"){
            ActivityUtils.initStateInFragment(this,head2_ll_bar4);
            Log.e("show","22222222222222222");
        }else{
            ActivityUtils.initStateInFragment(this,head1_ll_bar4);
            Log.e("show","11111111111111111");
        }

//        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.colorPrimary);//设置系统状态栏颜色
//        tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.mybgcolor_02));//设置系统状态栏背景图
        super.onResume();
    }

    public static  UserFragment newInstance(String type){
        UserFragment fragmentOne = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }


}
