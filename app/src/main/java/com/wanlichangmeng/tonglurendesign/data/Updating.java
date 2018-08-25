package com.wanlichangmeng.tonglurendesign.data;

import java.io.Serializable;
import java.util.List;

public class Updating implements Serializable {
    private boolean isVideo;
    private String username;
    private String avatar;
    private List<String> image;
    private String video;

    public Updating(String username, String avatar, boolean isVideo) {
        this.username = username;
        this.avatar = avatar;
        this.isVideo = isVideo;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo() {
        return video;
    }
}
