package com.db.imas.service;

import com.db.imas.model.dto.*;
import com.db.imas.model.vo.MangaAddMangaDetailVO;
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

    ResultDTO changeChapter(Integer mid,Integer chapter);

    ResultDTO<List<String>> uploadPics(HttpServletRequest request, MangaAddMangaDetailVO mangaDetail, MultipartFile pics[]);

    UploadDTO upload(String fileName, MultipartFile[] pics);

    ResultDTO<UploadParamsDTO> getUploadParams(UploadParamsDTO dto);

    ResultDTO setUploadParams(HttpServletRequest request, UploadParamsDTO dto);
}
