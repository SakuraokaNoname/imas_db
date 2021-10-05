package com.db.imas.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/10/5 11:42
 * @Version 1.0
 */
public class IPUtil {

    private static final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
    private static final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;

    public static boolean ipIsValid(String ipSection, String ip) {
        if (ipSection == null){
            throw new NullPointerException("IP段不能为空！");
        }
        if (ip == null){
            throw new NullPointerException("IP不能为空！");
        }
        if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP)){
            return false;
        }
        int idx = ipSection.indexOf('-');
        String[] sips = ipSection.substring(0, idx).split("\\.");
        String[] sipe = ipSection.substring(idx + 1).split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }

    public static String getIp2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static List<String> getIpInfo(String str){
        List<String> ipList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            String splitStr = str;
            if(i != 2){
                int index = splitIpInfoIndex(str);
                if(index == -1){
                    throw new NullPointerException();
                }
                splitStr = str.substring(0,index).trim();
                str = str.substring(index).trim();
            }
            ipList.add(splitStr);
        }
        return ipList;
    }

    public static int splitIpInfoIndex(String str){
        int index = 0;
        for(int i = 0; i < 3; i++){
            int tempIndex = 0;
            if(i == 2){
                index += str.indexOf(".") + 4;
                return index;
            }else{
                index += (str.indexOf(".") + 1);
                tempIndex = (str.indexOf(".") + 1);
            }
            str = str.substring(tempIndex).trim();
        }
        return -1;
    }

}
