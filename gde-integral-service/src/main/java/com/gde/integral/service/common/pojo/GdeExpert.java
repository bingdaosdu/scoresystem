package com.gde.integral.service.common.pojo;

/**
 * GDE社区达人实体类
 *
 * @author ~
 * @date 2019/7/1
 */
public class GdeExpert {

    /**
     * 姓名
     */
    private String name;

    /**
     * w3id
     */
    private String w3id;

    /**
     * 求助或最佳答复数量
     */
    private int amount;

    /**
     * 帖子访问量
     */
    private int visit;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    @Override
    public String toString() {
        return "GdeExpert{" +
                "name='" + name + '\'' +
                ", w3id='" + w3id + '\'' +
                ", amount=" + amount +
                ", visit=" + visit +
                '}';
    }

}
