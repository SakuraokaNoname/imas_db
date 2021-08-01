package com.db.imas.controller;

import com.db.imas.model.dto.MangaDTO;
import com.db.imas.model.dto.MangaDetailDTO;
import com.db.imas.model.dto.MangaSubDTO;
import com.db.imas.model.dto.ResultDTO;
import com.db.imas.model.vo.MangaAddMangaDetailVO;
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

    @GetMapping("chapter/{mid}/{chapter}")
    public ResultDTO changeChapter(@PathVariable Integer mid,@PathVariable Integer chapter){
        return mangaService.changeChapter(mid,chapter);
    }

    @PostMapping("uploadPics")
    public ResultDTO<List<String>> uploadPics(HttpServletRequest request, @RequestParam(value = "mangaDetail")MangaAddMangaDetailVO mangaDetail, @RequestParam(value = "pics") MultipartFile[] pics){
        return mangaService.uploadPics(request,mangaDetail,pics);
    }

}
