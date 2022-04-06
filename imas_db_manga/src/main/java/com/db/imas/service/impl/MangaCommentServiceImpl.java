package com.db.imas.service.impl;

import com.db.imas.constans.ErrorCode;
import com.db.imas.dao.MangaCommentDao;
import com.db.imas.model.dto.MangaCommentListDTO;
import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.entity.MangaCommentLike;
import com.db.imas.model.vo.MangaAddCommentVO;
import com.db.imas.model.vo.MangaLikeCommentVO;
import com.db.imas.service.MangaCommentService;
import com.db.imas.service.MangaService;
import com.db.imas.service.MangaUserService;
import com.db.imas.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.db.imas.util.Constants.DELETE_MANGA_SUB_LIST_TOKEN;

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

    @Autowired
    private MangaService mangaService;

    @Override
    public ResultDTO addComment(HttpServletRequest request, MangaAddCommentVO vo) {
        mangaUserService.checkUserTokenDTO(request);
        String token = request.getHeader("token");
        MangaUserDTO user = redisUtil.getObj(token, MangaUserDTO.class);
        if(ObjectUtils.isEmpty(user)){
            return ResultDTO.fail(ErrorCode.TOKEN_EXPIRE.getCode(),ErrorCode.TOKEN_EXPIRE.getMessage());
        }
        vo.setCreateTime(new Date());
        vo.setUpdateTime(new Date());
        // 延时删除漫画列表缓存
        int mid = mangaService.getMangaDetailById(vo.getMid());
        redisUtil.putRaw(DELETE_MANGA_SUB_LIST_TOKEN + mid,"",60);
        return ResultDTO.success(mangaCommentDao.addComment(vo) > 0);
    }

    @Override
    public ResultDTO<List<MangaCommentListDTO>> getCommentList(Integer mid,Integer uid) {
        return ResultDTO.success(mangaCommentDao.getCommentList(mid,uid,0));
    }

    @Override
    public ResultDTO deleteComment(HttpServletRequest request,Integer id) {
        mangaUserService.checkUserTokenDTO(request);
        String token = request.getHeader("token");
        MangaUserDTO user = redisUtil.getObj(token, MangaUserDTO.class);
        if(user.getPermission() == 0){
            return ResultDTO.fail(ErrorCode.PERMISSION_FAIL.getCode(),ErrorCode.PERMISSION_FAIL.getMessage());
        }
        mangaCommentDao.deleteComment(id);
        return ResultDTO.success();
    }

    @Override
    public ResultDTO likeComment(HttpServletRequest request, MangaLikeCommentVO vo) {
        String token = request.getHeader("token");
        MangaUserDTO user = redisUtil.getObj(token, MangaUserDTO.class);
        if(ObjectUtils.isEmpty(user)){
            return ResultDTO.fail(ErrorCode.TOKEN_EXPIRE.getCode(),ErrorCode.TOKEN_EXPIRE.getMessage());
        }
        MangaCommentLike commentLike = new MangaCommentLike();
        BeanUtils.copyProperties(vo,commentLike);

        commentLike.setUid(user.getId());
        // 如果没有点赞记录,则增加记录
        // 如果有点赞记录,点赞过的话,软删除点赞记录,如果已经软删除,则取消软删除
        String result = mangaCommentDao.getLikeComment(commentLike);
        if(!StringUtils.isEmpty(result)){
            commentLike.setUpdateTime(new Date());
            if("0".equals(result)){
                commentLike.setIsDelete("1");
                mangaCommentDao.updateLikeComment(commentLike);
            }
            if("1".equals(result)){
                commentLike.setIsDelete("0");
                mangaCommentDao.updateLikeComment(commentLike);
            }
            System.out.println(result);
            System.out.println(commentLike.getIsDelete());
            return ResultDTO.success();
        }
        commentLike.setIsDelete("0");
        commentLike.setCreateTime(new Date());
        commentLike.setUpdateTime(new Date());
        mangaCommentDao.addLikeComment(commentLike);
        return ResultDTO.success();
    }
}
