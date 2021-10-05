package com.db.imas.utils;

import com.db.imas.model.dto.MangaUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
