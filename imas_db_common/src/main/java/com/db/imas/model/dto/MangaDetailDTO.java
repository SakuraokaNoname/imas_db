package com.db.imas.model.dto;

import com.db.imas.model.entity.MangaPicture;

import java.util.List;

/**
 * @author noname
 * @create 2021/6/14
 */
public class MangaDetailDTO {

    private int id;
    private int mid;
    private String subTitle;
    private int orderId;
    private List<MangaPicture> pics;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<MangaPicture> getPics() {
        return pics;
    }

    public void setPics(List<MangaPicture> pics) {
        this.pics = pics;
    }
}
