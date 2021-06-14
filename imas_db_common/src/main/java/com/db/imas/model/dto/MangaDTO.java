package com.db.imas.model.dto;

import java.util.Date;

/**
 * @Author noname
 * @Date 2021/6/11 15:01
 * @Version 1.0
 */
public class MangaDTO {

    private int id;
    private String title;
    private String profile;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
