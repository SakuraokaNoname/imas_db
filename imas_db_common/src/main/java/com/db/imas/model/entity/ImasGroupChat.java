package com.db.imas.model.entity;

/**
 * @Author noname
 * @Date 2021/9/1 11:01
 * @Version 1.0
 */
public class ImasGroupChat {

    private int id;
    private String title;
    private String info;
    private String icon;
    private String chatId;
    private String member;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
