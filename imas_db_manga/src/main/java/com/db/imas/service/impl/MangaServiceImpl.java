package com.db.imas.service.impl;

import com.db.imas.dao.MangaDao;
import com.db.imas.model.dto.MangaDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/6/11 16:12
 * @Version 1.0
 */
@Service
public class MangaServiceImpl implements MangaService {

    @Autowired
    private MangaDao mangaDao;

    @Override
    public ResultDTO<List<MangaDTO>> getMangaList() {
        List<MangaDTO> result = mangaDao.getMangaList();
        return ResultDTO.success(result);
    }

}
