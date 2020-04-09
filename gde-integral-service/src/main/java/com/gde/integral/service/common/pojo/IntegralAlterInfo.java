package com.gde.integral.service.common.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * 积分变更实体类
 *
 * @author ~
 * @date 2019/6/25
 */
@Entity
@Table(name = "integral_alter_info")
public class IntegralAlterInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tId;

    /**
     * w3id
     */
    private String w3id;

    /**
     * 成员姓名
     */
    private String name;

    /**
     * 变更前积分
     */
    private double alterBefore;

    /**
     * 变更积分值
     */
    private double alterValue;

    /**
     * 变更后积分
     */
    private double alterLast;

    /**
     * 变更理由
     */
    private String alterReason;

    /**
     * 数据记录日期
     */
    private Date recordTime;

    /**
     * 操作者
     */
    private String operator;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
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

    public double getAlterBefore() {
        return alterBefore;
    }

    public void setAlterBefore(double alterBefore) {
        this.alterBefore = alterBefore;
    }

    public double getAlterValue() {
        return alterValue;
    }

    public void setAlterValue(double alterValue) {
        this.alterValue = alterValue;
    }

    public double getAlterLast() {
        return alterLast;
    }

    public void setAlterLast(double alterLast) {
        this.alterLast = alterLast;
    }

    public String getAlterReason() {
        return alterReason;
    }

    public void setAlterReason(String alterReason) {
        this.alterReason = alterReason;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "IntegralAlterInfo{" +
                "tId=" + tId +
                ", w3id='" + w3id + '\'' +
                ", name='" + name + '\'' +
                ", alterBefore=" + alterBefore +
                ", alterValue=" + alterValue +
                ", alterLast=" + alterLast +
                ", alterReason='" + alterReason + '\'' +
                ", recordTime=" + recordTime +
                '}';
    }
}
