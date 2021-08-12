package com.db.imas.controller;

import com.db.imas.model.dto.ImasIdolDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.service.ImasIdolService;
import com.db.imas.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/8/12 16:15
 * @Version 1.0
 */
@RestController
public class ImasIdolController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ImasIdolService imasIdolService;

    @RequestMapping("birthdayList")
    public ResultDTO<List<ImasIdolDTO>> selectBirthdayIdol(){
        return imasIdolService.selectBirthdayIdol();
    }

    @RequestMapping("changeBirthdayIdol")
    public ResultDTO changeBirthdayIdol(HttpServletRequest request){
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            return ResultDTO.fail();
        }
        if(!redisUtil.hasKey(token)){
            return ResultDTO.fail();
        }
        imasIdolService.changeBirthdayIdol();
        return ResultDTO.success();
    }

}
