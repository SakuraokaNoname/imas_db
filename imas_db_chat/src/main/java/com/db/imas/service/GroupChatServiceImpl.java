package com.db.imas.service;

import com.alibaba.fastjson.JSON;
import com.db.imas.dao.ImasGroupChatMapper;
import com.db.imas.model.entity.ImasChatMessage;
import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.protocol.packet.GroupChatResponse;
import com.db.imas.protocol.packet.JoinGroupMessage;
import com.db.imas.protocol.packet.OffGroupChatMessage;
import com.db.imas.session.Session;
import com.db.imas.util.CollectionsUtil;
import com.db.imas.util.GroupUtil;
import com.db.imas.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

    @Override
    public void insertChatMessageJob() {
        Set<String> prefixKeySet = redisUtil.getPrefixKeySet(OFF_CHAT_PREFIX);
        if(CollectionUtils.isEmpty(prefixKeySet)){
            return;
        }
        for(String str : prefixKeySet){
            GroupChatResponse response = redisUtil.getObj(str,OffGroupChatMessage.class).getResponse();
            ImasChatMessage message = new ImasChatMessage();
            if(str.indexOf(OFF_CHAT_PREFIX + "chat:") > -1){
                message.setType("1");
            }else if(str.indexOf(OFF_CHAT_PREFIX + "group:") > -1){
                message.setType("2");
                message.setToChatId(response.getToGroupId());
            }
            message.setCreateTime(response.getCreateTime());
            message.setId(response.getId());
            message.setMessage(response.getMessage());
            message.setSender(response.getSender());

        }
    }

    @Override
    public void insertGroupChatMessage(GroupChatResponse response) {
        ImasChatMessage message = new ImasChatMessage();
        message.setType("2");
        message.setToChatId(response.getToGroupId());
        message.setCreateTime(response.getCreateTime());
        message.setId(response.getId());
        message.setMessage(response.getMessage());
        message.setSender(response.getSender());
        imasGroupChatMapper.insertChatMessage(message);
    }

    @Override
    public void insertGroupMember(JoinGroupMessage message) {
        String id = message.getId();
        System.out.println(id + "===");
        String member = GroupUtil.getGroupMember(message.getChatId());
        message.setId(member);
        System.out.println(message.getId() + "===");
        imasGroupChatMapper.updateGroupMember(message);
    }

    @Override
    public Session getProducer(String id) {
        return imasGroupChatMapper.getProducer(id);
    }
}
