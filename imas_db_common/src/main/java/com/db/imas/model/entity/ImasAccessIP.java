package com.db.imas.model.entity;

import java.util.Date;

/**
 * @Author noname
 * @Date 2021/10/11 10:15
 * @Version 1.0
 */
public class ImasAccessIP {

    private int id;
    private String ip;
    private Date accessTime;
    private String accessAddr;
    private String isBlock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getAccessAddr() {
        return accessAddr;
    }

    public void setAccessAddr(String accessAddr) {
        this.accessAddr = accessAddr;
    }

    public String getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(String isBlock) {
        this.isBlock = isBlock;
    }
}
