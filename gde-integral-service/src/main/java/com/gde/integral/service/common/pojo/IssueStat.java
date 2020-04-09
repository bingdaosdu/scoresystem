package com.gde.integral.service.common.pojo;

/**
 * 问题分析数据实体类
 *
 * @author ~
 * @date 2019/7/1
 */
public class IssueStat {

    /**
     * 已提出问题总数
     */
    private int issueTotal;

    /**
     * 已解决问题数量
     */
    private int repliedCount;

    /**
     * 未解决问题数量
     */
    private int unansweredCount;

    public int getIssueTotal() {
        return issueTotal;
    }

    public void setIssueTotal(int issueTotal) {
        this.issueTotal = issueTotal;
    }

    public int getRepliedCount() {
        return repliedCount;
    }

    public void setRepliedCount(int repliedCount) {
        this.repliedCount = repliedCount;
    }

    public int getUnansweredCount() {
        return unansweredCount;
    }

    public void setUnansweredCount(int unansweredCount) {
        this.unansweredCount = unansweredCount;
    }
}
