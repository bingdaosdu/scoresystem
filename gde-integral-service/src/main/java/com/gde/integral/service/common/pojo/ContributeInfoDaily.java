package com.gde.integral.service.common.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GDE社区每天贡献值信息
 *
 * @author ~
 * @date 2019/6/29
 */
@Entity
@Table(name = "contribute_info_day")
public class ContributeInfoDaily {

    @Id
    private String uid;

    /**
     * 成员姓名
     */
    private String name;

    /**
     * 成员w3id
     */
    private String w3id;

    /**
     * 社区求助
     */
    private int groupHelp;

    /**
     * 社区原创博文
     */
    private int groupBlogOriginal;

    /**
     * 数据记录日期
     */
    private String startDate;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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

    public int getGroupHelp() {
        return groupHelp;
    }

    public void setGroupHelp(int groupHelp) {
        this.groupHelp = groupHelp;
    }

    public int getGroupBlogOriginal() {
        return groupBlogOriginal;
    }

    public void setGroupBlogOriginal(int groupBlogOriginal) {
        this.groupBlogOriginal = groupBlogOriginal;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
