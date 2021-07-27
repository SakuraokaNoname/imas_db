package com.db.imas.service.impl;

import com.db.imas.constans.ErrorCode;
import com.db.imas.dao.MangaCommentDao;
import com.db.imas.model.dto.MangaCommentListDTO;
import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddCommentVO;
import com.db.imas.service.MangaCommentService;
import com.db.imas.service.MangaUserService;
import com.db.imas.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author noname
 * @create 2021/7/26
 */
@Service
public class MangaCommentServiceImpl implements MangaCommentService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MangaCommentDao mangaCommentDao;

    @Autowired
    private MangaUserService mangaUserService;

    @Override
    public ResultDTO addComment(HttpServletRequest request, MangaAddCommentVO vo) {
        mangaUserService.checkUserTokenDTO(request);
        String token = request.getHeader("token");
        vo.setCreateTime(new Date());
        vo.setUpdateTime(new Date());
        return ResultDTO.success(mangaCommentDao.addComment(vo) > 0);
    }

    @Override
    public ResultDTO<List<MangaCommentListDTO>> getCommentList(Integer mid) {
        return ResultDTO.success(mangaCommentDao.getCommentList(mid,0));
    }

    @Override
    public ResultDTO deleteComment(HttpServletRequest request,Integer id) {
        mangaUserService.checkUserTokenDTO(request);
        String token = request.getHeader("token");
        MangaUserDTO user = redisUtil.getObj(token, MangaUserDTO.class);
        System.out.println(user.getId());
        if(user.getId() != 1){
            return ResultDTO.fail(ErrorCode.TOKEN_EXPIRE.getCode(),ErrorCode.TOKEN_EXPIRE.getMessage());
        }
        mangaCommentDao.deleteComment(id);
        return ResultDTO.success();
    }
}
