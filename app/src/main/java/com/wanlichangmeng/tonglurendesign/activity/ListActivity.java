package com.wanlichangmeng.tonglurendesign.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.wanlichangmeng.tonglurendesign.R;
//import com.wanlichangmeng.tonglurendesign.adapter.TabFragmentListAdapter;
import com.wanlichangmeng.tonglurendesign.fragment.GroupChatFragment;
import com.wanlichangmeng.tonglurendesign.fragment.MyApplicationFragment;
import com.wanlichangmeng.tonglurendesign.fragment.MyPlanFragment;
import com.wanlichangmeng.tonglurendesign.fragment.MyUpdatingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {
    FragmentManager manager;



    MyUpdatingFragment myUpdatingFragment;
    MyUpdatingFragment otherUpdatingFragment;
    MyApplicationFragment myApplicationFragment1;
    MyApplicationFragment myApplicationFragment2;
    MyPlanFragment myPlanFragment;
    MyPlanFragment otherPlanFragment;
    GroupChatFragment groupChat;

    int i=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        i++;

        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            String hh = bundle.getString("list_type");

            //showwhat.setText(hh);

            manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            if(hh.equals("updating_myself")){

                Log.e("多少次1:",""+i);
                myUpdatingFragment=MyUpdatingFragment.newInstance("myself");
                transaction.add(R.id.fragment_activity_List, myUpdatingFragment);
            }else if(hh.equals("updating_others")){

                Log.e("多少次2:",""+i);
                otherUpdatingFragment=MyUpdatingFragment.newInstance("others");
                transaction.add(R.id.fragment_activity_List, otherUpdatingFragment);

            }else if(hh.equals("application_to_me")){

                Log.e("多少次2:",""+i);
                myApplicationFragment1=MyApplicationFragment.newInstance("myself");
                transaction.add(R.id.fragment_activity_List, myApplicationFragment1);

            }else if(hh.equals("application_to_other")){

                Log.e("多少次3:",""+i);
                myApplicationFragment2=MyApplicationFragment.newInstance("others");
                transaction.add(R.id.fragment_activity_List, myApplicationFragment2);
            }else if(hh.equals("plan_myself")){

                Log.e("多少次4:",""+i);
                myPlanFragment=MyPlanFragment.newInstance("myself");
                transaction.add(R.id.fragment_activity_List, myPlanFragment);
            }else if(hh.equals("plan_others")){

                Log.e("多少次2:",""+i);
                otherPlanFragment=MyPlanFragment.newInstance("others");
                transaction.add(R.id.fragment_activity_List, otherPlanFragment);

            }else if(hh.equals("group_list")){

                Log.e("多少次2:",""+i);
                groupChat= GroupChatFragment.newInstance("others");
                transaction.add(R.id.fragment_activity_List, groupChat);

            }else{
                Log.e("多少次5:",hh);
            }







            //transaction.add(R.id.fragment_activity_personal, UserFragment.newInstance("others"));
            transaction.commit();
        }
        //initList();
    }


}
