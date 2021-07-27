package com.db.imas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.db.imas.constans.ErrorCode;
import com.db.imas.dao.MangaUserDao;
import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.MangaUserIconDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;
import com.db.imas.model.vo.MangaUpdateUserVO;
import com.db.imas.service.MangaUserService;
import com.db.imas.utils.Constants;
import com.db.imas.utils.MD5Util;
import com.db.imas.utils.RedisUtil;
import com.db.imas.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author noname
 * @Date 2021/7/23 17:24
 * @Version 1.0
 */
@Service
public class MangaUserServiceImpl implements MangaUserService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MangaUserDao mangaUserDao;

    @Override
    public ResultDTO<MangaUserDTO> userLogin(MangaLoginVO vo) {
        vo.setPassword(MD5Util.getMd5(vo.getPassword(),16));
        MangaUserDTO dto = mangaUserDao.userLogin(vo);
        if(ObjectUtils.isEmpty(dto)){
            return ResultDTO.fail(ErrorCode.LOGIN_ERROR.getCode(),ErrorCode.LOGIN_ERROR.getMessage());
        }
        //TODO 创建token并返回
        String token = TokenUtil.createToken(Constants.USER_TOKEN,dto.getId());
        redisUtil.putRaw(token, JSONObject.toJSONString(dto), Constants.TOKEN_EXPIRE);
        dto.setToken(token);
        return ResultDTO.success(dto);
    }

    @Override
    public ResultDTO<String> userRegister(MangaAddUserVO vo) {
        if(checkUserByOne(vo.getLoginId())){
            return ResultDTO.fail(ErrorCode.USER_NO_ONE.getCode(),ErrorCode.USER_NO_ONE.getMessage());
        }
        vo.setCreateTime(new Date());
        vo.setIcon("user_default.gif");
        vo.setName("制作人" + MD5Util.getRandomCode());
        vo.setPassword(MD5Util.getMd5(vo.getPassword(),16));
        vo.setPermission(0);
        if(!(mangaUserDao.userRegister(vo) > 0)){
            return ResultDTO.fail(ErrorCode.REGISTER_ERROR.getCode(),ErrorCode.REGISTER_ERROR.getMessage());
        }
        return ResultDTO.success();
    }

    @Override
    public ResultDTO<MangaUserDTO> userUpdate(HttpServletRequest request,MangaUpdateUserVO vo) {
        checkUserTokenDTO(request);
        String token = request.getHeader("token");
        if(!(mangaUserDao.userUpdate(vo) > 0)){
            return ResultDTO.fail(ErrorCode.USER_UPDATE_FAIL.getCode(),ErrorCode.USER_UPDATE_FAIL.getMessage());
        }
        MangaUserDTO dto = new MangaUserDTO();
        dto.setId(vo.getId());
        dto.setName(vo.getName());
        dto.setIcon(vo.getIcon());
        dto.setToken(token);
        return ResultDTO.success(dto);
    }

    @Override
    public boolean checkUserToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return !StringUtils.isEmpty(token) && redisUtil.hasKey(token);
    }

    @Override
    public ResultDTO checkUserTokenDTO(HttpServletRequest request) {
        if(!checkUserToken(request)){
            return ResultDTO.fail(ErrorCode.TOKEN_EXPIRE.getCode(),ErrorCode.TOKEN_EXPIRE.getMessage());
        }
        return ResultDTO.success(true);
    }

    @Override
    public MangaUserDTO getUserByToken(HttpServletRequest request) {
        return redisUtil.getObj(request.getHeader("token"),MangaUserDTO.class);
    }

    @Override
    public boolean checkUserByOne(String loginId) {
        return mangaUserDao.checkUserByOne(loginId) > 0;
    }

    @Override
    public ResultDTO userLogout(HttpServletRequest request) {
        checkUserTokenDTO(request);
        String token = request.getHeader("token");
        redisUtil.del(token);
        return ResultDTO.success();
    }

    @Override
    public ResultDTO<MangaUserIconDTO> userIconList() {
        return ResultDTO.success(mangaUserDao.userIconList());
    }

}
