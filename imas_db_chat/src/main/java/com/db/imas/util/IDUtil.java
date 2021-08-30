package com.db.imas.util;

import java.util.UUID;

/**
 * @Author noname
 * @Date 2021/8/23 15:35
 * @Version 1.0
 */
public class IDUtil {

    public static String randomId() {
        String id = "";
        for(String str : UUID.randomUUID().toString().split("-")){
            id += str;
        }
        return id.substring(0,16);
    }

    public static void main(String[] args) {
        System.out.println(randomId());
    }
}
