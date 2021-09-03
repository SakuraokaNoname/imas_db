package com.db.imas.util;

import com.db.imas.attribute.Attributes;
import com.db.imas.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @Author noname
 * @Date 2021/8/23 11:28
 * @Version 1.0
 */
public class SessionUtil {

    private static final Map<String, Channel> userChannelMap = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> groupChannelGroupMap = new ConcurrentHashMap<>();

    static {
    }

    public static Map<String, Channel> getUserChannelMap(){
        return userChannelMap;
    }

    public static Map<String, ChannelGroup> getGroupChannelGroupMap(){return groupChannelGroupMap;}

    public static void bindSession(Session session, Channel channel) {
        userChannelMap.put(session.getId() + "", channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            userChannelMap.remove(session.getId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String id) {

        return userChannelMap.get(id);
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupChannelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupChannelGroupMap.get(groupId);
    }
}
