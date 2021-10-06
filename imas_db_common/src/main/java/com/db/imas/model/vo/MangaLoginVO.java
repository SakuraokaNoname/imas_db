package com.db.imas.model.vo;

/**
 * @Author noname
 * @Date 2021/7/23 17:22
 * @Version 1.0
 */
public class MangaLoginVO {

    private String loginId;
    private String password;
    private String loginIP;

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String username) {
        this.loginId = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
