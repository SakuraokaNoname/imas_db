package com.db.imas.session;

/**
 * @Author noname
 * @Date 2021/8/23 11:21
 * @Version 1.0
 */
public class Session {
    // 用户唯一性标识
    private String id;
    private String name;
    private String icon;
    private String chatId;

    public Session(String id, String name, String icon, String chatId) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
