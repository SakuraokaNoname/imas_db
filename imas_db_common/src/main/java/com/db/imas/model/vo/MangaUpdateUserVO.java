package com.db.imas.model.vo;

/**
 * @Author noname
 * @Date 2021/7/26 15:28
 * @Version 1.0
 */
public class MangaUpdateUserVO {

    private int id;
    private String name;
    private String icon;
    private String loginIP;

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
