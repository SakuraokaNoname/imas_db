package com.db.imas.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author noname
 * @Date 2021/9/29 15:57
 * @Version 1.0
 */
public class MangaSubSearchDTO {

    private int sid;
    private int mid;
    private String subTitle;
    private String localizationGroup;
    private String translators;
    private String dantalions;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date updateTime;
    private String url;
    private String isShow;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
}
