package com.db.imas.dao;

import com.db.imas.model.dto.ImasIdolDTO;
import com.db.imas.model.dto.MangaIdolListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/8/12 16:09
 * @Version 1.0
 */
@Mapper
public interface ImasIdolDao {

    List<ImasIdolDTO> setBirthdayIdol(@Param("birthday")Date birthday);

    List<MangaIdolListDTO> getMangaIdolList(@Param("production")int production);

    void updateMangaIdol(@Param("mid")Integer mid,@Param("debutIdol")String debugIdol);

    String selectDebutIdol(@Param("mid")Integer mid);

    List<MangaIdolListDTO> getMangaDebutIdolList(@Param("debutIdols")List<String> debutIdols);
}
