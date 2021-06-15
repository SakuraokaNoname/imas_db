package com.db.imas.model.dto;

/**
 * @Author noname
 * @Date 2021/6/15 15:32
 * @Version 1.0
 */
public class MangaSubDTO {

    private int id;
    private String subTitle;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
