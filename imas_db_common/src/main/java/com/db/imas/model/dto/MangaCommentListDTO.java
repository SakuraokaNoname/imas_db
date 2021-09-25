package com.db.imas.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author noname
 * @create 2021/7/26
 */
public class MangaCommentListDTO {

    private int id;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private String name;
    private String icon;
    private int likeCount;

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
