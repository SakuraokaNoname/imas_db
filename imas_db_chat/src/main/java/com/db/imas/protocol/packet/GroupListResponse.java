package com.db.imas.protocol.packet;

import com.db.imas.session.Producer;

import java.util.List;

import static com.db.imas.protocol.command.Command.GROUP_LIST;

/**
 * @Author noname
 * @Date 2021/9/1 15:37
 * @Version 1.0
 */
public class GroupListResponse{

    private int id;
    private String title;
    private String info;
    private String icon;
    private String chatId;
    private List<Producer> producers;

    public GroupListResponse(int id, String title, String info, String icon, String chatId) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.icon = icon;
        this.chatId = chatId;
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

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }
}
