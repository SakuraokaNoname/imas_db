package com.db.imas.utils;

import java.text.ParseException;
import java.util.Date;

/**
 * token工具类
 * @author noname
 * @create 2020/9/23
 */
public class TokenUtil {

    // 生成token
    public static String createToken(String prefix,Integer id){
        StringBuffer sb = new StringBuffer(prefix);
        sb.append(id + "-");
        try {
            sb.append(DateUtil.format(new Date(),"YYYYMMddhhmmss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
