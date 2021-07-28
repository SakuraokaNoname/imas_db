package com.db.imas.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.*;

/**
 * @Author noname
 * @Date 2021/7/1 15:14
 * @Version 1.0
 * */


@Component
public class OSSUtil {

    @Value("${oss.bucketName}")
    private String bucketName;

    public void upload(String path,InputStream inputStream){
        OSSBuilding.getOSSClient().putObject(bucketName,path,inputStream);
    }
//
//    public void download() throws IOException {
//        OSSObject ossObject = ossClient.getObject(bucketName,"img/timg.jpg");
//        InputStream content = ossObject.getObjectContent();
//        if(!ObjectUtils.isEmpty(ossObject)){
//            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
//            while(true){
//                String line = reader.readLine();
//                if(line == null) break;
//                System.out.println("\n" + line);
//            }
//        }else{
//            System.out.println("对象为空");
//        }
//    }

    public void getFileList(){
        ObjectListing objectListing = OSSBuilding.getOSSClient().listObjects(bucketName);
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }
    }

    public boolean isExist(){
        return false;
    }
}
