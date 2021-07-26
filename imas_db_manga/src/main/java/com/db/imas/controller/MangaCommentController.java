package com.db.imas.controller;

import com.db.imas.model.dto.MangaCommentListDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddCommentVO;
import com.db.imas.service.MangaCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author noname
 * @create 2021/7/26
 */
@RestController
@RequestMapping("comment")
public class MangaCommentController {

    @Autowired
    private MangaCommentService mangaCommentService;

    @PostMapping("add")
    @ResponseBody
    public ResultDTO addComment(HttpServletRequest request, @RequestBody MangaAddCommentVO vo){
        return mangaCommentService.addComment(request,vo);
    }

    @GetMapping("list/{mid}")
    @ResponseBody
    public ResultDTO<List<MangaCommentListDTO>> getCommentList(@PathVariable Integer mid){
        return mangaCommentService.getCommentList(mid);
    }

    @GetMapping("del/{id}")
    @ResponseBody
    public ResultDTO deleteComment(HttpServletRequest request,@PathVariable int id){
        return mangaCommentService.deleteComment(request,id);
    }
}
