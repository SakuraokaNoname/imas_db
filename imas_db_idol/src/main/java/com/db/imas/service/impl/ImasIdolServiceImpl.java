package com.db.imas.service.impl;

import com.alibaba.fastjson.JSON;
import com.db.imas.constans.Production;
import com.db.imas.dao.ImasIdolDao;
import com.db.imas.model.dto.ImasIdolDTO;
import com.db.imas.model.dto.MangaIdolListDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.service.ImasIdolService;
import com.db.imas.util.Constants;
import com.db.imas.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.db.imas.util.Constants.CINDERELLA_IDOL_LIST;

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
        return ResultDTO.success(redisUtil.getObjList(Constants.BIRTHDAY_IDOL_PREFIX,ImasIdolDTO.class));
    }

    @Override
    public void changeBirthdayIdol() {
        List<ImasIdolDTO> birthdayIdol = imasIdolDao.selectBirthdayIdol(new Date());
        redisUtil.putRaw(Constants.BIRTHDAY_IDOL_PREFIX, JSON.toJSONString(birthdayIdol));
    }

    @Override
    public ResultDTO<List<MangaIdolListDTO>> getCinderellaIdolList() {
        List<MangaIdolListDTO> idolList = redisUtil.getObjList(CINDERELLA_IDOL_LIST, MangaIdolListDTO.class);
        if(idolList == null || idolList.size() == 0){
            idolList = imasIdolDao.getMangaIdolList(Production.IMAS_346.getId());
            redisUtil.putRaw(CINDERELLA_IDOL_LIST,JSON.toJSONString(idolList));
        }
        return ResultDTO.success(idolList);
    }

    @Override
    public void addMangaDebutIdol(HttpServletRequest request, Integer mid, Integer idolId) {
        String token = request.getHeader("token");
        if(!redisUtil.checkUserTokenIsAdmin(token)){
            return;
        }
        String debutIdols = imasIdolDao.selectDebutIdol(mid);
        String debutIdolStr = "," + idolId + ",";
        if(!StringUtils.isEmpty(debutIdols)){
            if(("," + debutIdols).indexOf(debutIdolStr) == -1){
                imasIdolDao.updateMangaIdol(mid,debutIdols + idolId + ",");
            }
            return;
        }
        imasIdolDao.updateMangaIdol(mid,debutIdolStr);
    }

    @Override
    public void removeMangaDebutIdol(HttpServletRequest request, Integer mid, Integer idolId) {
        String token = request.getHeader("token");
        if(!redisUtil.checkUserTokenIsAdmin(token)){
            return;
        }
        String debutIdols = imasIdolDao.selectDebutIdol(mid);
        int i = 0;
        for(String idol : debutIdols.split(",")){
            if(!StringUtils.isEmpty(idol)){
                i += i + 1;
            }
        }
        if(i == 1){
            imasIdolDao.updateMangaIdol(mid,"");
            return;
        }
        String removeIdol = "," + idolId + ",";
        imasIdolDao.updateMangaIdol(mid,("," + debutIdols).replace(removeIdol,","));
    }

    @Override
    public ResultDTO<List<MangaIdolListDTO>> getMangaDebutIdolList(Integer mid) {
        String debutIdols = imasIdolDao.selectDebutIdol(mid);
        List<MangaIdolListDTO> idolListDTOList = new ArrayList<>();
        if(!StringUtils.isEmpty(debutIdols)){
            List<String> idols = new ArrayList<>();
            for(String str:debutIdols.split(",")){
                idols.add(str);
            }
            idolListDTOList = imasIdolDao.getMangaDebutIdolList(idols);
        }
        return ResultDTO.success(idolListDTOList);
    }
}
