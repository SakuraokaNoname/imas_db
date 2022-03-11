package test;

import com.db.imas.constans.NoticeEnum;
import com.db.imas.util.TemplateReplaceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: chenlingtong
 * @CreateDate: 2022/3/9 10:36
 * @Version: 1.0.0
 * @Company: 名雕装饰股份有限公司
 */
public class TestApplication {

    public static void main(String[] args) {
        Map<String,String> replaceMap = new HashMap<>();
        replaceMap.put("manga","U149");
        replaceMap.put("mangaTitle","title");
        replaceMap.put("dantalions","noname");
        replaceMap.put("translators","yasuhara");
        System.out.println(TemplateReplaceUtil.replace(NoticeEnum.NEW_MANGA.getContent(),replaceMap));
    }

}
