package com.db.imas;

import com.db.imas.protocol.packet.GroupChatResponse;
import com.db.imas.protocol.packet.OffGroupChatMessage;
import com.db.imas.service.GroupChatService;
import com.db.imas.util.DateUtil;
import com.db.imas.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author noname
 * @Date 2021/7/1 15:25
 * @Version 1.0
 */
@SpringBootTest(classes = ImasDbChatApplication.class)
public class TestApplication {

    @Autowired
    private GroupChatService groupChatService;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test(){
//        GroupChatResponse response = redisUtil.getObj("OFF_CHAT:group:99999998:1", OffGroupChatMessage.class).getResponse();
//        groupChatService.insertGroupChatMessage(response);
//        int[] arr = {400,31,55,12,71,9,3};
//        Arrays.sort(arr);
//        for (int i : arr){
//            System.out.println(i);
//        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(new Date(DateUtil.getNowTime()));
    }
}

