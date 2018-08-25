package com.wanlichangmeng.tonglurendesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.data.Application;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplicationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private int type;
    private List<Application> data;
    //private ApplicationAdapter.OnItemClickListener onItemClickListener;
    public ApplicationAdapter(Context context, List<Application> data,int type) {
        this.context = context;
        this.type=type;
        if (data == null) {
            this.data = new ArrayList();
        } else {
            this.data = data;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_application, parent, false);
            return new ApplicationAdapter.ItemViewHolder1(view);
        }else if(viewType == 1){
            View view = LayoutInflater.from(context).inflate(R.layout.item_application_others, parent, false);
            return new ApplicationAdapter.ItemViewHolder2(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(type==0){
            if (holder instanceof ApplicationAdapter.ItemViewHolder1) {
                Glide.with(context)
                        .load(data.get(position).getAvatar()).apply(new RequestOptions().placeholder(R.color.colorAccent))

                        .into(((ApplicationAdapter.ItemViewHolder1) holder).application_profile);
//            Glide.with(context).load(data.get(position).getImage()).apply(new RequestOptions().placeholder(R.drawable.ic_launcher))
//                    .into(new SimpleTarget<Drawable>() {
//                        @Override
//                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                            ((TabFragmentListAdapter.ItemViewHolder) holder).fragment_updating_tab1_itm_2.setBackgroundDrawable(resource);
//                        }
//                    });
                ((ApplicationAdapter.ItemViewHolder1) holder).application_username.setText(data.get(position).getUsername());


            }


        }else if(type==1){
            if (holder instanceof ApplicationAdapter.ItemViewHolder2) {
                Glide.with(context)
                        .load(data.get(position).getAvatar()).apply(new RequestOptions().placeholder(R.color.colorAccent))

                        .into(((ApplicationAdapter.ItemViewHolder2) holder).application_profile);
//            Glide.with(context).load(data.get(position).getImage()).apply(new RequestOptions().placeholder(R.drawable.ic_launcher))
//                    .into(new SimpleTarget<Drawable>() {
//                        @Override
//                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                            ((TabFragmentListAdapter.ItemViewHolder) holder).fragment_updating_tab1_itm_2.setBackgroundDrawable(resource);
//                        }
//                    });
                ((ApplicationAdapter.ItemViewHolder2) holder).application_username.setText(data.get(position).getUsername());

                //((ApplicationAdapter.ItemViewHolder2) holder).application_status.setText("独一无二");
            }

        }

    }
    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(type==0){
            return 0;
        }else{
            return 1;
        }


    }

    static class ItemViewHolder1 extends RecyclerView.ViewHolder {

        @BindView(R.id.application_profile)
        protected ImageView application_profile;
//        @BindView(R.id.fragment_updating_tab1_itm_2)
//        protected ImageView fragment_updating_tab1_itm_2;
        @BindView(R.id.application_username)
        protected TextView application_username;

//        @BindView(R.id.application_time)
//        protected TextView application_time;


        @BindView(R.id.application_plan)
        protected TextView application_plan;
//        @BindView(R.id.application_relationship)
//        protected TextView application_relationship;




        public ItemViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    static class ItemViewHolder2 extends RecyclerView.ViewHolder {

        @BindView(R.id.application_profile)
        protected ImageView application_profile;
        //        @BindView(R.id.fragment_updating_tab1_itm_2)
//        protected ImageView fragment_updating_tab1_itm_2;
        @BindView(R.id.application_username)
        protected TextView application_username;

//        @BindView(R.id.application_time)
//        protected TextView application_time;


        @BindView(R.id.application_plan)
        protected TextView application_plan;
//        @BindView(R.id.application_relationship)
//        protected TextView application_relationship;



//        @BindView(R.id.application_status)
//        protected TextView application_status;
        public ItemViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
