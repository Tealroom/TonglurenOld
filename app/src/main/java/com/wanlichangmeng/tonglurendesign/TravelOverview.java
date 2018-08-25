package com.wanlichangmeng.tonglurendesign;


import com.amap.api.maps.model.LatLng;

public class TravelOverview {
    private String username;
    private String title;
    private String avatar;
    private String description;
    private int travelType;
    private int travelMode;

    private LatLng positon;
    public TravelOverview(){

    }
    public boolean setUsername(String username){
        this.username = username;
        return true;
    }
    public boolean setTitle(String title){
        this.title = title;
        return true;
    }
    public boolean setAvatar(String avatar){
        this.avatar = avatar;
        return true;
    }
    public boolean setTravelType(int travelType){
        this.travelType = travelType;
        return true;
    }
    public boolean setTravelMode(int travelMode){
        this.travelMode = travelMode;
        return true;
    }
    public boolean setDescription(String description){
        this.description = description;
        return true;
    }
    public boolean setPositon(LatLng positon){
        this.positon = positon;
        return true;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public int getTravelMode() {
        return travelMode;
    }

    public int getTravelType() {
        return travelType;
    }

    public LatLng getPositon() {
        return positon;
    }
}
