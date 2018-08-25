package com.wanlichangmeng.tonglurendesign.data;

import com.wanlichangmeng.indexlib.IndexBar.bean.BaseIndexPinyinBean;

public class Contacts extends BaseIndexPinyinBean{
    private String city;//城市名字
    private boolean isTop;//是否是最上面的 不需要被转化成拼音的

    public Contacts() {
    }

    public Contacts(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public Contacts setCity(String city) {
        this.city = city;
        return this;
    }

    public boolean isTop() {
        return isTop;
    }

    public Contacts setTop(boolean top) {
        isTop = top;
        return this;
    }

    @Override
    public String getTarget() {
        return city;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }


    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }
}
