package com.db.imas.constans;

/**
 * @Author noname
 * @Date 2021/12/31 16:01
 * @Version 1.0
 */
public enum NoticeEnum {

    NEW_MANGA("newManga","漫画更新","[{manga}]{mangaTitle} 更新了!<br>嵌字:{dantalions} 翻译:{translators}",""),
    ;

    private String type;
    private String title;
    private String content;
    private String link;

    NoticeEnum(String type, String title, String content, String link) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
