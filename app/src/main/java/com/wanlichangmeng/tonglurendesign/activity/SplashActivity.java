package com.wanlichangmeng.tonglurendesign.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import com.wanlichangmeng.tonglurendesign.R;
/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：程序最初的入口，用来显示app的logo
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载启动图片
        setContentView(R.layout.activity_splash);



        //后台处理耗时任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                //耗时任务，比如加载网络数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //延时三秒钟
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        //跳转至 MainActivity
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);

                        //结束当前的 Activity
                        SplashActivity.this.finish();
                    }
                });
            }
        }).start();
    }
}
