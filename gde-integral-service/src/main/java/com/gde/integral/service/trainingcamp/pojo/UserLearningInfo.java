package com.gde.integral.service.trainingcamp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户新手训练营学习信息
 *
 * @author ~
 * @date 2019/7/3
 */
@Entity
@Table(name = "user_of_learning")
public class UserLearningInfo {

    @Id
    @Column(name = "u_id")
    private int id;

    /**
     * w3id
     */
    @Column(name = "u_w3id")
    private String w3id;

    /**
     * 学习时间
     */
    @Column(name = "u_record_time")
    private Date recordTime;

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

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
