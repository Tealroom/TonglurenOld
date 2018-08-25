package com.wanlichangmeng.tonglurendesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.wanlichangmeng.tonglurendesign.data.Contacts;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{
    protected Context mContext;
    protected List<Contacts> mDatas;
    protected LayoutInflater mInflater;

    public ContactsAdapter(Context mContext, List<Contacts> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<Contacts> getDatas() {
        return mDatas;
    }

    public ContactsAdapter setDatas(List<Contacts> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final ContactsAdapter.ViewHolder holder, final int position) {
        final Contacts cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.avatar.setImageResource(R.drawable.friend);
        if(position<2){
            holder.content.setOnClickListener(new View.OnClickListener() {
                Bundle bundle = new Bundle();
                public void onClick(View v) {
                    if(position==0){
                        bundle.putString("list_type","application_to_me");
                    }else if(position==1){
                        bundle.putString("list_type","application_to_other");
                    }

                    Intent intent = new Intent(mContext, ListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }else if(position==2){
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
        else{

            holder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "pos:" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        ImageView avatar;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            content = itemView.findViewById(R.id.content);
        }
    }

}
