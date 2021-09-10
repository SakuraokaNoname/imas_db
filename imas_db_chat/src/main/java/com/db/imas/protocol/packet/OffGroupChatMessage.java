package com.db.imas.protocol.packet;

/**
 * @Author noname
 * @Date 2021/9/6 14:56
 * @Version 1.0
 */
public class OffGroupChatMessage {

    private String offList;
    private GroupChatResponse response;

    public String getOffList() {
        return offList;
    }

    public void setOffList(String offList) {
        this.offList = offList;
    }

    public GroupChatResponse getResponse() {
        return response;
    }

    public void setResponse(GroupChatResponse response) {
        this.response = response;
    }
}
