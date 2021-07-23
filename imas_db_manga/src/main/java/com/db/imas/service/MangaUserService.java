package com.db.imas.service;

import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;

/**
 * @Author noname
 * @Date 2021/7/23 17:24
 * @Version 1.0
 */
public interface MangaUserService {

    ResultDTO<String> userLogin(MangaLoginVO vo);

    ResultDTO<String> userRegister(MangaAddUserVO vo);
}
