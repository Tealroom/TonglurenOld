package com.wanlichangmeng.tonglurendesign.activity;

import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.data.Label;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;
import com.wanlichangmeng.tonglurendesign.utils.LabelUtils;
import com.wanlichangmeng.tonglurendesign.widget.ItemMedia;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewUpdatingActivity extends AppCompatActivity {
    @BindView(R.id.head2_ll_bar4)
    LinearLayout head2_ll_bar4;

    @BindView(R.id.media_list)
    FlexboxLayout mediaLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_updating);
        ButterKnife.bind(this);
        initMedia();
    }

    public void initMedia(){
        String[] tags = {"1","2"};

        for (int i = 0; i < tags.length; i++) {
            Log.e("捡来了","wocaocacoa");
            mediaLayout.addView(createNewFlexItem(tags[i]));
        }


    }
    /**这里的ItemMedia里面的layout必须要设定固定的width和height，不然就会显示不出来，默认为0！！！！！！！！！！！！！！！！！！！*/
    private ItemMedia createNewFlexItem(final String utl) {
        //本地文件
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "camera2.jpg");
        //加载图片
        ItemMedia imageView = new ItemMedia(this);
        imageView.setImageUrl(file,this);

//        int padding = LabelUtils.dpToPixel(this, 4);
//        int paddingLeftAndRight = LabelUtils.dpToPixel(this, 8);
//        ViewCompat.setPaddingRelative(imageView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
//        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        int margin = LabelUtils.dpToPixel(this, 6);
//        int marginTop = LabelUtils.dpToPixel(this, 16);
//        layoutParams.setMargins(margin, marginTop, margin, 0);
//        imageView.setLayoutParams(layoutParams);
        return imageView;
//        textView.setGravity(Gravity.CENTER);
//        textView.setText(label.getName());
//        textView.setTextSize(12);
//        textView.setTextColor(getResources().getColor(R.color.colorAccent));
//        textView.setBackgroundResource(R.drawable.button_orange);
//        textView.setTag(label.getId());
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("you has a click", label.getName());
//            }
//        });
//        int padding = LabelUtils.dpToPixel(this.getActivity(), 4);
//        int paddingLeftAndRight = LabelUtils.dpToPixel(this.getActivity(), 8);
//        ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
//        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        int margin = LabelUtils.dpToPixel(this.getActivity(), 6);
//        int marginTop = LabelUtils.dpToPixel(this.getActivity(), 16);
//        layoutParams.setMargins(margin, marginTop, margin, 0);
//        textView.setLayoutParams(layoutParams);
//        return textView;
    }

    @Override
    public void onResume() {

        ActivityUtils.initStateInActivity(this,head2_ll_bar4);


        super.onResume();
    }
}
