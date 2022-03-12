package com.db.imas.listener;

import com.db.imas.service.MangaService;
import com.db.imas.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

import static com.db.imas.util.Constants.*;

/**
 * @Author noname
 * @Date 2022/03/10 15:50
 * @Version
 * @Description redis 监控超时数据处理业务
 */
@Component
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MangaService mangaService;

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        if (key.startsWith(DELETE_MANGA_SUB_LIST_TOKEN)) {
            String id = key.substring(key.lastIndexOf(":") + 1);
            redisUtil.del(MANGA_SUB_LIST_TOKEN + id);
        }
        else if(key.startsWith(SYN_MANGA_PICTURE_TOKEN)){
            mangaService.synOSSPicture();
        }
    }

}

