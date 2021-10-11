package com.db.imas.controller;

import com.db.imas.model.dto.ImasAccessIPDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.entity.ImasAccessIP;
import com.db.imas.service.MangaAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/10/5 14:39
 * @Version 1.0
 */
@RestController
public class MangaAccessController {

    @Autowired
    private MangaAccessService mangaAccessService;

    @GetMapping("access")
    public ResultDTO checkAccessUser(HttpServletRequest request){
        return mangaAccessService.checkAccessUser(request);
    }

    @PostMapping("access/list/{isBlock}")
    public ResultDTO<List<ImasAccessIPDTO>> getCommentList(HttpServletRequest request, @PathVariable String isBlock){
        return mangaAccessService.selectAccessIP(request,isBlock);
    }

}
