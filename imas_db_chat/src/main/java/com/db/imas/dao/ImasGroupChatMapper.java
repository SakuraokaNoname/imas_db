package com.db.imas.dao;

import com.db.imas.model.entity.ImasChatMessage;
import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.session.Session;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/9/1 11:07
 * @Version 1.0
 */
@Mapper
public interface ImasGroupChatMapper {

    List<ImasGroupChat> getGroupChatList();

    List<Session> getProducerList();

    void insertChatMessage(ImasChatMessage message);
}
