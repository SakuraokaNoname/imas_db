package com.db.imas.model.entity;

import java.util.Date;

/**
 * @Author noname
 * @Date 2021/6/11 15:02
 * @Version 1.0
 */
public class MangaDetail {

    private int id;
    private int mid;
    private String subTitle;
    private String localizationGroup;
    private String translators;
    private String dantalions;
    private Date updateTime;
    private int orderId;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
