package test.manga;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @Author noname
 * @Date 2021/7/1 15:25
 * @Version 1.0
 */
public class TestDemo1 {

    public static void main(String[] args) {
        // 1.BCrypt.gensalt() 生成盐值并保存
        // 2.BCrypt.hashpw(pwd,salt) 生成加密后的密码并保存
        // 3.BCrypt.checkpw(pwd,salt) 判断密码是否正确
//        System.out.println(BCrypt.hashpw("woshi54clt",BCrypt.gensalt()));
//        "$2a$10$ivwVN6OX0NVhed.8Ntvr8epHsosbBk9TfpsIWiUL3o14kINW2CgiO"
        System.out.println(BCrypt.checkpw("woshi54clt1","$2a$10$ivwVN6OX0NVhed.8Ntvr8epHsosbBk9TfpsIWiUL3o14kINW2CgiO"));
    }

}
