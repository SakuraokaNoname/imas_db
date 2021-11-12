package com.db.imas.job;

import com.db.imas.service.MangaAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author noname
 * @Date 2021/11/10 15:30
 * @Version 1.0
 */
@Configuration
@EnableScheduling
public class synAccessCountJob {

    @Autowired
    private MangaAccessService mangaAccessService;

    // 每十分钟更新访问人数
    @Scheduled(cron = "0 0/10 * * * ?")
    public void synAccessCountJob() {
        System.out.println("更新访问人数");
        mangaAccessService.synAccessCount();
    }

}
