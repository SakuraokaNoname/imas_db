package com.db.imas.service.impl;

import com.db.imas.constans.MangaType;
import com.db.imas.dao.MangaEmojiDao;
import com.db.imas.model.dto.MangaEmojiDTO;
import com.db.imas.model.dto.MangaEmojiListDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.entity.MangaEmoji;
import com.db.imas.model.vo.MangaEmojiInfoVO;
import com.db.imas.service.MangaEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.db.imas.utils.Constants.SERVER_URL;

/**
 * @Author noname
 * @Date 2022/01/05 16:54
 * @Version 1.0
 */
@Service
public class MangaEmojiServiceImpl implements MangaEmojiService {

    @Autowired
    private MangaEmojiDao mangaEmojiDao;

    @Override
    public ResultDTO<MangaEmojiDTO> getMangaEmojiList(MangaEmojiInfoVO vo) {
        MangaEmojiDTO dto = new MangaEmojiDTO();
        List<MangaEmojiListDTO> dtoList = new ArrayList<>();
        MangaEmojiListDTO u149 = getEmojiByManga(MangaType.MANGA_U149, vo);
        MangaEmojiListDTO wwg = getEmojiByManga(MangaType.MANGA_WWG, vo);
        dtoList.add(u149);
        dtoList.add(wwg);
        dto.setImgList(dtoList);
        return ResultDTO.success(dto);
    }

    private MangaEmojiListDTO getEmojiByManga(MangaType mangaType,MangaEmojiInfoVO vo){
        List<MangaEmoji> mangaEmojiList = mangaEmojiDao.getMangaEmojiList(mangaType.getId() + "");
        MangaEmojiListDTO emojiListDTO = new MangaEmojiListDTO();
        emojiListDTO.setWidth(vo.getWidth());
        emojiListDTO.setHeight(vo.getHeight());
        emojiListDTO.setIcon(SERVER_URL + mangaEmojiList.get(0).getUrl());
        List<String> emojiList = new ArrayList<>();
        for(MangaEmoji emoji : mangaEmojiList){
            emojiList.add(SERVER_URL + emoji.getUrl());
        }
        emojiListDTO.setList(emojiList);
        return emojiListDTO;
    }
}
