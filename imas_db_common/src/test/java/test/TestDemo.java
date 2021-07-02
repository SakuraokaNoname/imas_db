package test;

import com.aliyun.oss.OSS;
import com.db.imas.ImasDbCommonApplication;
import com.db.imas.utils.OSSUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author noname
 * @Date 2021/7/1 15:25
 * @Version 1.0
 */
@SpringBootTest(classes = ImasDbCommonApplication.class)
public class TestDemo {

    @Autowired
    private OSSUtil ossUtil;

    @Test
    public void test1(){
        ossUtil.getFileList();
    }

}
