package com.db.imas.service;

import com.db.imas.model.dto.ImasAccessCountDTO;
import com.db.imas.model.dto.ImasAccessIPDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.entity.ImasAccessIP;
import com.db.imas.model.entity.ImasIP;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/10/5 14:47
 * @Version 1.0
 */
public interface MangaAccessService {

    ResultDTO checkAccessUser(HttpServletRequest request);

    Integer addIpInfo(ImasIP ip);

    boolean insertIPData(ImasAccessIP accessIP);

    List<ImasIP> selectPrefixIP(String ip);

    ResultDTO<List<ImasAccessIPDTO>> selectAccessIP(HttpServletRequest request, String isBlock);

    String getAccessAddr(String ip);

    ResultDTO<ImasAccessCountDTO> selectAccessCount();

    void synAccessCount();
}
