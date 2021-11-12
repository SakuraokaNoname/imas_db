package com.db.imas.model.dto;

/**
 * @Author noname
 * @Date 2021/11/9 10:16
 * @Version 1.0
 */
public class ImasAccessCountDTO {

    private Integer todayAccessCount;
    private Integer yesterdayAccessCount;

    public Integer getTodayAccessCount() {
        return todayAccessCount;
    }

    public void setTodayAccessCount(Integer todayAccessCount) {
        this.todayAccessCount = todayAccessCount;
    }

    public Integer getYesterdayAccessCount() {
        return yesterdayAccessCount;
    }

    public void setYesterdayAccessCount(Integer yesterdayAccessCount) {
        this.yesterdayAccessCount = yesterdayAccessCount;
    }
}
