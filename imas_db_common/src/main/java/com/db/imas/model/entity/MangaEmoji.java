package com.db.imas.model.entity;

import java.util.Date;

/**
 * @Author noname
 * @Date 2022/01/05 16:54
 * @Version 1.0
 */
public class MangaEmoji {

    private int id;
    private String type;
    private String isTab;
    private String url;
    private Date createTime;

    public String getIsTab() {
        return isTab;
    }

    public void setIsTab(String isTab) {
        this.isTab = isTab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
