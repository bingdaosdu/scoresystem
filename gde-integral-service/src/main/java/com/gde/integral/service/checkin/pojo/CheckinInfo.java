package com.gde.integral.service.checkin.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 成员签到信息实体类
 *
 * @author ~
 * @date 2019/6/26
 */
@Entity
@Table(name = "checkin_info")
public class CheckinInfo {

    @Id
    private String w3id;

    private String name;

    /**
     * 连续签到次数
     */
    private int checkinCount;

    /**
     * 总计签到次数
     */
    private int checkinTotal;

    /**
     * 最后一次签到时间
     */
    private Date lastTime;

    /**
     * 第一次签到时间
     */
    private Date firstTime;

    public String getW3id() {
        return w3id;
    }

    public void setW3id(String w3id) {
        this.w3id = w3id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCheckinCount() {
        return checkinCount;
    }

    public void setCheckinCount(int checkinCount) {
        this.checkinCount = checkinCount;
    }

    public int getCheckinTotal() {
        return checkinTotal;
    }

    public void setCheckinTotal(int checkinTotal) {
        this.checkinTotal = checkinTotal;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }
}
