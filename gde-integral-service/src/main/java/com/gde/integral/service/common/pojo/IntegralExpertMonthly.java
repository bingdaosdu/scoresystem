package com.gde.integral.service.common.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 月度达人积分实体类
 *
 * @author ~
 * @date 2019/7/1
 */
@Entity
@Table(name = "integral_expert_monthly")
public class IntegralExpertMonthly {

    public IntegralExpertMonthly() {
    }

    public IntegralExpertMonthly(String name, String w3id, double integralMonthly, String startDate, String endDate) {
        this.name = name;
        this.w3id = w3id;
        this.integralMonthly = integralMonthly;
        this.startDate = startDate;
        this.endDate = endDate;
    }

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
    private double integralMonthly;

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

    public double getIntegralMonthly() {
        return integralMonthly;
    }

    public void setIntegralMonthly(double integralMonthly) {
        this.integralMonthly = integralMonthly;
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
        return "IntegralExpertMonthly{" +
                "tId=" + tId +
                ", name='" + name + '\'' +
                ", w3id='" + w3id + '\'' +
                ", integralMonthly=" + integralMonthly +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
