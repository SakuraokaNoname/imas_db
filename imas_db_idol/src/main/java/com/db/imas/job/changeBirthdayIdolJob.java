package com.db.imas.job;

import com.db.imas.service.ImasIdolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author noname
 * @Date 2021/8/12 15:49
 * @Version 1.0
 */
@Configuration
@EnableScheduling
public class changeBirthdayIdolJob {

    @Autowired
    private ImasIdolService idolService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void changeBirthdayIdol(){
        idolService.changeBirthdayIdol();
    }

}
