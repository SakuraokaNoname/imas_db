package com.db.imas.constans;

/**
 * @Author noname
 * @Date 2021/7/27 16:42
 * @Version 1.0
 */
public enum MangaType {

    U149(1,"U149"),
    WWG(2,"WWG"),
    ENSEMBLE(3,"Ensemble"),
    A20(4,"A20");


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

    public static int getMangaType(String type){
        for(MangaType mangaType:MangaType.values()){
            if(type.equals(mangaType.type)){
                return mangaType.id;
            }
        }
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void main(String[] args) {
        System.out.println(getMangaType(2));
    }
}
