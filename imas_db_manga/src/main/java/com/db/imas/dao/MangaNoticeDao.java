package com.db.imas.dao;

import com.db.imas.model.dto.MangaNoticeDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaNoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/12/31 16:01
 * @Version 1.0
 */
@Mapper
public interface MangaNoticeDao {

    List<MangaNoticeDTO> getMangaNoticeList();

    int addNotice(MangaNoticeVO vo);
}
