package test;

import com.db.imas.ImasDbCommonApplication;
import com.db.imas.model.entity.ImasIP;
import com.db.imas.service.MangaAccessService;
import com.db.imas.utils.IPUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.InputStream;
import java.util.List;
import java.util.*;

/**
 * @Author noname
 * @Date 2021/7/1 15:25
 * @Version 1.0
 */
//@SpringBootTest
@SpringBootTest(classes = ImasDbCommonApplication.class)
public class TestDemo {

//    @Autowired
//    private OSSUtil ossUtil;

    @Autowired
    private MangaAccessService mangaAccessService;

    @Test
    public void insertIpDB(){
        long startTime = System.currentTimeMillis();    //获取开始时间

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("ip_db.txt");
        try(Scanner scanner = new Scanner(is,"GBK")) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                List<String> ipInfo = IPUtil.getIpInfo(str);
                ImasIP ip = new ImasIP();
                ip.setIp0(ipInfo.get(0));
                ip.setIp255(ipInfo.get(1));
                ip.setAddr(ipInfo.get(2));
                System.out.println(str);
                System.out.println(ipInfo.toString());
                int result = mangaAccessService.addIpInfo(ip);
                System.out.println(result);
                Thread.sleep(5);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
//        System.out.println(IPUtil.ipIsValid("1.14.128.0-1.14.213.255", "1.14.138.255"));
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }

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

