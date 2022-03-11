package com.db.imas.service.impl;

import com.alibaba.fastjson.JSON;
import com.db.imas.constans.ErrorCode;
import com.db.imas.constans.MangaType;
import com.db.imas.constans.NoticeEnum;
import com.db.imas.dao.MangaDao;
import com.db.imas.model.dto.*;
import com.db.imas.model.vo.MangaAddMangaDetailVO;
import com.db.imas.model.vo.MangaNoticeVO;
import com.db.imas.model.vo.MangaSearchMangaSubVO;
import com.db.imas.model.vo.UploadParamsVO;
import com.db.imas.service.MangaNoticeService;
import com.db.imas.service.MangaService;
import com.db.imas.service.MangaUserService;
import com.db.imas.util.Constants;
import com.db.imas.util.OSSUtil;
import com.db.imas.util.RedisUtil;
import com.db.imas.util.TemplateReplaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

import static com.db.imas.util.Constants.*;

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

    @Autowired
    private MangaNoticeService mangaNoticeService;

    @Override
    public ResultDTO<List<MangaDTO>> getMangaList() {
        return ResultDTO.success(mangaDao.getMangaList());
    }

    @Override
    public ResultDTO<List<MangaSubDTO>> getMangaSubList(Integer id) {
        String key = MANGA_SUB_LIST_TOKEN + id;
        List<MangaSubDTO> mangaSubList = redisUtil.getObjList(key,MangaSubDTO.class);
        if(mangaSubList == null || mangaSubList.size() == 0){
            mangaSubList = mangaDao.getMangaSubList(id);
            redisUtil.putRaw(key, JSON.toJSONString(mangaSubList), TOKEN_EXPIRE);
        }
        return ResultDTO.success(mangaSubList);
    }

    @Override
    public ResultDTO<MangaDetailDTO> getMangaDetail(Integer id) {
        return ResultDTO.success(mangaDao.getMangaDetail(id));
    }

    @Override
    public ResultDTO changeChapter(Integer mid, Integer orderId, String type) {
        if(orderId == null || (orderId <= 1 && "prev".equals(type))){
            return ResultDTO.fail(ErrorCode.PAGE_TURNING_ERROR.getCode(),ErrorCode.PAGE_TURNING_ERROR.getMessage());
        }

        if(mangaDao.selectMangaDetailMaxPage(mid) == orderId && "next".equals(type)){
            return ResultDTO.fail(ErrorCode.PAGE_TURNING_ERROR.getCode(),ErrorCode.PAGE_TURNING_ERROR.getMessage());
        }
        Integer sid = null;
        if("next".equals(type)){
            sid = pageTurning(mid, orderId, 1);
        }else if("prev".equals(type)){
            sid = pageTurning(mid, orderId, 0);
        }
        return ResultDTO.success(sid);
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
        redisUtil.del(MANGA_SUB_LIST_TOKEN + mangaDetail.getSid());
        mangaDetail.setUpdateTime(new Date());
        Integer result1 = mangaDao.addMangaDetail(mangaDetail);
        Collections.sort(mangaDetail.getPics());
        Integer result2 = mangaDao.addMangaPicture(mangaDetail);
        if(result1 < 1 || result2 < 1){
            throw new NullPointerException();
        }

        MangaNoticeVO noticeVO = new MangaNoticeVO();
        noticeVO.setTitle(NoticeEnum.NEW_MANGA.getTitle());
        noticeVO.setType(NoticeEnum.NEW_MANGA.getType());
        noticeVO.setContent(this.replaceAddMangaNotice(mangaDetail));
        mangaNoticeService.addNotice(request,noticeVO);

        redisUtil.del(Constants.UPLOAD_MANGAID_TOKEN);
        redisUtil.del(Constants.UPLOAD_SUBID_TOKEN);
        redisUtil.putRaw(DELETE_MANGA_SUB_LIST_TOKEN,"",5);
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

    @Override
    public ResultDTO<List<MangaSubSearchDTO>> searchManga(MangaSearchMangaSubVO vo) {
        if(StringUtils.isEmpty(vo.getIdolList()) || ",".equals(vo.getIdolList())){
            return ResultDTO.fail(ErrorCode.SEARCH_PARAM_NULL.getCode(),ErrorCode.SEARCH_PARAM_NULL.getMessage());
        }
        List<String> searchIdol = new ArrayList<>();
        for(String str : vo.getIdolList().split(",")){
            if(!StringUtils.isEmpty(str)){
                searchIdol.add(str);
            }
        }
        List<String> searchMid = new ArrayList<>();
        List<MangaDebutIdol> mangaDebutIdolList = mangaDao.getMangaSubDebutIdol();
        for(MangaDebutIdol debutIdol : mangaDebutIdolList){
            int size = searchIdol.size();
            int flag = 0;
            debutIdol.setDebutIdol("," + debutIdol.getDebutIdol());
            for(String idol : searchIdol){
                if(debutIdol.getDebutIdol().indexOf("," + idol + ",") > -1){
                    flag = flag + 1;
                }
            }
            if(flag == size){
                searchMid.add(debutIdol.getId() + "");
            }
        }
        if(searchMid.size() == 0){
            return ResultDTO.success(new ArrayList<>());
        }
        List<MangaSubSearchDTO> searchResult = mangaDao.searchMangaSubList(searchMid);
        return ResultDTO.success(searchResult);
    }

    @Override
    public int synOSSPicture() {
        List<MangaPictureDownloadDTO> picList = mangaDao.selectMangaPicture();
        int count = 0;
        for(MangaPictureDownloadDTO pic : picList){
            String folderUrl = "/data/manga/" + MangaType.getMangaType(pic.getMid()) + "/" + pic.getSid() + "/";
            String fileName = "manga/" + MangaType.getMangaType(pic.getMid()) + "/" + pic.getSid() + "/" + pic.getImg();
            String path = folderUrl + pic.getImg();
            File folder = new File(folderUrl);
            // 如果路径不存在,则生成路径
            if(!folder.exists()){
                folder.mkdirs();
            }
            // 如果图片不存在,则同步
            if(!new File(path).exists()){
                ossUtil.download(fileName,path);
                count += 1;
            }
        }
        return count;
    }

    @Override
    public ResultDTO<Integer> synOSSPictureDTO(HttpServletRequest request) {
        String token = request.getHeader("token");
        if(!redisUtil.checkUserTokenIsAdmin(token)){
            return ResultDTO.fail(ErrorCode.PERMISSION_FAIL.getCode(),ErrorCode.PERMISSION_FAIL.getMessage());
        }
        return ResultDTO.success(synOSSPicture());
    }

    @Override
    // type [0:上一页] [1:下一页]
    public Integer pageTurning(Integer mid, Integer orderId, int type) {
        if(type == 1){
            Integer sid = mangaDao.selectToMangaDetail(mid, orderId, 1);
            return sid == null ? pageTurning(mid,orderId + 1, 1) : sid;
        }else if(type == 0){
            Integer sid = mangaDao.selectToMangaDetail(mid, orderId, 0);
            return sid == null ? pageTurning(mid,orderId - 1, 0) : sid;
        }
        return null;
    }

    private String replaceAddMangaNotice(MangaAddMangaDetailVO mangaDetail){
        Map<String,String> replaceMap = new HashMap<>();
        replaceMap.put("manga",MangaType.getMangaType(mangaDetail.getMid()));
        replaceMap.put("mangaTitle",mangaDetail.getSubTitle());
        replaceMap.put("dantalions",mangaDetail.getDantalions());
        replaceMap.put("translators",mangaDetail.getTranslators());
        return TemplateReplaceUtil.replace(NoticeEnum.NEW_MANGA.getContent(),replaceMap);
    }

}
