package com.db.imas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ImasDbMangaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImasDbMangaApplication.class, args);
    }

}
