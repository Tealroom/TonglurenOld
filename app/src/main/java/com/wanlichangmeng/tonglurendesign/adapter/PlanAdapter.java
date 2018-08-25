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
import com.wanlichangmeng.tonglurendesign.data.Plan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Plan> data;
    //private ApplicationAdapter.OnItemClickListener onItemClickListener;
    public PlanAdapter(Context context, List<Plan> data) {
        this.context = context;
        if (data == null) {
            this.data = new ArrayList();
        } else {
            this.data = data;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_list_plan, parent, false);
            return new PlanAdapter.ItemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PlanAdapter.ItemViewHolder) {
            Glide.with(context)
                    .load(data.get(position).getFirstImage()).apply(new RequestOptions().placeholder(R.color.colorAccent))

                    .into(((ItemViewHolder) holder).plan_list_firstImage);
//            Glide.with(context).load(data.get(position).getImage()).apply(new RequestOptions().placeholder(R.drawable.ic_launcher))
//                    .into(new SimpleTarget<Drawable>() {
//                        @Override
//                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                            ((TabFragmentListAdapter.ItemViewHolder) holder).fragment_updating_tab1_itm_2.setBackgroundDrawable(resource);
//                        }
//                    });
            ((ItemViewHolder) holder).plan_list_title.setText(data.get(position).getTitle());
        }
    }
    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.plan_list_firstImage)
        protected ImageView plan_list_firstImage;
        //        @BindView(R.id.fragment_updating_tab1_itm_2)
//        protected ImageView fragment_updating_tab1_itm_2;
        @BindView(R.id.plan_list_title)
        protected TextView plan_list_title;

        @BindView(R.id.plan_list_time)
        protected TextView plan_list_time;


        @BindView(R.id.plan_list_type)
        protected TextView plan_list_type;

        @BindView(R.id.plan_list_status)
        protected TextView plan_list_status;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
