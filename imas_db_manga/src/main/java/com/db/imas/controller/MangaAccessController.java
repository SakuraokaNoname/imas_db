package com.db.imas.controller;

import com.db.imas.model.dto.ImasAccessIPDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.entity.ImasAccessIP;
import com.db.imas.service.MangaAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/10/5 14:39
 * @Version 1.0
 */
@RestController
@RequestMapping("api/access")
public class MangaAccessController {

    @Autowired
    private MangaAccessService mangaAccessService;

    @GetMapping("check")
    public ResultDTO checkAccessUser(HttpServletRequest request){
        return mangaAccessService.checkAccessUser(request);
    }

    @PostMapping({"list/{isBlock}","list"})
    public ResultDTO<List<ImasAccessIPDTO>> getAccessList(HttpServletRequest request, @PathVariable(required = false) String isBlock){
        return mangaAccessService.selectAccessIP(request,isBlock);
    }

}
