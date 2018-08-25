package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.activity.CameraActivity;
import com.wanlichangmeng.tonglurendesign.fragment.chat.MakePictrueFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MakeVedioFragment extends Fragment {
    @BindView(R.id.take)
    Button take;
    @BindView(R.id.delete)
    Button delete;

    private boolean mIsRecordingVideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_make_vedio, container, false);
        ButterKnife.bind(this,view);


        Init();
        return view;
    }


    public void Init(){
        take.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mIsRecordingVideo) {

                    ((CameraActivity)getActivity()).stopRecordingVideo();
                    mIsRecordingVideo = false;
                    take.setText("开始");
                } else {
                    mIsRecordingVideo = true;
                    ((CameraActivity)getActivity()).startRecordingVideo();
//                    ((CameraActivity) getActivity()).hiddenBottom();

                    take.setText("停止");
                }


            }
        });
        take.setText("开始");
        mIsRecordingVideo = false;
    }

    public void showDelete(){
        delete.setVisibility(View.VISIBLE);
    }

    public static MakeVedioFragment newInstance(String param1, String param2) {
        MakeVedioFragment fragment = new MakeVedioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

}
