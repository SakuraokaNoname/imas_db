package com.db.imas.model.vo;

import java.util.Date;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/7/27 16:24
 * @Version 1.0
 */
public class MangaAddMangaDetailVO {

    private int sid;
    private int mid;
    private String subTitle;
    private String localizationGroup;
    private String translators;
    private String dantalions;
    private Date updateTime;
    private int orderId;
    private List<String> pics;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
