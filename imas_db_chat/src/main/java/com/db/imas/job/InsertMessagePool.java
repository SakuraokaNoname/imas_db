package com.db.imas.job;

import com.db.imas.model.entity.ImasChatMessage;
import com.db.imas.protocol.packet.GroupChatResponse;
import com.db.imas.service.GroupChatService;
import com.db.imas.util.ServiceUtil;
import org.springframework.util.ObjectUtils;

/**
 * @Author noname
 * @Date 2021/9/11 16:40
 * @Version 1.0
 */
public class InsertMessagePool implements Runnable{

    private static GroupChatService groupChatService;

    private GroupChatResponse response;

    public InsertMessagePool(GroupChatResponse response){
        this.response = response;
        groupChatService = ServiceUtil.getBean(GroupChatService.class);
    }

    @Override
    public void run() {
        try{
            System.out.println("信息存入数据库");
            if(ObjectUtils.isEmpty(response)){
                return;
            }
            groupChatService.insertGroupChatMessage(response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
