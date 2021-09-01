package com.db.imas.service;

import com.alibaba.fastjson.JSON;
import com.db.imas.dao.ImasGroupChatMapper;
import com.db.imas.model.entity.ImasGroupChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/9/1 11:17
 * @Version 1.0
 */
@Service
public class GroupChatServiceImpl implements GroupChatService{

    @Autowired
    private ImasGroupChatMapper imasGroupChatMapper;

    @Override
    public List<ImasGroupChat> getGroupChatList() {
        List<ImasGroupChat> groupChatList = imasGroupChatMapper.getGroupChatList();
        return groupChatList;
    }
}
