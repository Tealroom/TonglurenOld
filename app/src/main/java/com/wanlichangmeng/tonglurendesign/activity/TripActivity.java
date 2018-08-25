package com.wanlichangmeng.tonglurendesign.activity;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.fragment.ConditionFragment;
import com.wanlichangmeng.tonglurendesign.fragment.NewTripFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripActivity extends AppCompatActivity {






    NewTripFragment fragment1;


    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        ButterKnife.bind(this);

        manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        fragment1=new NewTripFragment();
        transaction.add(R.id.fragment1_activity_trip,fragment1);
        transaction.commit();

    }
}
