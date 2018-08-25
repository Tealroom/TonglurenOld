package com.wanlichangmeng.tonglurendesign.fragment.chat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.activity.CameraActivity;
import com.wanlichangmeng.tonglurendesign.activity.ListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MakePictrueFragment extends Fragment {

    @BindView(R.id.take)
    Button take;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_make_pictrue, container, false);
        ButterKnife.bind(this,view);


        Init();
        return view;
    }


    public void Init(){
        take.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((CameraActivity)getActivity()).takePicture();
            }
        });
    }


    public static MakePictrueFragment newInstance(String param1, String param2) {
        MakePictrueFragment fragment = new MakePictrueFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

}
