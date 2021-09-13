package com.db.imas.service;

import com.db.imas.model.entity.ImasChatMessage;
import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.protocol.packet.GroupChatResponse;
import com.db.imas.protocol.packet.JoinGroupMessage;
import com.db.imas.protocol.packet.OffGroupChatMessage;
import com.db.imas.session.Session;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/9/1 11:16
 * @Version 1.0
 */
public interface GroupChatService {

    List<ImasGroupChat> getGroupChatList();

    List<Session> getProducerList();

    void addCacheMessage(String offId, OffGroupChatMessage message);

    Long getGroupChatIncId();

    List<GroupChatResponse> getOffGroupChatMessageList(String id,String chatId);

    void insertChatMessageJob();

    void insertGroupChatMessage(GroupChatResponse response);

    void insertGroupMember(JoinGroupMessage message);
}
