package com.db.imas.service;

import com.db.imas.model.dto.MangaQueryUserDTO;
import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.MangaUserIconDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;
import com.db.imas.model.vo.MangaUpdateUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/7/23 17:24
 * @Version 1.0
 */
public interface MangaUserService {

    ResultDTO<MangaUserDTO> userLogin(MangaLoginVO vo);

    ResultDTO<String> userRegister(MangaAddUserVO vo);

    ResultDTO<MangaUserDTO> userUpdate(HttpServletRequest request,MangaUpdateUserVO vo);

    boolean checkUserToken(HttpServletRequest request);

    ResultDTO checkUserTokenDTO(HttpServletRequest request);

    ResultDTO checkUserTokenIsAdmin(HttpServletRequest request);

    MangaUserDTO getUserByToken(HttpServletRequest request);

    boolean checkUserByOne(String username);

    ResultDTO userLogout(HttpServletRequest request);

    ResultDTO<MangaUserIconDTO> userIconList();

    void removeCurrentUserToken(int id);

    ResultDTO<List<MangaQueryUserDTO>> selectUserList(HttpServletRequest request);
}
