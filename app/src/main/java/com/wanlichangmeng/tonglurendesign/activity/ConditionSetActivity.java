package com.wanlichangmeng.tonglurendesign.activity;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.fragment.ConditionFragment;
import com.wanlichangmeng.tonglurendesign.fragment.ConditionSetFragment;
import com.wanlichangmeng.tonglurendesign.fragment.MapFragment;
import com.wanlichangmeng.tonglurendesign.fragment.NewTripFragment;

import butterknife.ButterKnife;

public class ConditionSetActivity extends AppCompatActivity {

    ConditionSetFragment fragment1;

    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_set);
        ButterKnife.bind(this);

        manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        fragment1=new ConditionSetFragment();
        transaction.add(R.id.fragment1_activity_condition_set,fragment1);
        transaction.commit();
    }



}
