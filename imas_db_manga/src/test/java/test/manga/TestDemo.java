package test.manga;

import com.db.imas.ImasDbMangaApplication;
import com.db.imas.dao.ImasIpDao;
import com.db.imas.dao.MangaDao;
import com.db.imas.model.dto.MangaPictureDownloadDTO;
import com.db.imas.model.entity.ImasAccessIP;
import com.db.imas.model.entity.ImasIP;
import com.db.imas.service.MangaAccessService;
import com.db.imas.utils.Constants;
import com.db.imas.utils.IPUtil;
import com.db.imas.utils.OSSUtil;
import com.db.imas.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

/**
 * @Author noname
 * @Date 2021/7/1 15:25
 * @Version 1.0
 */
//@SpringBootTest
@SpringBootTest(classes = ImasDbMangaApplication.class)
public class TestDemo {

//    @Autowired
//    private OSSUtil ossUtil;

    @Autowired
    private MangaAccessService mangaAccessService;

    @Autowired
    private ImasIpDao imasIpDao;

    @Autowired
    private MangaDao mangaDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private OSSUtil ossUtil;

//    @Test
//    public void synOSS(){
//        List<MangaPictureDownloadDTO> picList = mangaDao.selectMangaPicture();
//        for(MangaPictureDownloadDTO pic : picList){
//            String folderUrl = "D:/data/manga/" + getMangaName(pic.getMid()) + "/" + pic.getSid() + "/";
//            String fileName = "manga/" + getMangaName(pic.getMid()) + "/" + pic.getSid() + "/" + pic.getImg();
//            String path = folderUrl + pic.getImg();
//            File folder = new File(folderUrl);
//
//            if(!folder.exists()){
//                folder.mkdirs();
//            }
//            if(!new File(path).exists()){
//                System.out.println("进入,下载");
//                ossUtil.download(fileName,path);
//                return;
//            }
//            System.out.println("进入,不下载");
////            String path = "/data/manga" + pic.getMid() + "/" + pic.getSid() + "/" + pic.getImg();
//            return;
//        }
//    }

    public String getMangaName(int mid){
        switch (mid){
            case 1:
                return "u149";
            case 2:
                return "wwg";
        }
        return "";
    }

//    @Test
//    public void insertAccessIp() throws ParseException, IOException {
//        List<ImasAccessIP> addAccessIPList = new ArrayList<>();
//        addAccessIPList = getAccessIP(addAccessIPList, Constants.ACCESS_PREFIX);
//        addAccessIPList = getAccessIP(addAccessIPList, Constants.ACCESS_ERROR_PREFIX);
//        for(ImasAccessIP imasAccessIP : addAccessIPList){
//            mangaAccessService.insertIPData(imasAccessIP);
//        }
//    }

    @Test
    public void selectIp(){
//        List<ImasIP> ipList = imasIpDao.selectJapanAndKoreaIP();
//        long startTime = System.currentTimeMillis();    //获取开始时间
//        for(ImasIP ip : ipList){
//            String str = ip.getIp0() + "-" + ip.getIp255();
//            if(IPUtil.ipIsValid(str, "211.18.234.0")){
//                System.out.println("ip异常");
//            }
//        }
//        long endTime = System.currentTimeMillis();    //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }

//    @Test
//    public void insertIpDB(){
//        long startTime = System.currentTimeMillis();    //获取开始时间
//
//        InputStream is = this.getClass().getClassLoader().getResourceAsStream("ip_db.txt");
//        try(Scanner scanner = new Scanner(is,"GBK")) {
//            while (scanner.hasNextLine()) {
//                String str = scanner.nextLine();
//                List<String> ipInfo = IPUtil.getIpInfo(str);
//                ImasIP ip = new ImasIP();
//                ip.setIp0(ipInfo.get(0));
//                ip.setIp255(ipInfo.get(1));
//                ip.setAddr(ipInfo.get(2));
//                System.out.println(str);
//                System.out.println(ipInfo.toString());
//                int result = mangaAccessService.addIpInfo(ip);
//                System.out.println(result);
//                Thread.sleep(5);
//            }
//        } catch (Exception e) {
//            System.out.println("error");
//        }
////        System.out.println(IPUtil.ipIsValid("1.14.128.0-1.14.213.255", "1.14.138.255"));
//        long endTime = System.currentTimeMillis();    //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
//    }

    @Test
    public void test1(){
//        long startTime = System.currentTimeMillis();    //获取开始时间
//        for (int i = 0;i < 50; i++){
//            ossUtil.getFileList();
//        }
//        List<String> arr = new ArrayList<>();
//        arr.add("003.jpg");
//        arr.add("001.jpg");
//        arr.add("002.jpg");
//        Collections.sort(arr);
//        System.out.println(arr.toString());
//        MangaSearchMangaSubVO vo = new MangaSearchMangaSubVO();
//        vo.setIdolList(",24,4,15,31,");
//        ResultDTO<List<MangaSubSearchDTO>> dto = mangaService.searchManga(vo);
//
//        long endTime = System.currentTimeMillis();    //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
//
//        for(MangaSubSearchDTO dto1:dto.getData()){
//            System.out.println(dto1.getSubTitle());
//        }

    }
}

