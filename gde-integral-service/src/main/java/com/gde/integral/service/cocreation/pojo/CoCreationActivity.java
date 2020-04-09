package com.gde.integral.service.cocreation.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 共创活动实体类
 *
 * @author ~
 * @date 2019/7/5
 */
@Entity
@Table(name = "gongchuang")
public class CoCreationActivity {

    @Id
    private String tid;

    /**
     * 活动帖标题
     */
    private String title;

    /**
     * 活动帖链接
     */
    private String link;

    /**
     * 活动帖回复数量
     */
    private int replyNum;

    /**
     * 活动帖浏览量
     */
    private int viewNum;

    /**
     * 活动帖收藏量
     */
    private int likeNum;

    /**
     * 活动帖发布时间
     */
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date publishTime;

    /**
     * 共创活动图片地址
     */
    private String imgUrl;

    /**
     * 类型
     */
    private String category;

    /**
     * 宣传标题
     */
    private String promoteTitle;

    /**
     * 针对用户
     */
    private String aimUser;

    /**
     * 共创活动开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date startDate;

    /**
     * 共创活动结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date endDate;

    /**
     * 是否在前端展示
     */
    private String visible;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPromoteTitle() {
        return promoteTitle;
    }

    public void setPromoteTitle(String promoteTitle) {
        this.promoteTitle = promoteTitle;
    }

    public String getAimUser() {
        return aimUser;
    }

    public void setAimUser(String aimUser) {
        this.aimUser = aimUser;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }
}
