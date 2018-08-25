package com.wanlichangmeng.tonglurendesign.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.fragment.NewTripFragment;
import com.wanlichangmeng.tonglurendesign.fragment.UserFragment;

import butterknife.ButterKnife;

public class PersonalActivity extends AppCompatActivity {

    UserFragment fragment1;


    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            fragment1 = new UserFragment();
            transaction.add(R.id.fragment_activity_personal, UserFragment.newInstance("others"));
            transaction.commit();
        }
    }
}
