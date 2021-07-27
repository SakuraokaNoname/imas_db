package com.db.imas.service.impl;

import com.db.imas.constans.ErrorCode;
import com.db.imas.constans.MangaType;
import com.db.imas.dao.MangaDao;
import com.db.imas.model.dto.*;
import com.db.imas.model.vo.MangaAddMangaDetailVO;
import com.db.imas.service.MangaService;
import com.db.imas.service.MangaUserService;
import com.db.imas.utils.Constants;
import com.db.imas.utils.OSSUtil;
import com.db.imas.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/6/11 16:12
 * @Version 1.0
 */
@Service
public class MangaServiceImpl implements MangaService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private OSSUtil ossUtil;

    @Autowired
    private MangaDao mangaDao;

    @Autowired
    private MangaUserService mangaUserService;

    @Override
    public ResultDTO<List<MangaDTO>> getMangaList() {
        return ResultDTO.success(mangaDao.getMangaList());
    }

    @Override
    public ResultDTO<List<MangaSubDTO>> getMangaSubList(Integer id) {
        return ResultDTO.success(mangaDao.getMangaSubList(id));
    }

    @Override
    public ResultDTO<MangaDetailDTO> getMangaDetail(Integer id) {
        return ResultDTO.success(mangaDao.getMangaDetail(id));
    }

    @Override
    public ResultDTO changeChapter(Integer mid, Integer chapter) {
        return ResultDTO.success(mangaDao.changeChapter(mid,chapter));
    }

    @Override
    public ResultDTO<List<String>> uploadPics(HttpServletRequest request, MangaAddMangaDetailVO mangaDetail, MultipartFile[] pics) {
        mangaUserService.checkUserTokenDTO(request);
        String token = request.getHeader("token");
        MangaUserDTO user = redisUtil.getObj(token, MangaUserDTO.class);
        if(user.getId() != 1){
            ResultDTO.fail(ErrorCode.PERMISSION_FAIL.getCode(), ErrorCode.PERMISSION_FAIL.getMessage());
        }
        System.out.println(mangaDetail.getMid());
        //TODO 根据mid查询detail的最大id+1作为插入的id
        //TODO OSS会自动根据路径创建不存在的文件夹
        //String uploadPath = Constants.UPLOAD_BASIC_URL + MangaType.getMangaType(mangaDetail.getMid()) + "/";
        String uploadTestPath = Constants.UPLOAD_BASIC_URL + MangaType.getMangaType(mangaDetail.getMid()) + "/999/";
        if(ObjectUtils.isEmpty(pics) || pics.length < 1){
            return ResultDTO.fail(ErrorCode.UPLOAD_ERROR.getCode(),ErrorCode.UPLOAD_ERROR.getMessage());
        }

        List<String> picsName = new ArrayList<String>();
        for (MultipartFile pic : pics) {
            String picName = pic.getOriginalFilename();
            //判断是否有文件且是否为图片文件
            if(!StringUtils.isEmpty(picName) && !"".equalsIgnoreCase(picName.trim())) {
                try{
                    ossUtil.upload(uploadTestPath + picName,pic.getInputStream());
                    picsName.add(picName);
                }catch (Exception e){
                    return ResultDTO.fail();
                }
            }
        }
        return ResultDTO.success(picsName);
    }

}
