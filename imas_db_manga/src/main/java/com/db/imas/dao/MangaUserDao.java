package com.db.imas.dao;

import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author noname
 * @Date 2021/7/23 17:25
 * @Version 1.0
 */
@Mapper
public interface MangaUserDao {

    MangaUserDTO userLogin(MangaLoginVO vo);

    Integer userRegister(MangaAddUserVO vo);

    Integer checkUserByOne(String loginId);
}
