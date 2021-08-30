package com.db.imas.protocol;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Author noname
 * @Date 2021/8/28 15:37
 * @Version 1.0
 */
public abstract class DataPacket{

    private String originalText;

    @JSONField(serialize = false)
    public abstract Byte getCommand();

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    @Override
    public String toString() {
        return "DataPacket{" +
                "originalText='" + originalText + '\'' +
                '}';
    }
}
