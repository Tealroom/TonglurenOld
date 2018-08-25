package com.wanlichangmeng.tonglurendesign.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.data.Updating;
import com.wanlichangmeng.tonglurendesign.fragment.NewTripFragment;
import com.wanlichangmeng.tonglurendesign.fragment.NewUpdatingFragment;
import com.wanlichangmeng.tonglurendesign.fragment.UpdatingContentFragment;

import butterknife.ButterKnife;

public class UpdatingActivity extends AppCompatActivity {

    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatding);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            String hh = bundle.getString("new_or_content");

            Updating hehe = (Updating) bundle.getSerializable("data");//这一步可以省略好像，直接在fragment里面获取getActivity之后再来搞这个


            manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            if(hh.equals("new")){
                transaction.add(R.id.fragment_activity_updating, NewUpdatingFragment.newInstance("new"));
            }else if(hh.equals("content")){
                transaction.add(R.id.fragment_activity_updating, UpdatingContentFragment.newInstance(hehe));
            }





            transaction.commit();
        }

    }
}
