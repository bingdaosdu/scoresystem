package com.gde.integral.service.common.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GDE社区问答实体类
 *
 * @author ~
 * @date 2019/6/25
 */
@Entity
@Table(name = "gdeblogbestcomments")
public class GdeQa {

    @Id
    private String tid;

    /**
     * 提问人姓名
     */
    @Column(name = "ask")
    private String questioner;

    /**
     * 提问人w3id
     */
    @Column(name = "ask_id")
    private String questionerW3id;

    /**
     * 提问时间
     */
    @Column(name = "publish")
    private String questionTime;

    /**
     * 最佳答复者
     */
    @Column(name = "rep_name")
    private String bestAnswer;

    /**
     * 最佳答复者w3id
     */
    @Column(name = "rep_w3id")
    private String bestAnswerW3id;

    /**
     * 最佳答复时间
     */
    @Column(name = "rep_time")
    private String bestAnswerTime;

    /**
     * 问答帖浏览量
     */
    private int visitNum;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getQuestioner() {
        return questioner;
    }

    public void setQuestioner(String questioner) {
        this.questioner = questioner;
    }

    public String getQuestionerW3id() {
        return questionerW3id;
    }

    public void setQuestionerW3id(String questionerW3id) {
        this.questionerW3id = questionerW3id;
    }

    public String getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(String questionTime) {
        this.questionTime = questionTime;
    }

    public String getBestAnswer() {
        return bestAnswer;
    }

    public void setBestAnswer(String bestAnswer) {
        this.bestAnswer = bestAnswer;
    }

    public String getBestAnswerW3id() {
        return bestAnswerW3id;
    }

    public void setBestAnswerW3id(String bestAnswerW3id) {
        this.bestAnswerW3id = bestAnswerW3id;
    }

    public String getBestAnswerTime() {
        return bestAnswerTime;
    }

    public void setBestAnswerTime(String bestAnswerTime) {
        this.bestAnswerTime = bestAnswerTime;
    }

    public int getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
    }

    @Override
    public String toString() {
        return "GdeQa{" +
                "tid='" + tid + '\'' +
                ", questioner='" + questioner + '\'' +
                ", questionerW3id='" + questionerW3id + '\'' +
                ", questionTime='" + questionTime + '\'' +
                ", bestAnswer='" + bestAnswer + '\'' +
                ", bestAnswerW3id='" + bestAnswerW3id + '\'' +
                ", bestAnswerTime='" + bestAnswerTime + '\'' +
                ", visitNum=" + visitNum +
                '}';
    }
}
