package com.db.imas.service;

import com.db.imas.model.dto.MangaCommentListDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddCommentVO;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author noname
 * @create 2021/7/26
 */
public interface MangaCommentService {

    ResultDTO addComment(HttpServletRequest request,MangaAddCommentVO vo);

    ResultDTO<List<MangaCommentListDTO>> getCommentList(Integer mid);

    ResultDTO deleteComment(HttpServletRequest request,Integer id);
}
