package com.db.imas.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/9/8 14:19
 * @Version 1.0
 */
public class CollectionsUtil {

//    OFF_CHAT:group:99999998:11
    public static List<String> sort(List<String> list){
        List<String> newList = new ArrayList<>();
        String prefix = list.get(0).substring(0,list.get(0).lastIndexOf(":") + 1);
        int[] arr = new int[list.size()];
        for(int i = 0; i < arr.length; i++){
            String str = list.get(i);
            arr[i] = Integer.parseInt(str.substring(str.lastIndexOf(":") + 1));
        }
        Arrays.sort(arr);
        for(int id : arr){
            newList.add(prefix + id);
        }
        return newList;
    }

}
