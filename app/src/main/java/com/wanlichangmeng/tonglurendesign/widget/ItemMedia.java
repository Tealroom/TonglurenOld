package com.wanlichangmeng.tonglurendesign.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wanlichangmeng.tonglurendesign.R;


import java.io.File;

import butterknife.BindView;

public class ItemMedia extends ConstraintLayout {
    @BindView(R.id.main_image)
    ImageView main_image;
    @BindView(R.id.delete_image)
    ImageView delete_image;
    public ItemMedia(final Context context) {
        this(context, null);
        View view = LayoutInflater.from(context).inflate(R.layout.item_media,this);
        main_image= (ImageView) view.findViewById(R.id.main_image);
        delete_image= (ImageView) view.findViewById(R.id.delete_image);
        init();
    }

    public ItemMedia(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.item_media,this);
        main_image= (ImageView) view.findViewById(R.id.main_image);
        delete_image= (ImageView) view.findViewById(R.id.delete_image);
        init();
    }

    public ItemMedia(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view =LayoutInflater.from(context).inflate(R.layout.item_media,this);
        main_image= (ImageView) view.findViewById(R.id.main_image);
        delete_image= (ImageView) view.findViewById(R.id.delete_image);
        init();
    }

    public void init(){
        main_image.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {

                Log.e("我擦","jinlaile ");
            }
        });
        delete_image.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {

                Log.e("我擦","haihaola ");
            }
        });
    }
    public ImageView getMain(){
        return main_image;
    }
    public void setImageUrl(File file,Context context){
        //本地文件
        RequestOptions options = new RequestOptions().override(320,450).placeholder(R.drawable.ic_panorama_black_48dp);
        //加载图片
        Log.e("我来了一次","hehe ");
        Glide.with(context).load("http://img15.3lian.com/2015/f2/8/d/96.jpg").apply(options).into(main_image);

    }
}
