package com.db.imas.service.impl;

import com.db.imas.constans.ErrorCode;
import com.db.imas.dao.ImasIpDao;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.entity.ImasIP;
import com.db.imas.service.MangaAccessService;
import com.db.imas.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author noname
 * @Date 2021/10/5 14:48
 * @Version 1.0
 */
@Service
public class MangaAccessServiceImpl implements MangaAccessService {

    @Autowired
    private ImasIpDao imasIpDao;

    @Override
    public ResultDTO checkAccessUser(HttpServletRequest request) {
        String ip = IPUtil.getIp2(request);
        if(StringUtils.isEmpty(ip)){
            ResultDTO.fail(ErrorCode.ACCESS_LIMIT.getCode(),ErrorCode.ACCESS_LIMIT.getMessage());
        }

        // TODO 方法返回值有问题
        //IPUtil.getIp2();
        // TODO 将获取到的ip拿去判断是否处于黑名单IP列表中,如果为黑名单ip则禁止访问
        if(!IPUtil.ipIsValid(null,ip)){
            return ResultDTO.fail(ErrorCode.ACCESS_LIMIT.getCode(),ErrorCode.ACCESS_LIMIT.getMessage());
        }

        return ResultDTO.success(true);
    }

    @Override
    public Integer addIpInfo(ImasIP ip) {
        return imasIpDao.insertIpInfo(ip);
    }

}
