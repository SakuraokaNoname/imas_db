package com.db.imas.service;

import com.db.imas.model.dto.ImasIdolDTO;
import com.db.imas.model.dto.MangaIdolListDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.utils.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/8/12 15:53
 * @Version 1.0
 */
public interface ImasIdolService {

    ResultDTO<List<ImasIdolDTO>> selectBirthdayIdol();

    void changeBirthdayIdol();

    ResultDTO<List<MangaIdolListDTO>> getCinderellaIdolList();

    void addMangaIdol(HttpServletRequest request,Integer mid,Integer idolId);

    ResultDTO<List<MangaIdolListDTO>> getMangaDebutIdolList(Integer mid);
}
