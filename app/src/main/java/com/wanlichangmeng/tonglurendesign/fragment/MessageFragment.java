package com.wanlichangmeng.tonglurendesign.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.activity.ContactsActivity;
import com.wanlichangmeng.tonglurendesign.activity.ListActivity;
import com.wanlichangmeng.tonglurendesign.adapter.ChatItemAdapter;
import com.wanlichangmeng.tonglurendesign.utils.ActivityUtils;
import com.wanlichangmeng.tonglurendesign.data.Conversation;
import com.wanlichangmeng.tonglurendesign.data.UserInfo;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：消息
 */
public class MessageFragment extends Fragment {
    @BindView(R.id.ll_bar4)
    LinearLayout ll_bar4;
    @BindView(R.id.friend_list)
    TextView friend_list;
    @BindView(R.id.group_list)
    TextView group_list;
    @BindView(R.id.chat_recycler_view)
    SwipeMenuRecyclerView chatList;



    private List<Conversation> conversationList;

    private List<UserInfo> userInfoList;
    private ChatItemAdapter itemAdapter;
    private List<String> message ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this,view);
        Init();
        InitData();

        return view;
    }
    public void InitData(){
        Conversation c1 = new Conversation();
        UserInfo u1 = new UserInfo();
        Conversation c2 = new Conversation();

        message = new ArrayList<>() ;
        conversationList = new ArrayList<>();
        userInfoList = new ArrayList<>();
        message.add("hinds");
        message.add("fdsafsad");
        c2.setMessage(message);
        c2.setUnread(12);
        c2.setUserInfo(u1);
        c1.setMessage(message);
        c1.setUnread(122);
        c1.setUserInfo(u1);
        conversationList.add(c1);

        conversationList.add(c2);
        itemAdapter = new ChatItemAdapter(this.getContext(),conversationList,userInfoList);

        //设置RecyclerView管理器
        chatList.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        chatList.setAdapter(itemAdapter);






        chatList.setLongPressDragEnabled(true);// 开启长按拖拽
        chatList.setItemViewSwipeEnabled(true);// 开启滑动删除。
    }
    public void Init(){

        friend_list.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                bundle.putString("list_type","application_to_me");
                Intent intent = new Intent(getActivity(), ContactsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        group_list.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            Bundle bundle = new Bundle();
            public void onClick(View v) {
                bundle.putString("list_type","group_list");
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

//        SwipeMenuCreator creator = menu -> {
//            // create "delete" item
//            SwipeMenuItem deleteItem = new SwipeMenuItem(this.getContext());
//            // set item background
//            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
//            // set item width
//            deleteItem.setWidth(200);
//            deleteItem.setTitle("删除");
//            deleteItem.setTitleSize(18);
//            deleteItem.setTitleColor(Color.WHITE);
//            // add to menu
//            menu.addMenuItem(deleteItem);
//        };
//
//        // set creator
//        chatList.setMenuCreator(creator);
//        // Left
//        chatList.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
//
//        recyclerView.setLongPressDragEnabled(true);// 开启长按拖拽
//        recyclerView.setItemViewSwipeEnabled(true);// 开启滑动删除。
//        recyclerView.setOnItemMoveListener(onItemMoveListener);// 监听拖拽和侧滑删除，更新UI和数据。
//
//        chatList.setOnMenuItemClickListener((position, menu, index) -> {
//            if (index == 0) {
//                // 删除
//                conversationList.remove(position);
//            }
//
//            // false : close the menu; true : not close the menu
//            return false;
//        });





// 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
             // 各种文字和图标属性设置。
                leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。

//                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
//             // 各种文字和图标属性设置。
//                leftMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。

                // 注意：哪边不想要菜单，那么不要添加即可。
            }
        };
        // 设置监听器。
        chatList.setSwipeMenuCreator(mSwipeMenuCreator);



        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();

                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            }
        };
        // 菜单点击监听。
        chatList.setSwipeMenuItemClickListener(mMenuItemClickListener);




        OnItemMoveListener mItemMoveListener = new OnItemMoveListener() {
//            @Override
//            public boolean onItemMove(ViewHolder srcHolder, ViewHolder targetHolder) {
//                int fromPosition = srcHolder.getAdapterPosition();
//                int toPosition = targetHolder.getAdapterPosition();
//
//                // Item被拖拽时，交换数据，并更新adapter。
//                Collections.swap(conversationList, fromPosition, toPosition);
//                itemAdapter.notifyItemMoved(fromPosition, toPosition);
//                return true;
//            }


            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                int fromPosition = srcHolder.getAdapterPosition();
                int toPosition = targetHolder.getAdapterPosition();

                // Item被拖拽时，交换数据，并更新adapter。
                Collections.swap(conversationList, fromPosition, toPosition);
                itemAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                int position = srcHolder.getAdapterPosition();
                // Item被侧滑删除时，删除数据，并更新adapter。
                conversationList.remove(position);
                itemAdapter.notifyItemRemoved(position);
            }

//            @Override
//            public void onItemDismiss(ViewHolder srcHolder) {
//
//            }
        };
        chatList.setOnItemMoveListener(mItemMoveListener);// 监听拖拽和侧滑删除，更新UI和数据。
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {
        ActivityUtils.initStateInFragment(this,ll_bar4);
//        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.colorPrimary);//设置系统状态栏颜色
//        tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.mybgcolor_02));//设置系统状态栏背景图
        super.onResume();
    }



//    /**
//     * 适配器
//     */
//    class AppAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return mAppList.size();
//        }
//
//        @Override
//        public ApplicationInfo getItem(int position) {
//            return mAppList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = View.inflate(getApplicationContext(),
//                        R.layout.item_list_app, );
//                new ViewHolder(convertView);
//            }
//            ViewHolder holder = (ViewHolder) convertView.getTag();
//            ApplicationInfo item = getItem(position);
//            holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()));
//            holder.tv_name.setText(item.loadLabel(getPackageManager()));
//            holder.iv_icon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(SwipeMenuView.this, "iv_icon_click", Toast.LENGTH_SHORT).show();
//                }
//            });
//            holder.tv_name.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(SwipeMenuView.this, "tv_name_click", Toast.LENGTH_SHORT).show();
//                }
//            });
//            return convertView;
//        }
//
//        class ViewHolder {
//            @BindView(R.id.iv_icon)
//            ImageView iv_icon;
//            @BindView(R.id.tv_name)
//            TextView tv_name;
//
//            public ViewHolder(View view) {
//                ButterKnife.bind(this,view);
//                view.setTag(this);
//            }
//        }
//    }
}
