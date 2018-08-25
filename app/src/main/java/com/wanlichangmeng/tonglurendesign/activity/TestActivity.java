package com.wanlichangmeng.tonglurendesign.activity;

import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayout;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.utils.LabelUtils;
import com.wanlichangmeng.tonglurendesign.widget.ItemMedia;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.media_list)
    FlexboxLayout mediaLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        String[] tags = {"1","2"};

        for (int i = 0; i < tags.length; i++) {
            Log.e("捡来了","wocaocacoa");
            mediaLayout.addView(createNewFlexItem(tags[i]));
        }
        ImageView test = new ImageView(this);
        test.setImageDrawable(getResources().getDrawable(R.drawable.emotion_aini));
        mediaLayout.addView(test);
    }

    private ItemMedia createNewFlexItem(final String utl) {
        //本地文件
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "camera2.jpg");
        //加载图片
        ItemMedia imageView = new ItemMedia(this);
        ImageView hehe = imageView.getMain();
        hehe.setBackgroundColor(android.graphics.Color.BLUE);
        imageView.setImageUrl(file,this);


        return imageView;

    }
}
