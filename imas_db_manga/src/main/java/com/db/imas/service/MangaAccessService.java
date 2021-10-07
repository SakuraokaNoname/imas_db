package com.db.imas.service;

import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.entity.ImasIP;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author noname
 * @Date 2021/10/5 14:47
 * @Version 1.0
 */
public interface MangaAccessService {

    ResultDTO checkAccessUser(HttpServletRequest request);

    Integer addIpInfo(ImasIP ip);


}
