package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Context;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;




import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.utils.PhotoUtils;
import com.wanlichangmeng.tonglurendesign.utils.ToastUtils;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;


public class NewUpdatingFragment extends Fragment {

    private static final String TAG = "PhotoImageFragment";



    Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_updating, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        //autoObtainCameraPermission();

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static  NewUpdatingFragment newInstance(String whatever){
        NewUpdatingFragment fragmentOne = new NewUpdatingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", whatever);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }


}
