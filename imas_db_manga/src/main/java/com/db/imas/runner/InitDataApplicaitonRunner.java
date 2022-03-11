package com.db.imas.runner;

import com.db.imas.dao.ImasIpDao;
import com.db.imas.util.IPBlockList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author noname
 * @Date 2021/9/1 15:08
 * @Version 1.0
 * 初始化IP黑名单(日本,韩国)
 */
@Component
public class InitDataApplicaitonRunner implements ApplicationRunner {

    @Autowired
    private ImasIpDao imasIpDao;

    @Override
    public void run(ApplicationArguments var1) throws Exception {
        IPBlockList.initBlockList(imasIpDao.selectJapanAndKoreaIP());
    }
}
