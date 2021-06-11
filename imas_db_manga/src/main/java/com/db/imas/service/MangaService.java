package com.db.imas.service;

import com.db.imas.model.dto.MangaDTO;
import com.db.imas.model.dto.ResultDTO;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/6/11 16:02
 * @Version 1.0
 */
public interface MangaService {

    ResultDTO<List<MangaDTO>> getMangaList();

}
