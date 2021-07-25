package com.db.imas.service;

import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author noname
 * @Date 2021/7/23 17:24
 * @Version 1.0
 */
public interface MangaUserService {

    ResultDTO<MangaUserDTO> userLogin(MangaLoginVO vo);

    ResultDTO<String> userRegister(MangaAddUserVO vo);

    ResultDTO checkUserToken(HttpServletRequest request);

    MangaUserDTO getUserByToken(HttpServletRequest request);

    boolean checkUserByOne(String username);
}
