package com.db.imas.service.impl;

import com.alibaba.fastjson.JSON;
import com.db.imas.dao.ImasIdolDao;
import com.db.imas.model.dto.ImasIdolDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.service.ImasIdolService;
import com.db.imas.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/8/12 15:55
 * @Version 1.0
 */
@Service
public class ImasIdolServiceImpl implements ImasIdolService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ImasIdolDao imasIdolDao;

    @Override
    public ResultDTO<List<ImasIdolDTO>> selectBirthdayIdol() {
        return ResultDTO.success(redisUtil.getObjList("BIRTHDAY:",ImasIdolDTO.class));
    }

    @Override
    public void changeBirthdayIdol() {
        List<ImasIdolDTO> birthdayIdol = imasIdolDao.setBirthdayIdol(new Date());
        redisUtil.putRaw("BIRTHDAY:", JSON.toJSONString(birthdayIdol));
    }
}
