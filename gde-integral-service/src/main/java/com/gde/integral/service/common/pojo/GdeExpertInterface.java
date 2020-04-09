package com.gde.integral.service.common.pojo;

/**
 * GDE社区达人接口
 *
 * @author ~
 * @date 2019/7/1
 */
public interface GdeExpertInterface {

    /**
     * 姓名
     *
     * @return 姓名
     */
    String getName();

    /**
     * w3id
     *
     * @return w3id
     */
    String getW3id();

    /**
     * 数量
     *
     * @return 数量
     */
    int getAmount();

    /**
     * 访问量
     *
     * @return 访问量
     */
    int getVisitTotal();

    /**
     * toString
     *
     * @return toString
     */
    default String toStringInfo() {
        return "name=" + getName() + "; w3id=" + getW3id()
                +"; amount=" + getAmount() + ";visitTotal=" + getVisitTotal();
    }

}
