package com.db.imas.service.impl;

import com.db.imas.dao.MangaDao;
import com.db.imas.model.dto.MangaDTO;
import com.db.imas.model.dto.MangaDetailDTO;
import com.db.imas.model.dto.MangaSubDTO;
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
        return ResultDTO.success(mangaDao.getMangaList());
    }

    @Override
    public ResultDTO<List<MangaSubDTO>> getMangaSubList(Integer id) {
        return ResultDTO.success(mangaDao.getMangaSubList(id));
    }

    @Override
    public ResultDTO<MangaDetailDTO> getMangaDetail(Integer id) {
        return ResultDTO.success(mangaDao.getMangaDetail(id));
    }

    @Override
    public ResultDTO changeChapter(Integer mid, Integer chapter) {
        if(mangaDao.changeChapter(mid,chapter) > 0){
            return ResultDTO.success();
        }
        return ResultDTO.fail();
    }

}
