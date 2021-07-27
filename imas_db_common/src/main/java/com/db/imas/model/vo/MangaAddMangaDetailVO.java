package com.db.imas.model.vo;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/7/27 16:24
 * @Version 1.0
 */
public class MangaAddMangaDetailVO {

    private int id;
    private int mid;
    private String subTitle;
    private String localizationGroup;
    private String translators;
    private String dantalions;
    private String updateTime;
    private List<MangaAddMangaPictureVO> pics;

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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<MangaAddMangaPictureVO> getPics() {
        return pics;
    }

    public void setPics(List<MangaAddMangaPictureVO> pics) {
        this.pics = pics;
    }
}
