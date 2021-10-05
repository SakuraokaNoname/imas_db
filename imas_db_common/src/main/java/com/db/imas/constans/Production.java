package com.db.imas.constans;

/**
 * @Author noname
 * @Date 2021/9/23 10:44
 * @Version 1.0
 */
public enum Production {

    IMAS_765(1,"765as"),
    IMAS_346(2,"346"),
    IMAS_MILLION(3,"765million"),
    IMAS_315(4,"315"),
    IMAS_283(5,"283");

    Production(int id, String productionName) {
        this.id = id;
        this.productionName = productionName;
    }

    private int id;
    private String productionName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }
}
