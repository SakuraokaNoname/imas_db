package com.db.imas.model.dto;


import java.util.Date;

/**
 * @Author noname
 * @Date 2021/7/5 17:19
 * @Version 1.0
 */
public class MangaUserDTO {

    private int id;
    private String name;
    private String icon;
    private String token;
    private int permission;

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
