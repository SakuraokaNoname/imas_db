package com.db.imas.model.vo;

/**
 * @Author noname
 * @Date 2021/7/26 15:28
 * @Version 1.0
 */
public class MangaUpdateUserVO {

    private int id;
    private String name;
    private String password;
    private String icon;
    private Integer permission;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}
