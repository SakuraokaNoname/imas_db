package com.db.imas.controller;

import com.db.imas.model.dto.ResultDTO;
import com.db.imas.service.MangaAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

}
