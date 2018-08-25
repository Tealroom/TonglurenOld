package com.wanlichangmeng.tonglurendesign.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.fragment.TripDetailFragment;
import com.wanlichangmeng.tonglurendesign.fragment.UserFragment;

import butterknife.ButterKnife;

public class TripDetailActivity extends AppCompatActivity {

    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            transaction.add(R.id.fragment1_activity_trip_detail, TripDetailFragment.newInstance("double"));
            transaction.commit();
        }
    }
}
