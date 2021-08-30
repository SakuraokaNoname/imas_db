package com.db.imas.attribute;


import com.db.imas.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Author noname
 * @Date 2021/8/17 16:29
 * @Version 1.0
 */
public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}

