package com.gde.integral.service.common.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * gde社区成员月度贡献信息实体类
 *
 * @author ~
 * @date 2019/6/25
 */
@Entity
@Table(name = "contribute_info_month")
public class ContributeInfoMonth {

    @Id
    private int uid;

    /**
     * 成员姓名
     */
    private String name;

    /**
     * 成员w3id
     */
    private String w3id;

    /**
     * 社区讨论
     */
    private int groupDiscuss;

    /**
     * 社区讨论回复
     */
    private int groupDiscussReply;

    /**
     * 社区讨论精华
     */
    private int groupDiscussDigest;

    /**
     * 社区讨论分享
     */
    private int groupDiscussShare;

    /**
     * 社区求助
     */
    private int groupHelp;

    /**
     * 社区求助回复
     */
    private int groupHelpReply;

    /**
     * 社区求助精华
     */
    private int groupHelpDigest;

    /**
     * 社区博文总数
     */
    private int groupBlog;

    /**
     * 社区原创博文
     */
    private int groupBlogOriginal;

    /**
     * 社区精华博文
     */
    private int groupBlogDigest;

    /**
     * 社区博文分享
     */
    private int groupBlogShare;

    /**
     * 数据开始日期
     */
    private String startDate;

    /**
     * 数据结束日期
     */
    private String endDate;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getGroupDiscuss() {
        return groupDiscuss;
    }

    public void setGroupDiscuss(int groupDiscuss) {
        this.groupDiscuss = groupDiscuss;
    }

    public int getGroupDiscussReply() {
        return groupDiscussReply;
    }

    public void setGroupDiscussReply(int groupDiscussReply) {
        this.groupDiscussReply = groupDiscussReply;
    }

    public int getGroupDiscussDigest() {
        return groupDiscussDigest;
    }

    public void setGroupDiscussDigest(int groupDiscussDigest) {
        this.groupDiscussDigest = groupDiscussDigest;
    }

    public int getGroupDiscussShare() {
        return groupDiscussShare;
    }

    public void setGroupDiscussShare(int groupDiscussShare) {
        this.groupDiscussShare = groupDiscussShare;
    }

    public int getGroupHelp() {
        return groupHelp;
    }

    public void setGroupHelp(int groupHelp) {
        this.groupHelp = groupHelp;
    }

    public int getGroupHelpReply() {
        return groupHelpReply;
    }

    public void setGroupHelpReply(int groupHelpReply) {
        this.groupHelpReply = groupHelpReply;
    }

    public int getGroupHelpDigest() {
        return groupHelpDigest;
    }

    public void setGroupHelpDigest(int groupHelpDigest) {
        this.groupHelpDigest = groupHelpDigest;
    }

    public int getGroupBlogOriginal() {
        return groupBlogOriginal;
    }

    public void setGroupBlogOriginal(int groupBlogOriginal) {
        this.groupBlogOriginal = groupBlogOriginal;
    }

    public int getGroupBlogDigest() {
        return groupBlogDigest;
    }

    public void setGroupBlogDigest(int groupBlogDigest) {
        this.groupBlogDigest = groupBlogDigest;
    }

    public int getGroupBlogShare() {
        return groupBlogShare;
    }

    public void setGroupBlogShare(int groupBlogShare) {
        this.groupBlogShare = groupBlogShare;
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

    public int getGroupBlog() {
        return groupBlog;
    }

    public void setGroupBlog(int groupBlog) {
        this.groupBlog = groupBlog;
    }

    @Override
    public String toString() {
        return "ContributeInfoMonth{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", w3id='" + w3id + '\'' +
                ", groupDiscuss=" + groupDiscuss +
                ", groupDiscussReply=" + groupDiscussReply +
                ", groupDiscussDigest=" + groupDiscussDigest +
                ", groupDiscussShare=" + groupDiscussShare +
                ", groupHelp=" + groupHelp +
                ", groupHelpReply=" + groupHelpReply +
                ", groupHelpDigest=" + groupHelpDigest +
                ", groupBlog=" + groupBlog +
                ", groupBlogOriginal=" + groupBlogOriginal +
                ", groupBlogDigest=" + groupBlogDigest +
                ", groupBlogShare=" + groupBlogShare +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
