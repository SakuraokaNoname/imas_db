package com.db.imas.service;

import com.db.imas.model.dto.*;
import com.db.imas.model.vo.MangaAddMangaDetailVO;
import com.db.imas.model.vo.MangaSearchMangaSubVO;
import com.db.imas.model.vo.UploadParamsVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/6/11 16:02
 * @Version 1.0
 */
public interface MangaService {

    ResultDTO<List<MangaDTO>> getMangaList();

    ResultDTO<List<MangaSubDTO>> getMangaSubList(Integer id);

    ResultDTO<MangaDetailDTO> getMangaDetail(Integer id);

    ResultDTO changeChapter(Integer mid, Integer orderId, String type);

    Integer pageTurning(Integer mid, Integer orderId, int type);

    UploadDTO upload(String fileName, MultipartFile[] pics);

    ResultDTO<List<UploadParamsDTO>> getMidAndSidList(HttpServletRequest request);

    ResultDTO setUploadParams(HttpServletRequest request, UploadParamsVO dto);

    ResultDTO addManga(HttpServletRequest request, MangaAddMangaDetailVO mangaDetail) throws NullPointerException;

    ResultDTO<List<MangaSubSearchDTO>> searchManga(MangaSearchMangaSubVO vo);

    int synOSSPicture();

    ResultDTO<Integer> synOSSPictureDTO(HttpServletRequest request);

}
