package com.db.imas.dao;

import com.db.imas.model.dto.ImasIdolDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/8/12 16:09
 * @Version 1.0
 */
@Mapper
public interface ImasIdolDao {

    List<ImasIdolDTO> setBirthdayIdol(@Param("birthday")Date birthday);

}
