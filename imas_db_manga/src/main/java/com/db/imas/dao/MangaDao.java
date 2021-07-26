package com.db.imas.dao;

import com.db.imas.model.dto.MangaDTO;
import com.db.imas.model.dto.MangaDetailDTO;
import com.db.imas.model.dto.MangaSubDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/6/11 15:48
 * @Version 1.0
 */
@Mapper
public interface MangaDao {

    List<MangaDTO> getMangaList();

    List<MangaSubDTO> getMangaSubList(@Param("id")Integer id);

    MangaDetailDTO getMangaDetail(@Param("id")Integer id);

    Integer changeChapter(@Param("mid") Integer mid,@Param("chapter") Integer chapter);

}
