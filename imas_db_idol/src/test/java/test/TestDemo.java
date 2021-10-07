package test;

import com.db.imas.ImasDbIdolApplication;
import com.db.imas.service.ImasIdolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author noname
 * @create 2021/10/7
 */
@SpringBootTest(classes = ImasDbIdolApplication.class)
public class TestDemo {

    @Autowired
    private ImasIdolService imasIdolService;

    @Test
    public void test(){
//        imasIdolService.addMangaIdol(null,119,175);
    }

}
