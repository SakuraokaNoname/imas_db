package com.db.imas.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author noname
 * @Date 2021/7/1 16:25
 * @Version 1.0
 * */


@PropertySource(value = "classpath:application.yml")
@Component
public class OSSBuilding {
    //volatile是Java提供的一种轻量级的同步机制,在并发编程中,也扮演着比较重要的角色.
    //同synchronized相比(synchronized通常称为重量级锁),volatile更轻量级,相比使用
    //synchronized所带来的庞大开销,倘若能恰当的合理的使用volatile,则wonderful
    private volatile static OSS client;

    private OSSBuilding(){}

    private volatile static OSSClientBuilder ossClientBuilder;

    private static String endpoint;

    private static String accessKeyId;

    private static String accessKeySecret;

    @Value("${oss.endpoint}")
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    @Value("${oss.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
    @Value("${oss.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    @Bean
    public static OSS getOSSClient(){
        if(client == null){
            synchronized(OSSBuilding.class){
                if(client==null){
                    client = getOSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
                }
            }
        }
        return client;
    }

    public static OSSClientBuilder getOSSClientBuilder(){
        if(ossClientBuilder == null){
            synchronized(OSSBuilding.class){
                if(ossClientBuilder==null){
                    ossClientBuilder = new OSSClientBuilder();
                }
            }
        }
        return ossClientBuilder;
    }

}