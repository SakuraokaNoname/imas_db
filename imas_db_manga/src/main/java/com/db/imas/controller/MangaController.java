package com.db.imas.controller;

import com.db.imas.model.dto.MangaDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/6/11 16:29
 * @Version 1.0
 */
@RestController
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @RequestMapping("list")
    public ResultDTO<List<MangaDTO>> getMangaList(){
        return mangaService.getMangaList();
    }

}
