package com.db.imas.utils;

import java.util.Map;

/**
 * @Author noname
 * @Date 2021/12/31 16:01
 * @Version 1.0
 */
public class TemplateReplaceUtil {

    /**
     * 模板替换，如：
     * @param template 你好{name}, 我在{do}
     * @param map name = czt, do = 做什么
     * @return
     */
    public static String replace (String template, Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return template;
        }
        // 遍历字符串
        for (int i = 0; i < template.length(); i++) {
            char c = template.charAt(i);
            String concat = "";
            //定位字符{
            if (c == '{') {
                int temp = i;
                char next;
                do {
                    //获取下个值为}跳出
                    next = template.charAt(++temp);
                    if (next == '}') {
                        break;
                    }
                    concat += next;
                } while (i <= template.length() - 1);
                String value = map.get(concat);
                if (value != null) {
                    template = template.replace(template.substring(i, temp + 1), value);
                }
            }
        }
        return template;
    }
}