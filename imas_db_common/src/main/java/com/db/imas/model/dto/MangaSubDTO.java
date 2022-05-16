package com.db.imas.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author noname
 * @Date 2021/6/15 15:32
 * @Version 1.0
 */
public class MangaSubDTO {

    private int sid;
    private int mid;
    private String subTitle;
    private String localizationGroup;
    private String translators;
    private String dantalions;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date updateTime;
    private String url;
    private int commentCount;
    private String isShow;
    private String debutIdol;

    public String getDebutIdol() {
        return debutIdol;
    }

    public void setDebutIdol(String debutIdol) {
        this.debutIdol = debutIdol;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getLocalizationGroup() {
        return localizationGroup;
    }

    public void setLocalizationGroup(String localizationGroup) {
        this.localizationGroup = localizationGroup;
    }

    public String getTranslators() {
        return translators;
    }

    public void setTranslators(String translators) {
        this.translators = translators;
    }

    public String getDantalions() {
        return dantalions;
    }

    public void setDantalions(String dantalions) {
        this.dantalions = dantalions;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
