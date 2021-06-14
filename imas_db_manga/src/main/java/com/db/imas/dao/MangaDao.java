package com.db.imas.dao;

import com.db.imas.model.dto.MangaDTO;
import com.db.imas.model.dto.MangaDetailDTO;
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

    MangaDetailDTO getMangaDetail(@Param("id")Integer id);

}
