package com.db.imas.config;

import com.db.imas.listener.KeyExpiredListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: chenlingtong
 * @CreateDate: 2022/3/10 16:06
 * @Version: 1.0.0
 */
@Component
public class RedisListenerConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisTemplate.getConnectionFactory());
        return redisMessageListenerContainer;
    }

    @Bean
    public KeyExpiredListener keyExpiredListenerT() {
        return new KeyExpiredListener(this.redisMessageListenerContainer());
    }

}
