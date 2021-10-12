package com.db.imas.service.impl;

import com.db.imas.constans.ErrorCode;
import com.db.imas.dao.ImasIpDao;
import com.db.imas.model.dto.ImasAccessIPDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.entity.ImasAccessIP;
import com.db.imas.model.entity.ImasIP;
import com.db.imas.service.MangaAccessService;
import com.db.imas.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/10/5 14:48
 * @Version 1.0
 */
@Service
public class MangaAccessServiceImpl implements MangaAccessService {

    @Autowired
    private ImasIpDao imasIpDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultDTO checkAccessUser(HttpServletRequest request) {
        String ip = IPUtil.getIpAddr(request);
        if(StringUtils.isEmpty(ip)){
            return ResultDTO.fail(ErrorCode.ACCESS_LIMIT.getCode(),ErrorCode.ACCESS_LIMIT.getMessage());
        }

        if("0:0:0:0:0:0:0:1".equals(ip)){
            // TODO 本地访问不记录redis
            return ResultDTO.success(true);
        }

        String now = DateUtil.getNow();
        if(IPBlockList.isBlockIP(ip)){
            // TODO 记录访问IP
            redisUtil.putRaw(Constants.ACCESS_ERROR_PREFIX + now,ip,Constants.TOKEN_EXPIRE);
            return ResultDTO.fail(ErrorCode.ACCESS_LIMIT.getCode(),ErrorCode.ACCESS_LIMIT.getMessage());
        }

        redisUtil.putRaw(Constants.ACCESS_PREFIX + now,ip,Constants.TOKEN_EXPIRE);
        return ResultDTO.success(true);
    }

    @Override
    public Integer addIpInfo(ImasIP ip) {
        return imasIpDao.insertIpInfo(ip);
    }

    @Override
    public boolean insertIPData(ImasAccessIP accessIP) {
        return imasIpDao.insertAccessIP(accessIP) == 1;
    }

    @Override
    public List<ImasIP> selectPrefixIP(String ip) {
        return imasIpDao.selectPrefixIP(ip);
    }

    @Override
    public ResultDTO<List<ImasAccessIPDTO>> selectAccessIP(HttpServletRequest request, String isBlock) {
        String token = request.getHeader("token");
        if(!redisUtil.checkUserTokenIsAdmin(token)){
            return ResultDTO.fail(ErrorCode.PERMISSION_FAIL.getCode(),ErrorCode.PERMISSION_FAIL.getMessage());
        }
        return ResultDTO.success(imasIpDao.selectAccessIP(isBlock));
    }

}
