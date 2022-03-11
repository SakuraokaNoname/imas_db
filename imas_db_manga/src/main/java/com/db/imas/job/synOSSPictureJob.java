package com.db.imas.job;

import com.db.imas.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author noname
 * @Date 2021/10/13 17:10
 * @Version 1.0
 */
@Configuration
@EnableScheduling
public class synOSSPictureJob {

    @Autowired
    private MangaService mangaService;

    // 每天凌晨一点将访问记录录入数据库并删除缓存记录
//    @Scheduled(cron = "0 0 0/3 * * ?")
//    public void synOSSPictureJob() {
//        System.out.println("开始同步OSS图片");
//        int count = mangaService.synOSSPicture();
//        System.out.println("OSS图片同步完毕,共同步" + count + "张");
//    }

}
