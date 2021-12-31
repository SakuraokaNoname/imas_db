package com.db.imas.controller;

import com.db.imas.model.dto.MangaNoticeDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaNoticeVO;
import com.db.imas.service.MangaNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/12/31 16:01
 * @Version 1.0
 */
@RestController
@RequestMapping("api/notice")
public class MangaNoticeController {

    @Autowired
    private MangaNoticeService mangaNoticeService;

    @GetMapping("list")
    public ResultDTO<List<MangaNoticeDTO>> getNoticeList(){
        return ResultDTO.success(mangaNoticeService.getMangaNoticeList());
    }

    @PostMapping("add")
    public ResultDTO addNotice(HttpServletRequest request, @RequestBody MangaNoticeVO vo){
        return mangaNoticeService.addNotice(request,vo);
    }

}
