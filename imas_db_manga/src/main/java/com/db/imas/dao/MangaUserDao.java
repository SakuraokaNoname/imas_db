package com.db.imas.dao;

import com.db.imas.model.dto.MangaQueryUserDTO;
import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.MangaUserIconDTO;
import com.db.imas.model.entity.MangaUser;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;
import com.db.imas.model.vo.MangaUpdateUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/7/23 17:25
 * @Version 1.0
 */
@Mapper
public interface MangaUserDao {

    MangaUserDTO userLogin(MangaLoginVO vo);

    Integer userRegister(MangaAddUserVO vo);

    Integer userUpdate(MangaUpdateUserVO vo);

    Integer checkUserByOne(String loginId);

    Integer checkUserNameByOne(@Param("userName") String userName);

    List<MangaUserIconDTO> userIconList();

    void UpdateLoginIP(String ip);

    List<MangaQueryUserDTO> selectUserList();

    MangaUser getUser(String loginId);
}
