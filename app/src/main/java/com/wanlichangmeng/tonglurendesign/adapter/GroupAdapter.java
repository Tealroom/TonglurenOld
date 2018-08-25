package com.wanlichangmeng.tonglurendesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.activity.ChatActivity;
import com.wanlichangmeng.tonglurendesign.activity.ListActivity;
import com.wanlichangmeng.tonglurendesign.data.Group;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{
    protected Context mContext;
    protected List<Group> mDatas;
    protected LayoutInflater mInflater;

    public GroupAdapter(Context mContext, List<Group> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<Group> getDatas() {
        return mDatas;
    }

    public GroupAdapter setDatas(List<Group> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupAdapter.ViewHolder(mInflater.inflate(R.layout.item_group, parent, false));
    }

    @Override
    public void onBindViewHolder(final GroupAdapter.ViewHolder holder, final int position) {
        final Group groupBean = mDatas.get(position);
        holder.tvCity.setText(groupBean.getGroupName());
        holder.avatar.setImageResource(R.drawable.friend);


        holder.content.setOnClickListener(new View.OnClickListener() {
            Bundle bundle = new Bundle();
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        ImageView avatar;
        ConstraintLayout content;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvGroupName);
            avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            content = (ConstraintLayout)itemView.findViewById(R.id.content);
        }
    }

}
