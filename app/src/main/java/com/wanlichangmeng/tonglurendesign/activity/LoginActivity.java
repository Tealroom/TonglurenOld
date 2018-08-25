package com.wanlichangmeng.tonglurendesign.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wanlichangmeng.tonglurendesign.R;
/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：二级activity，在特殊条件（登陆信息过期）下进入的activity
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
