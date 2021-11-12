package com.db.imas.controller;

import com.db.imas.model.dto.*;
import com.db.imas.model.vo.MangaAddMangaDetailVO;
import com.db.imas.model.vo.MangaSearchMangaSubVO;
import com.db.imas.model.vo.UploadParamsVO;
import com.db.imas.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/6/11 16:29
 * @Version 1.0
 */
@RestController
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @GetMapping("list")
    public ResultDTO<List<MangaDTO>> getMangaList(){
        return mangaService.getMangaList();
    }

    @GetMapping("sublist/{id}")
    public ResultDTO<List<MangaSubDTO>> getMangaSubList(@PathVariable Integer id){
        return mangaService.getMangaSubList(id);
    }

    @GetMapping("detail/{id}")
    public ResultDTO<MangaDetailDTO> getMangaDetail(@PathVariable Integer id){
        return mangaService.getMangaDetail(id);
    }

    @GetMapping("chapter/{mid}/{orderId}/{type}")
    public ResultDTO changeChapter(@PathVariable Integer mid,@PathVariable Integer orderId,@PathVariable String type){
        return mangaService.changeChapter(mid,orderId,type);
    }

    @PostMapping("addManga")
    public ResultDTO addManga(HttpServletRequest request, @RequestBody MangaAddMangaDetailVO mangaDetail){
        return mangaService.addManga(request,mangaDetail);
    }

    @PostMapping("upload")
    public UploadDTO upload(@RequestParam("qqfilename")String fileName, @RequestParam(value = "pics") MultipartFile[] pics){
        return mangaService.upload(fileName,pics);
    }

    @PostMapping("uploadList")
    public ResultDTO<List<UploadParamsDTO>> getMidAndSidList(HttpServletRequest request){
        return mangaService.getMidAndSidList(request);
    }

    @PostMapping("setUploadParams")
    public ResultDTO setUploadParams(HttpServletRequest request ,@RequestBody UploadParamsVO vo){
        return mangaService.setUploadParams(request,vo);
    }

    @PostMapping("search")
    public ResultDTO<List<MangaSubSearchDTO>> searchMangaSubList(@RequestBody MangaSearchMangaSubVO vo){
        return mangaService.searchManga(vo);
    }

    @PostMapping("syn/oss")
    public ResultDTO<Integer> synOSSPicture(HttpServletRequest request){
        return mangaService.synOSSPictureDTO(request);
    }
}
