package com.db.imas.service;

import com.db.imas.model.dto.MangaEmojiDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaEmojiInfoVO;

/**
 * @Author noname
 * @Date 2022/01/05 16:54
 * @Version 1.0
 */
public interface MangaEmojiService {

    ResultDTO<MangaEmojiDTO> getMangaEmojiList(MangaEmojiInfoVO vo);

}
