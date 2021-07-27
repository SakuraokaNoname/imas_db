package com.db.imas.constans;

/**
 * @Author noname
 * @Date 2021/7/27 16:42
 * @Version 1.0
 */
public enum MangaType {

    MANGA_U149(1,"u149"),
    MANGA_WWG(2,"wwg");

    private int id;
    private String type;

    MangaType(int id,String type){
        this.id = id;
        this.type = type;
    }

    public static String getMangaType(int id){
        for(MangaType type:MangaType.values()){
            if(type.id == id){
                return type.type;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getMangaType(2));
    }
}
