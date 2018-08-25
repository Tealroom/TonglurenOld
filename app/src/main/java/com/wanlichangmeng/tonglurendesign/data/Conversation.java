package com.wanlichangmeng.tonglurendesign.data;

import com.wanlichangmeng.tonglurendesign.data.UserInfo;

import java.util.List;

public class Conversation {
    private UserInfo userInfo;
    private List<String> message;//这里应该是message对象才对
    private int unread;

    public String getMessage() {
        return message.get(message.size()-1);
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getUnreadNum() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }
}
