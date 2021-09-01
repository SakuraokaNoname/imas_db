package com.db.imas.runner;

import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.service.GroupChatService;
import com.db.imas.util.GroupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/9/1 15:08
 * @Version 1.0
 * 初始化数据
 */
@Component
public class InitDataApplicaitonRunner implements ApplicationRunner {

    @Autowired
    private GroupChatService groupChatService;

    @Override
    public void run(ApplicationArguments var1) throws Exception{
        List<ImasGroupChat> imasGroupChats = groupChatService.getGroupChatList();
        for (ImasGroupChat imasGroupChat:imasGroupChats){
            GroupUtil.initGroupList(imasGroupChat);
        }
    }
}
