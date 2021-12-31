package com.db.imas.service.impl;

import com.db.imas.constans.ErrorCode;
import com.db.imas.dao.MangaNoticeDao;
import com.db.imas.model.dto.MangaNoticeDTO;
import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaNoticeVO;
import com.db.imas.service.MangaNoticeService;
import com.db.imas.service.MangaUserService;
import com.db.imas.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/12/31 16:01
 * @Version 1.0
 */
@Service
public class MangaNoticeServiceImpl implements MangaNoticeService {

    @Autowired
    private MangaNoticeDao mangaNoticeDao;

    @Autowired
    private MangaUserService mangaUserService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<MangaNoticeDTO> getMangaNoticeList() {
        return mangaNoticeDao.getMangaNoticeList();
    }

    @Override
    public ResultDTO addNotice(HttpServletRequest request,MangaNoticeVO vo) {
        mangaUserService.checkUserTokenDTO(request);
        String token = request.getHeader("token");
        MangaUserDTO user = redisUtil.getObj(token, MangaUserDTO.class);
        if(user.getId() != 1){
            return ResultDTO.fail(ErrorCode.PERMISSION_FAIL.getCode(), ErrorCode.PERMISSION_FAIL.getMessage());
        }
        return ResultDTO.success(mangaNoticeDao.addNotice(vo));
    }

}
