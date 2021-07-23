package com.db.imas.service.impl;

import com.db.imas.constans.ErrorCode;
import com.db.imas.dao.MangaUserDao;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;
import com.db.imas.service.MangaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author noname
 * @Date 2021/7/23 17:24
 * @Version 1.0
 */
@Service
public class MangaUserServiceImpl implements MangaUserService {

    @Autowired
    private MangaUserDao mangaUserDao;

    @Override
    public ResultDTO<String> userLogin(MangaLoginVO vo) {
        if(!(mangaUserDao.userLogin(vo) > 0)){
            return ResultDTO.fail(ErrorCode.LOGIN_ERROR.getCode(),ErrorCode.LOGIN_ERROR.getMessage());
        }
        //TODO 创建token并返回
        return null;
    }

    @Override
    public ResultDTO<String> userRegister(MangaAddUserVO vo) {
        vo.setCreateTime(new Date());
        vo.setIcon("default.jpg");
        vo.setName("制作人" + "随机6位");
        vo.setPermission(0);
        if(!(mangaUserDao.userRegister(vo) > 0)){
            return ResultDTO.fail(ErrorCode.REGISTER_ERROR.getCode(),ErrorCode.REGISTER_ERROR.getMessage());
        }
        return ResultDTO.success();
    }

}
