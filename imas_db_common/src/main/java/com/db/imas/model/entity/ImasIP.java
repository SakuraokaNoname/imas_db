package com.db.imas.model.entity;

/**
 * @Author noname
 * @Date 2021/10/5 15:19
 * @Version 1.0
 */
public class ImasIP {

    private int id;
    private String ip0;
    private String ip255;
    private String addr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp0() {
        return ip0;
    }

    public void setIp0(String ip0) {
        this.ip0 = ip0;
    }

    public String getIp255() {
        return ip255;
    }

    public void setIp255(String ip255) {
        this.ip255 = ip255;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
