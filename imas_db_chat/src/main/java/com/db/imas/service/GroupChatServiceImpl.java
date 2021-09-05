package com.db.imas.service;

import com.alibaba.fastjson.JSON;
import com.db.imas.dao.ImasGroupChatMapper;
import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.protocol.packet.GroupChatResponse;
import com.db.imas.session.Session;
import com.db.imas.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.db.imas.util.Constans.NO_RECEIVE;
import static com.db.imas.util.Constans.OFF_CHAT_PREFIX;

/**
 * @Author noname
 * @Date 2021/9/1 11:17
 * @Version 1.0
 */
@Service
public class GroupChatServiceImpl implements GroupChatService{

    @Autowired
    private ImasGroupChatMapper imasGroupChatMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<ImasGroupChat> getGroupChatList() {
        List<ImasGroupChat> groupChatList = imasGroupChatMapper.getGroupChatList();
        return groupChatList;
    }

    @Override
    public List<Session> getProducerList() {
        return imasGroupChatMapper.getProducerList();
    }

    @Override
    public void addCacheMessage(String offId,GroupChatResponse response) {
        response.setIsReceive(NO_RECEIVE);
        System.out.println(OFF_CHAT_PREFIX + offId + JSON.toJSONString(response));
        redisUtil.putRaw(OFF_CHAT_PREFIX + offId,JSON.toJSONString(response));
    }
}
