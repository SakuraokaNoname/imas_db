package com.db.imas.util;

import com.db.imas.model.entity.ImasIP;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/10/7 10:53
 * @Version 1.0
 */
public class IPBlockList {

    private static final List<ImasIP> blockList = new ArrayList<>();

    public static void initBlockList(List<ImasIP> ipList){
        for(ImasIP ip : ipList){
            addBlockIP(ip);
        }
    }

    public static void addBlockIP(ImasIP ip){
        blockList.add(ip);
    }

    public static boolean isBlockIP(String ip){
        for(ImasIP imasIP : blockList){
            String blockIP = imasIP.getIp0() + "-" + imasIP.getIp255();
            if(IPUtil.ipIsValid(blockIP,ip)){
                return true;
            }
        }
        return false;
    }

}
