package com.db.imas.dao;

import com.db.imas.model.entity.ImasIP;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author noname
 * @Date 2021/10/5 16:53
 * @Version 1.0
 */
@Mapper
public interface ImasIpDao {

    Integer insertIpInfo(ImasIP ip);

}
