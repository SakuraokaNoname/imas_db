package com.db.imas.controller;

import com.db.imas.model.dto.MangaQueryUserDTO;
import com.db.imas.model.dto.MangaUserDTO;
import com.db.imas.model.dto.MangaUserIconDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddUserVO;
import com.db.imas.model.vo.MangaLoginVO;
import com.db.imas.model.vo.MangaUpdateUserVO;
import com.db.imas.service.MangaUserService;
import com.db.imas.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/7/23 16:03
 * @Version 1.0
 */
@RestController
@RequestMapping("api/user")
public class MangaUserController {

    @Autowired
    private MangaUserService mangaUserService;

    @PostMapping("login")
    public ResultDTO<MangaUserDTO> userLogin(HttpServletRequest request, @RequestBody MangaLoginVO vo){
        vo.setLoginIP(IPUtil.getIp2(request));
        return mangaUserService.userLogin(vo);
    }

    @PostMapping("register")
    public ResultDTO<String> userRegister(HttpServletRequest request, @RequestBody MangaAddUserVO vo){
        vo.setCreateIP(IPUtil.getIp2(request));
        return mangaUserService.userRegister(vo);
    }

    @PostMapping("update")
    public ResultDTO<MangaUserDTO> userUpdate(HttpServletRequest request,@RequestBody MangaUpdateUserVO vo){
        return mangaUserService.userUpdate(request,vo);
    }

    @PostMapping("token")
    @ResponseBody
    public ResultDTO checkUserToken(HttpServletRequest request){
        return mangaUserService.checkUserTokenDTO(request);
    }

    @PostMapping("logout")
    public ResultDTO userLogout(HttpServletRequest request){
        return mangaUserService.userLogout(request);
    }

    @GetMapping("icon")
    public ResultDTO<MangaUserIconDTO> userIconList(){
        return mangaUserService.userIconList();
    }

    @PostMapping("admin")
    public ResultDTO checkUserIsAdmin(HttpServletRequest request){
        return mangaUserService.checkUserTokenIsAdmin(request);
    }

    @PostMapping("list")
    public ResultDTO<List<MangaQueryUserDTO>> selectUserList(HttpServletRequest request){
        return mangaUserService.selectUserList(request);
    }
}
