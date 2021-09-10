package com.db.imas.service;

import com.alibaba.fastjson.JSON;
import com.db.imas.dao.ImasGroupChatMapper;
import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.protocol.packet.GroupChatResponse;
import com.db.imas.protocol.packet.OffGroupChatMessage;
import com.db.imas.session.Session;
import com.db.imas.util.CollectionsUtil;
import com.db.imas.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.db.imas.util.Constans.*;

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
    public void addCacheMessage(String offId, OffGroupChatMessage message) {
        redisUtil.putRaw(OFF_CHAT_PREFIX + offId,JSON.toJSONString(message),MESSAGE_EXPIRE);
    }

    @Override
    public Long getGroupChatIncId() {
        return redisUtil.incRaw(INC_PREFIX);
    }

    @Override
    public List<GroupChatResponse> getOffGroupChatMessageList(String id,String chatId) {
        Set<String> prefixKeySet = redisUtil.getPrefixKeySet(OFF_CHAT_PREFIX + chatId);
        if(CollectionUtils.isEmpty(prefixKeySet)){
            return null;
        }
        List<String> prefixKeyList = new ArrayList<>(prefixKeySet);
        List<String> sortList = CollectionsUtil.sort(prefixKeyList);
        List<GroupChatResponse> responseList = new ArrayList<>();
        for(String str : sortList){
            OffGroupChatMessage offGroupChatMessage = redisUtil.getObj(str,OffGroupChatMessage.class);
            if(offGroupChatMessage.getOffList().indexOf("," + id + ",") != -1){
                responseList.add(offGroupChatMessage.getResponse());
                String ids = offGroupChatMessage.getOffList();
                String newId = ids.replace("," + id + ",",",");
                offGroupChatMessage.setOffList(newId);
                redisUtil.putRaw(str,JSON.toJSONString(offGroupChatMessage),MESSAGE_EXPIRE);
            }
        }
        return responseList;
    }
}
