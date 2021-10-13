package com.db.imas.dao;

import com.db.imas.model.dto.*;
import com.db.imas.model.vo.MangaAddMangaDetailVO;
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

    List<MangaDebutIdol> getMangaSubDebutIdol();

    List<MangaSubSearchDTO> searchMangaSubList(@Param("detailId")List<String> detailId);

    MangaDetailDTO getMangaDetail(@Param("id")Integer id);

    Integer changeChapter(@Param("mid") Integer mid,@Param("chapter") Integer chapter);

    List<UploadParamsDTO> getMidAndSidList();

    Integer addMangaDetail(MangaAddMangaDetailVO vo);

    Integer addMangaPicture(MangaAddMangaDetailVO vo);

    List<MangaPictureDownloadDTO> selectMangaPicture();
}
