package com.gde.integral.service.common.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 月度积分(根据最佳答案)实体类
 *
 * @author ~
 * @date 2019/6/25
 */
@Entity
@Table(name = "integral_best_answer_month")
public class IntegralBestAnswerMonth {

    @Id
    private int tId;

    /**
     * 成员姓名
     */
    private String name;

    /**
     * 成员w3id
     */
    private String w3id;

    /**
     * 成员月度积分
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

    @Override
    public String toString() {
        return "IntegralBestAnswerMonth{" +
                "tId=" + tId +
                ", name='" + name + '\'' +
                ", w3id='" + w3id + '\'' +
                ", monthIntegral=" + monthIntegral +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
