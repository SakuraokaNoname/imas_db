package com.db.imas.model.vo;

import java.util.Date;

/**
 * @Author noname
 * @Date 2021/6/11 15:01
 * @Version 1.0
 */
public class MangaVO {

    private int id;
    private int pid;
    private String title;
    private String profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

}
