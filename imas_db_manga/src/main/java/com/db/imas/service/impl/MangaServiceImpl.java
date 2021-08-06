package com.db.imas.service.impl;

import com.db.imas.constans.ErrorCode;
import com.db.imas.constans.MangaType;
import com.db.imas.dao.MangaDao;
import com.db.imas.model.dto.*;
import com.db.imas.model.vo.MangaAddMangaDetailVO;
import com.db.imas.model.vo.UploadParamsVO;
import com.db.imas.service.MangaService;
import com.db.imas.service.MangaUserService;
import com.db.imas.utils.Constants;
import com.db.imas.utils.OSSUtil;
import com.db.imas.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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
    @Transactional(rollbackFor = Exception.class)
    public ResultDTO addManga(HttpServletRequest request, MangaAddMangaDetailVO mangaDetail) throws NullPointerException {
        mangaUserService.checkUserTokenDTO(request);
        String token = request.getHeader("token");
        MangaUserDTO user = redisUtil.getObj(token, MangaUserDTO.class);
        if(user.getId() != 1){
            return ResultDTO.fail(ErrorCode.PERMISSION_FAIL.getCode(), ErrorCode.PERMISSION_FAIL.getMessage());
        }
        if(ObjectUtils.isEmpty(mangaDetail)){
            return ResultDTO.fail(ErrorCode.UPLOAD_NOT_PARAMS.getCode(),ErrorCode.UPLOAD_NOT_PARAMS.getMessage());
        }
        mangaDetail.setUpdateTime(new Date());
        Integer result1 = mangaDao.addMangaDetail(mangaDetail);
        Integer result2 = mangaDao.addMangaPicture(mangaDetail);
        System.out.println(mangaDetail.getPics().toString());
        if(result1 < 1 || result2 < 1){
            throw new NullPointerException();
        }
        return ResultDTO.success();
    }

    @Override
    public UploadDTO upload(String fileName, MultipartFile[] pics) {
        //OSS会自动根据路径创建不存在的文件夹
        String mid = redisUtil.getRaw(Constants.UPLOAD_MANGAID_TOKEN);
        String sid = redisUtil.getRaw(Constants.UPLOAD_SUBID_TOKEN);
        if(StringUtils.isEmpty(mid) || StringUtils.isEmpty(sid)){
            return UploadDTO.error();
        }
        String uploadPath = Constants.UPLOAD_BASIC_URL + MangaType.getMangaType(Integer.parseInt(mid)) + "/" + sid + "/" + fileName;
        if(ObjectUtils.isEmpty(pics) || pics.length < 1){
            return UploadDTO.error();
        }

        for (MultipartFile pic : pics) {
            //判断是否有文件且是否为图片文件
            if(!StringUtils.isEmpty(fileName) && !"".equalsIgnoreCase(fileName.trim())) {
                try{
                    ossUtil.upload(uploadPath,pic.getInputStream());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return UploadDTO.error();
                }
            }
        }
        return UploadDTO.success(uploadPath);
    }

    @Override
    public ResultDTO<List<UploadParamsDTO>> getMidAndSidList(HttpServletRequest request) {
        mangaUserService.checkUserTokenDTO(request);
        String token = request.getHeader("token");
        MangaUserDTO user = redisUtil.getObj(token, MangaUserDTO.class);
        if(user.getId() != 1){
            return ResultDTO.fail(ErrorCode.PERMISSION_FAIL.getCode(), ErrorCode.PERMISSION_FAIL.getMessage());
        }
        List<UploadParamsDTO> dto = mangaDao.getMidAndSidList();
        return ResultDTO.success(dto);
    }

    @Override
    public ResultDTO setUploadParams(HttpServletRequest request , UploadParamsVO vo) {
        mangaUserService.checkUserTokenDTO(request);
        String token = request.getHeader("token");
        MangaUserDTO user = redisUtil.getObj(token, MangaUserDTO.class);
        if(user.getId() != 1){
            return ResultDTO.fail(ErrorCode.PERMISSION_FAIL.getCode(), ErrorCode.PERMISSION_FAIL.getMessage());
        }
        if(vo.getMid() == null || vo.getSid() == null){
            return ResultDTO.fail(ErrorCode.UPLOAD_NOT_PARAMS.getCode(),ErrorCode.UPLOAD_NOT_PARAMS.getMessage());
        }
        redisUtil.putRaw(Constants.UPLOAD_MANGAID_TOKEN,vo.getMid().toString(),3600);
        redisUtil.putRaw(Constants.UPLOAD_SUBID_TOKEN,vo.getSid().toString(),3600);
        return ResultDTO.success();
    }

}
