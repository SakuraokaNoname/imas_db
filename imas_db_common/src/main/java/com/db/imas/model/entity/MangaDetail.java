package com.db.imas.model.entity;

/**
 * @Author noname
 * @Date 2021/6/11 15:02
 * @Version 1.0
 */
public class MangaDetail {

    private int id;
    private int mid;
    private String subTitle;
    private int orderId;

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
