package com.db.imas.util;

import com.db.imas.model.entity.ImasGroupChat;

import javax.swing.*;
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

    private static final Map<String, ImasGroupChat> groupListMap = new HashMap<>();

    public static void initGroupList(ImasGroupChat imasGroupChat){
        groupListMap.put(imasGroupChat.getChatId(),imasGroupChat);
    }

    public static ImasGroupChat getGroup(String chatId){
        return groupListMap.get(chatId);
    }

    public static List<ImasGroupChat> getGroupList(){
        return new ArrayList<>(groupListMap.values());
    }

}
