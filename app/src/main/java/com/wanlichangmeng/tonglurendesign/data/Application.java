package com.wanlichangmeng.tonglurendesign.data;

public class Application {
    private String username;
    private String avatar;
    private boolean isFriend;
    private String time;
    private String plan_name;
    public Application(String username, String avatar, boolean isFriend,String time,String plan_name) {
        this.username = username;
        this.avatar = avatar;
        this.isFriend = isFriend;
        this.time = time;
        this.plan_name = plan_name;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public String getUsername() {
        return username;
    }
}
