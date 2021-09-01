package com.db.imas;

import com.db.imas.service.GroupChatService;
import com.db.imas.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author noname
 * @Date 2021/7/1 15:25
 * @Version 1.0
 */
@SpringBootTest(classes = ImasDbChatApplication.class)
public class TestApplication {

//    @Autowired
//    private GroupChatService groupChatService;

    @Test
    public void test(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(new Date(DateUtil.getNowTime()));
    }
}

