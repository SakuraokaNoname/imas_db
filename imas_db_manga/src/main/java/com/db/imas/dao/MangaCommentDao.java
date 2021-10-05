package com.db.imas.dao;

import com.db.imas.model.dto.MangaCommentListDTO;
import com.db.imas.model.entity.MangaCommentLike;
import com.db.imas.model.vo.MangaAddCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author noname
 * @create 2021/7/26
 */
@Mapper
public interface MangaCommentDao {

    Integer addComment(MangaAddCommentVO vo);

    List<MangaCommentListDTO> getCommentList(@Param("mid")Integer mid,@Param("uid")Integer uid, @Param("isDelete")int isDelete);

    void deleteComment(Integer id);

    Integer addLikeComment(MangaCommentLike commentLike);

    String getLikeComment(MangaCommentLike commentLike);

    Integer updateLikeComment(MangaCommentLike commentLike);
}
