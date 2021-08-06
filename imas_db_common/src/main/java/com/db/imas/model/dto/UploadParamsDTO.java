package com.db.imas.model.dto;

/**
 * @Author noname
 * @Date 2021/8/4 15:29
 * @Version 1.0
 */
public class UploadParamsDTO {

    private String title;
    private Integer mid;
    private Integer sid;
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
