package com.db.imas.model.dto;

/**
 * @Author noname
 * @Date 2021/10/13 15:57
 * @Version 1.0
 */
public class MangaPictureDownloadDTO {

    private int mid;
    private int sid;
    private String img;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
