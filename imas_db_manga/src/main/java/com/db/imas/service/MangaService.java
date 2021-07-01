package com.db.imas.service;

import com.db.imas.model.dto.MangaDTO;
import com.db.imas.model.dto.MangaDetailDTO;
import com.db.imas.model.dto.MangaSubDTO;
import com.db.imas.model.dto.ResultDTO;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/6/11 16:02
 * @Version 1.0
 */
public interface MangaService {

    ResultDTO<List<MangaDTO>> getMangaList();

    ResultDTO<List<MangaSubDTO>> getMangaSubList(Integer id);

    ResultDTO<MangaDetailDTO> getMangaDetail(Integer id);

    ResultDTO changeChapter(Integer mid,Integer chapter);
}
