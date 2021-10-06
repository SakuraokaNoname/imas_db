package com.db.imas.service.impl;

import com.alibaba.fastjson.JSON;
import com.db.imas.constans.Production;
import com.db.imas.dao.ImasIdolDao;
import com.db.imas.model.dto.ImasIdolDTO;
import com.db.imas.model.dto.MangaIdolListDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.service.ImasIdolService;
import com.db.imas.utils.RedisUtil;
import com.db.imas.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @Override
    public ResultDTO<List<MangaIdolListDTO>> getCinderellaIdolList() {
        return ResultDTO.success(imasIdolDao.getMangaIdolList(Production.IMAS_346.getId()));
    }

    @Override
    public void addMangaIdol(HttpServletRequest request, Integer mid, Integer idolId) {
        String token = request.getHeader("token");
        if(!redisUtil.checkUserTokenIsAdmin(token)){
            return;
        }
        String debutIdols = imasIdolDao.selectDebutIdol(mid);
        String debutIdolStr = idolId + ",";
        // TODO debutIdols前要加一个逗号,indexOf判断时需要使用 "," + idolId + "," 来判断
        if(!StringUtils.isEmpty(debutIdols)){
            if(debutIdols.indexOf(debutIdolStr) == -1){
                imasIdolDao.addMangaIdol(mid,debutIdols + debutIdolStr);
            }
            return;
        }
        imasIdolDao.addMangaIdol(mid,debutIdolStr);
    }

    @Override
    public ResultDTO<List<MangaIdolListDTO>> getMangaDebutIdolList(Integer mid) {
        String debutIdols = imasIdolDao.selectDebutIdol(mid);
        List<MangaIdolListDTO> dtos = new ArrayList<>();
        if(!StringUtils.isEmpty(debutIdols)){
            List<String> idols = new ArrayList<>();
            for(String str:debutIdols.split(",")){
                idols.add(str);
            }
            dtos = imasIdolDao.getMangaDebutIdolList(idols);
        }
        return ResultDTO.success(dtos);
    }
}
