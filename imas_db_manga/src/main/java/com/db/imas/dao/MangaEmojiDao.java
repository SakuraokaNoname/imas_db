package com.db.imas.dao;

import com.db.imas.model.entity.MangaEmoji;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author noname
 * @Date 2022/01/05 16:54
 * @Version 1.0
 */
@Mapper
public interface MangaEmojiDao {

    List<MangaEmoji> getMangaEmojiList(@Param("type")String type);

}
