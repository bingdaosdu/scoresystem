package com.gde.integral.service.common.pojo;

import javax.persistence.*;

/**
 * 月度积分(总) 实体类
 *
 * @author ~
 * @date 2019/6/26
 */
@Entity
@Table(name = "integral_monthly")
public class IntegralMonthly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 成员w3id
     */
    private String w3id;

    /**
     * 成员姓名
     */
    private String name;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
