package com.db.imas.service;

import com.db.imas.constans.NoticeEnum;
import com.db.imas.model.dto.MangaNoticeDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaNoticeVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/12/31 16:01
 * @Version 1.0
 */
public interface MangaNoticeService {

    List<MangaNoticeDTO> getMangaNoticeList();

    ResultDTO addNotice(HttpServletRequest request,MangaNoticeVO vo);

}
