package com.db.imas.model.dto;

import java.util.List;

/**
 * @Author noname
 * @Date 2022/01/05 16:54
 * @Version 1.0
 */
public class MangaEmojiDTO {

    private List<MangaEmojiListDTO> imgList;

    public List<MangaEmojiListDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<MangaEmojiListDTO> imgList) {
        this.imgList = imgList;
    }
}
