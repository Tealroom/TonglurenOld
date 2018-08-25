package com.wanlichangmeng.tonglurendesign.data;

public class Plan {
    private String title;
    private String firstImage;

    private String time;
    private String type;
    private String status;

    public Plan(String title, String firstImage, String time,String type,String status) {
        this.title = title;
        this.firstImage = firstImage;
        this.type = type;
        this.time = time;
        this.status = status;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public String getTitle() {
        return title;
    }
}

