package com.db.imas.protocol.packet;

import java.util.List;
import java.util.Map;

import static com.db.imas.protocol.command.Command.GROUP_LIST;

/**
 * @Author noname
 * @Date 2021/9/2 15:42
 * @Version 1.0
 */
public class GroupListPacketResponse {

    private Byte command = GROUP_LIST;
    private Map<String, List<GroupListResponse>> groupListResponseList;

    public GroupListPacketResponse(Map<String, List<GroupListResponse>> groupListResponseList) {
        this.groupListResponseList = groupListResponseList;
    }

    public Byte getCommand() {
        return command;
    }

    public void setCommand(Byte command) {
        this.command = command;
    }

    public Map<String, List<GroupListResponse>> getGroupListResponseList() {
        return groupListResponseList;
    }

    public void setGroupListResponseList(Map<String, List<GroupListResponse>> groupListResponseList) {
        this.groupListResponseList = groupListResponseList;
    }
}
