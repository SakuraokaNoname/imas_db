package test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.db.imas.ImasDbCommonApplication;
import com.db.imas.utils.OSSBuilding;
import com.db.imas.utils.OSSUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    @Test
    public void test1(){

        LocalDateTime nowTime = LocalDateTime.now();
        String zikoku = String.valueOf(nowTime);

        String zikoku_substring = zikoku.substring(10,12);

        System.out.println(zikoku);
        System.out.println(zikoku_substring);



        long startTime = System.currentTimeMillis();    //获取开始时间
//        for (int i = 0;i < 50; i++){
//            ossUtil.getFileList();
//        }
        List<String> arr = new ArrayList<>();
        arr.add("003.jpg");
        arr.add("001.jpg");
        arr.add("002.jpg");
        Collections.sort(arr);
        System.out.println(arr.toString());

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }

    @Test
    public void Test2(){
        String string = "2021-08-12";
        System.out.println();
    }
}

