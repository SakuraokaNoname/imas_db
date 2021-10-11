package com.db.imas.job;

import com.db.imas.model.entity.ImasAccessIP;
import com.db.imas.model.entity.ImasIP;
import com.db.imas.service.MangaAccessService;
import com.db.imas.utils.Constants;
import com.db.imas.utils.IPUtil;
import com.db.imas.utils.RedisUtil;
import io.netty.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author noname
 * @Date 2021/10/11 10:08
 * @Version 1.0
 */
@Configuration
@EnableScheduling
public class insertIPDataJob {

    @Autowired
    private MangaAccessService mangaAccessService;

    @Autowired
    private RedisUtil redisUtil;

    // 每天凌晨一点将访问记录录入数据库并删除缓存记录
    @Scheduled(cron = "0 0 1 * * ?")
    public void insertIPData() throws ParseException {
        List<ImasAccessIP> addAccessIPList = new ArrayList<>();
        addAccessIPList = getAccessIP(addAccessIPList, Constants.ACCESS_PREFIX);
        addAccessIPList = getAccessIP(addAccessIPList, Constants.ACCESS_ERROR_PREFIX);
        for(ImasAccessIP imasAccessIP : addAccessIPList){
            mangaAccessService.insertIPData(imasAccessIP);
        }
    }

    private List<ImasAccessIP> getAccessIP(List<ImasAccessIP> addAccessIPList,String prefix) throws ParseException {
        Set<String> accessSet = redisUtil.getPrefixKeySet(prefix);
        if(!accessSet.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
            ImasAccessIP accessIP = new ImasAccessIP();
            for(String pre : accessSet){
                String ip = redisUtil.getRaw(pre);
                String accessTime = pre.substring(pre.indexOf(":") + 1);
                accessIP.setIp(ip);
                if(Constants.ACCESS_PREFIX.equals(prefix)){
                    accessIP.setIsBlock("0");
                }else if(Constants.ACCESS_ERROR_PREFIX.equals(prefix)){
                    accessIP.setIsBlock("1");
                }
                accessIP.setAccessTime(sdf.parse(accessTime));
                // TODO 根据IP搜索地区
                accessIP.setAccessAddr(getAccessAddr(ip));
                addAccessIPList.add(accessIP);
                redisUtil.del(pre);
            }
        }
        return addAccessIPList;
    }

    private String getAccessAddr(String ip){
        String[] splitStr = ip.split("[.]");
        String prefixIP = splitStr[0] + "." + splitStr[1] + ".";
        List<ImasIP> searchIPList = mangaAccessService.selectPrefixIP(prefixIP);
        for(ImasIP imasIP : searchIPList){
            String IPSection = imasIP.getIp0() + "-" + imasIP.getIp255();
            if(IPUtil.ipIsValid(IPSection,ip)){
                return imasIP.getAddr();
            }
        }
        String subPrefixIP = splitStr[0] + ".";
        List<ImasIP> subSearchIPList = mangaAccessService.selectPrefixIP(subPrefixIP);
        for(ImasIP imasIP : subSearchIPList){
            String IPSection = imasIP.getIp0() + "-" + imasIP.getIp255();
            if(IPUtil.ipIsValid(IPSection,ip)){
                return imasIP.getAddr();
            }
        }
        return "error";
    }

}
