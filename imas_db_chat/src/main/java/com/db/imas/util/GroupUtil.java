package com.db.imas.util;

import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.session.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
