package com.db.imas.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author noname
 * @Date 2021/9/1 17:28
 * @Version 1.0
 */
public class DateUtil {

    public static String getNowTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        return formatter.format(localDateTime);
    }

}
