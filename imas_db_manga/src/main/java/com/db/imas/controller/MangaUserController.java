package com.db.imas.controller;

import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;
import com.db.imas.service.MangaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author noname
 * @Date 2021/7/23 16:03
 * @Version 1.0
 */
@RestController
public class MangaUserController {

    @Autowired
    private MangaUserService mangaUserService;

    @PostMapping("login")
    @ResponseBody
    public ResultDTO<MangaUserDTO> userLogin(@RequestBody MangaLoginVO vo){
        return mangaUserService.userLogin(vo);
    }

    @PostMapping("register")
    @ResponseBody
    public ResultDTO<String> userRegister(@RequestBody MangaAddUserVO vo){
        return mangaUserService.userRegister(vo);
    }

    @PostMapping("token")
    @ResponseBody
    public ResultDTO checkUserToken(HttpServletRequest request){
        return mangaUserService.checkUserToken(request);
    }
}
