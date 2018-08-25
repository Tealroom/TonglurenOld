package com.wanlichangmeng.tonglurendesign.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.adapter.ViewPagerAdapter;
import com.wanlichangmeng.tonglurendesign.fragment.MakeVedioFragment;
import com.wanlichangmeng.tonglurendesign.fragment.MessageFragment;
import com.wanlichangmeng.tonglurendesign.fragment.UserFragment;
import com.wanlichangmeng.tonglurendesign.fragment.chat.MakePictrueFragment;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;
import com.wanlichangmeng.tonglurendesign.utils.BitmapUtil;
import com.wanlichangmeng.tonglurendesign.utils.Camera2Helper;
import com.wanlichangmeng.tonglurendesign.utils.ViewPagerHelper;
import com.wanlichangmeng.tonglurendesign.widget.camera.AutoFitTextureView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraActivity extends FragmentActivity implements Camera2Helper.AfterDoListener{

    //底部菜单栏控件
//    @BindView(R.id.navigation)
//    BottomNavigationView navigation;
    @BindView(R.id.head2_ll_bar4)
    LinearLayout head2_ll_bar4;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.camera_main)
    LinearLayout camera_main;
    //底部菜单选项对应的适配器
    @BindView(R.id.viewpager)
    ViewPagerHelper viewpager;

    @BindView(R.id.fragment_back_button)
    ImageView fragment_back_button;

    @BindView(R.id.cancel)
    ImageView cancel;
    @BindView(R.id.confirm)
    ImageView confirm;
    @BindView(R.id.videoConfirm)
    TextView videoConfirm;

    @BindView(R.id.texture)
    AutoFitTextureView textureView;




    private MakePictrueFragment takePictrue;
    private MakeVedioFragment takeVideo;
    private MenuItem menuItem;





    private Camera2Helper camera2Helper;
    private File file;

    public static final String PHOTO_PATH = Environment.getExternalStorageDirectory().getPath();
    public static final String PHOTO_NAME = "camera2";

    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_AUDIO = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);


        initView();
    }


    private void initView() {

        takeVideo = new MakeVedioFragment();
        takePictrue = new MakePictrueFragment();
        //ActivityUtils.transparencyBar(this);

        fragment_back_button.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                viewpager.setCurrentItem(2);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {

                bundle.putString("list_type","application_to_other");
                Intent intent = new Intent(CameraActivity.this, NewUpdatingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
                //这个新的至于是当前activity里面的fragment还是一个新的activity就等录制完成再说
            }
        });
        cancel.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                camera2Helper.unlockFocus();
                confirm.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
            }
        });
        videoConfirm.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {

                bundle.putString("list_type","application_to_other");
                Intent intent = new Intent(CameraActivity.this, NewUpdatingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        //BottomNavigationViewHelper.disableShiftMode(navigation);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //adapter.addFragment(new TakeFragment());
        adapter.addFragment(takePictrue);
        //adapter.addFragment(takeVideo);


        adapter.addFragment(takeVideo);
        //adapter.addFragment(UserFragment.newInstance("myself"));
        adapter.addFragment(UserFragment.newInstance("myself"));
        viewpager.setAdapter(adapter);
        viewpager.setScanScroll(false);
        viewpager.setOffscreenPageLimit(2);//viewpager缓存的个数，默认是2，表示前后各一个
        // 存在的问题，如果有的页面不需要缓存该如何自动刷新，可以利用eventbus传参来进行该页面的操作
        //takePictrue.openCamera();
        viewpager.setCurrentItem(0);


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation1:
                        viewpager.setCurrentItem(0);//首页
                        return true;
                    case R.id.navigation2:
                        viewpager.setCurrentItem(1);//社区
                        return true;
                    case R.id.navigation3:
                        viewpager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });



        init();


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {

        ActivityUtils.initStateInActivity(this,head2_ll_bar4);

        camera2Helper.startCameraPreView();

        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera2Helper.onDestroyHelper();
    }




    private void init(){

        //verifyAudioPermissions(this);//土办法申请权限
        file = new File(PHOTO_PATH, PHOTO_NAME + ".jpg");

        //Log.e("我的照片的地址哈哈哈哈",file.toString());
        camera2Helper=Camera2Helper.getInstance(CameraActivity.this,textureView,file);
        camera2Helper.setAfterDoListener(this);
    }

    public void takePicture(){
        camera2Helper.takePicture();cancel.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
    }
    public void startRecordingVideo(){
        camera2Helper.startRecordingVideo();

    }
    public void stopRecordingVideo(){
        camera2Helper.stopRecordingVideo();
        videoConfirm.setVisibility(View.VISIBLE);
        takeVideo.showDelete();
        navigation.setVisibility(View.GONE);

    }
    public void hiddenBottom(){
        navigation.setVisibility(View.GONE);
    }
    public void afterTakePicture(){

    }

    @Override
    public void onAfterPreviewBack() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onAfterTakePicture() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InputStream input = null;
                try {
                    input = new FileInputStream(file);
                    byte[] byt = new byte[input.available()];
                    input.read(byt);
                    Log.e("我日你妈",file.toString());
                    BitmapUtil.saveBitmap(BitmapUtil.byteToBitmap(byt),file);
                    //imageView.setImageBitmap(BitmapUtil.bytes2Bitmap(byt));
                } catch (FileNotFoundException e) {
                    Log.e("我日你妈1",file.toString());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("我日你妈2",file.toString());
                    e.printStackTrace();
                }
            }
        });
    }
    /*
     * 申请录音权限*/
    public static void verifyAudioPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);
        }
    }



}
