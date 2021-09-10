package com.db.imas.protocol;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.packet.*;

import java.util.HashMap;
import java.util.Map;

import static com.db.imas.protocol.command.Command.*;

/**
 * @Author noname
 * @Date 2021/8/28 17:36
 * @Version 1.0
 */
public class DataPacketCodec {

    public static final DataPacketCodec INSTANCE = new DataPacketCodec();

    private final Map<Byte, Class<? extends DataPacket>> packetTypeMap;

    private DataPacketCodec() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(CONNECTION, ConnectionMessage.class);
        packetTypeMap.put(PRODUCER_LIST, BasicMessage.class);
        packetTypeMap.put(GROUP_CHAT, GroupChatMessage.class);
        packetTypeMap.put(BE_ONLINE, BeOnlineMessage.class);
        packetTypeMap.put(OFF_LINE_CHAT_MESSAGE, OffLineChatMessage.class);
        packetTypeMap.put(KEEPALIVE, KeepAliveMessage.class);
    }

    public DataPacket decode(String text) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Map<String,Object> map = JSON.parseObject(text,Map.class);
        Integer commandMap = (Integer)map.get("command");
        Byte command = (byte) commandMap.intValue();
        Class clazz = packetTypeMap.get(command);
        DataPacket packet = (DataPacket)clazz.newInstance();
        packet.setOriginalText(text);
        return packet;
    }

}
