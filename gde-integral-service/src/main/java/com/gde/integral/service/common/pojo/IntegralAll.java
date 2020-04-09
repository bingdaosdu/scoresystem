package com.gde.integral.service.common.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 成员总积分实体类
 *
 * @author ~
 * @date 2019/6/25
 */
@Entity
@Table(name = "integral_all")
public class IntegralAll {

    /**
     * w3id为主键
     */
    @Id
    private String w3id;

    /**
     * 成员姓名
     */
    private String name;

    /**
     * 成员总积分
     */
    private double integralTotal;

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

    public double getIntegralTotal() {
        return integralTotal;
    }

    public void setIntegralTotal(double integralTotal) {
        this.integralTotal = integralTotal;
    }

    @Override
    public String toString() {
        return "IntegralAll{" +
                "w3id='" + w3id + '\'' +
                ", name='" + name + '\'' +
                ", integralTotal=" + integralTotal +
                '}';
    }
}
