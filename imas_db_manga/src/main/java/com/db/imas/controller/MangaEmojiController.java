package com.db.imas.controller;

import com.db.imas.model.dto.MangaEmojiDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaEmojiInfoVO;
import com.db.imas.service.MangaEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author noname
 * @Date 2022/01/05 16:54
 * @Version 1.0
 */
@RestController
@RequestMapping("api/emoji")
public class MangaEmojiController {

    @Autowired
    private MangaEmojiService mangaEmojiService;

    @GetMapping("list")
    public ResultDTO<MangaEmojiDTO> getMangaEmojiList(MangaEmojiInfoVO vo){
        return mangaEmojiService.getMangaEmojiList(vo);
    }

}
