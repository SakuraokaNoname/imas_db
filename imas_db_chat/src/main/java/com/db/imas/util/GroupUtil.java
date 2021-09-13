package com.db.imas.util;

import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.protocol.packet.GroupListResponse;
import com.db.imas.session.Producer;
import com.db.imas.session.Session;
import io.netty.channel.Channel;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.db.imas.util.Constans.PRODUCER_ONLINE;

/**
 * @Author noname
 * @Date 2021/9/1 15:04
 * @Version 1.0
 */
public class GroupUtil {

    private static final Map<String, Session> producerMap = new HashMap<>();

    private static final Map<String, ImasGroupChat> groupListMap = new HashMap<>();

    private static final Map<String, List<String>> groupOfflineMap = new HashMap<>();

    public static void initGroupList(ImasGroupChat imasGroupChat){
        groupListMap.put(imasGroupChat.getChatId(),imasGroupChat);
    }

    public static void initgroupOfflineMap(String chatId,List<String> ids){
        groupOfflineMap.put(chatId, ids);
    }

    public static List<String> getGroupOfflineList(String chatId){
        return groupOfflineMap.get(chatId);
    }

    public static ImasGroupChat getGroup(String chatId){
        return groupListMap.get(chatId);
    }

    public static Map<String, ImasGroupChat> getGroupListMap() {
        return groupListMap;
    }

    public static void initProducerMap(Session session){
        producerMap.put(session.getId() + "",session);
    }

    public static Session getProducer(String id){
        return producerMap.get(id);
    }

    public static List<Session> getProducerList(){
        return new ArrayList<>(producerMap.values());
    }

    public static String getGroupMember(String chatId){
        return getGroup(chatId).getMember();
    }

    public static void addGroupMember(String id,String chatId){
        ImasGroupChat groupChat = getGroup(chatId);
        if(groupChat != null){
            String member = groupChat.getMember();
            groupChat.setMember(member + id + ",");
        }
        initGroupList(groupChat);
    }

    public static void removeGroupMember(String id){
        for(String key : groupListMap.keySet()){
            //更新离线列表
            List<String> ids = getGroupOfflineList(key);
            ids.add(id);
            initgroupOfflineMap(key,ids);
        }
    }

    public static Map<String, List<GroupListResponse>> refreshGroupMap(){
        Map<String,List<GroupListResponse>> groupListResponseMap = new HashMap<>();
        Map<String, ImasGroupChat> groupListMap = getGroupListMap();
        List<GroupListResponse> groupListResponseList = new ArrayList<>();
        for(String key : groupListMap.keySet()){
            ImasGroupChat imasGroupChat = groupListMap.get(key);
            GroupListResponse groupListResponse = new GroupListResponse(imasGroupChat.getId(),imasGroupChat.getTitle(),imasGroupChat.getInfo(),imasGroupChat.getIcon(),imasGroupChat.getChatId());
            List<Producer> producers = new ArrayList<>();
            for(String member : imasGroupChat.getMember().split(",")){
                if(!StringUtils.isEmpty(member)){
                    Producer producer = new Producer();
                    producer.setId(Integer.parseInt(member));
                    Channel channel = SessionUtil.getChannel(member);
                    if(channel != null){
                        producer.setIsOnline(PRODUCER_ONLINE);
                        // TODO 添加进channelGroup
                    }
                    producers.add(producer);
                }
            }
            groupListResponse.setProducers(producers);
            groupListResponseList.add(groupListResponse);
        }
        groupListResponseMap.put("groupChat",groupListResponseList);
        return groupListResponseMap;
    }
}
