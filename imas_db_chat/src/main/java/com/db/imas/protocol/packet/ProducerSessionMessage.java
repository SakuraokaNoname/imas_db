package com.db.imas.protocol.packet;

/**
 * @Author noname
 * @Date 2021/8/23 11:21
 * @Version 1.0
 */
public class ProducerSessionMessage {

    private String id;
    private String name;
    private String icon;
    private String chatId;

    public ProducerSessionMessage(String id, String name, String icon, String chatId) {
        this.id = id;
        this.name = name;
        this.icon = icon;
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

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
