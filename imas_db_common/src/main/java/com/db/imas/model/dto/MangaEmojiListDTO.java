package com.db.imas.model.dto;

import java.util.List;

/**
 * @Description:
 * @Author: chenlingtong
 * @CreateDate: 2022/1/5 17:01
 * @Version: 1.0.0
 * @Company: 名雕装饰股份有限公司
 */
public class MangaEmojiListDTO {

    private String icon;
    private String width;
    private String height;
    private List<String> list;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
