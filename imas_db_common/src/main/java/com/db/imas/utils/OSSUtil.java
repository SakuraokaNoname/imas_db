package com.db.imas.utils;

import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
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

    // path: "D:\\localpath\\examplefile.txt"
    public void download(String fileName, String path){
        OSSBuilding.getOSSClient().getObject(new GetObjectRequest(bucketName, fileName),new File(path));
    }

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
