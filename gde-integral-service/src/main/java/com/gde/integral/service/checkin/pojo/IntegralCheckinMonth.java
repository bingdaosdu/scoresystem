package com.gde.integral.service.checkin.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 月度签到积分实体类
 *
 * @author ~
 * @date 2019/6/26
 */
@Entity
@Table(name = "integral_checkin_month")
public class IntegralCheckinMonth {

    @Id
    private int tId;

    /**
     * 成员名字
     */
    private String name;

    /**
     * 成员w3id
     */
    private String w3id;

    /**
     * 成员月度签到积分
     */
    private double monthIntegral;

    /**
     * 数据开始日期
     */
    private String startDate;

    /**
     * 数据结束日期
     */
    private String endDate;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getW3id() {
        return w3id;
    }

    public void setW3id(String w3id) {
        this.w3id = w3id;
    }

    public double getMonthIntegral() {
        return monthIntegral;
    }

    public void setMonthIntegral(double monthIntegral) {
        this.monthIntegral = monthIntegral;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
