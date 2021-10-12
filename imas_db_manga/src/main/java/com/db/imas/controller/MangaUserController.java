package com.db.imas.controller;

import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.MangaUserIconDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;
import com.db.imas.model.vo.MangaUpdateUserVO;
import com.db.imas.service.MangaUserService;
import com.db.imas.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultDTO<MangaUserDTO> userLogin(HttpServletRequest request, @RequestBody MangaLoginVO vo){
        vo.setLoginIP(IPUtil.getIp2(request));
        return mangaUserService.userLogin(vo);
    }

    @PostMapping("register")
    @ResponseBody
    public ResultDTO<String> userRegister(HttpServletRequest request, @RequestBody MangaAddUserVO vo){
        vo.setCreateIP(IPUtil.getIp2(request));
        return mangaUserService.userRegister(vo);
    }

    @PostMapping("update")
    @ResponseBody
    public ResultDTO<MangaUserDTO> userUpdate(HttpServletRequest request,@RequestBody MangaUpdateUserVO vo){
        return mangaUserService.userUpdate(request,vo);
    }

    @PostMapping("token")
    @ResponseBody
    public ResultDTO checkUserToken(HttpServletRequest request){
        return mangaUserService.checkUserTokenDTO(request);
    }

    @PostMapping("logout")
    @ResponseBody
    public ResultDTO userLogout(HttpServletRequest request){
        return mangaUserService.userLogout(request);
    }

    @GetMapping("icon")
    @ResponseBody
    public ResultDTO<MangaUserIconDTO> userIconList(){
        return mangaUserService.userIconList();
    }

    @PostMapping("admin")
    public ResultDTO checkUserIsAdmin(HttpServletRequest request){
        return mangaUserService.checkUserTokenIsAdmin(request);
    }
}
