package com.db.imas.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author noname
 * @Date 2021/7/1 16:25
 * @Version 1.0
 */
@Configuration
public class OSSBuilding {

    private static String endpoint;

    private static String accessKeyId;

    private static String accessKeySecret;
    @Value("${oss.endpoint}")
    public void setEndpoint(String endpoint) {
        OSSBuilding.endpoint = endpoint;
    }
    @Value("${oss.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        OSSBuilding.accessKeyId = accessKeyId;
    }
    @Value("${oss.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        OSSBuilding.accessKeySecret = accessKeySecret;
    }

    private static volatile OSS oss;

    public static OSS getOSS() {
        if (oss == null) {
            synchronized (OSSClient.class) {
                if (oss == null) {
                    System.out.println("================="+endpoint);
                    oss = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
                    Runtime.getRuntime().addShutdownHook(new Thread(() -> oss.shutdown()));
                }
            }
        }
        return oss;
    }
}
