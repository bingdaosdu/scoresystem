package com.gde.integral.service.common.entity;

import java.util.List;

/**
 * 分页结果类
 * @param <T>
 *
 * @author ~
 * @date 2019/06/12
 */
public class PageResult<T> {

    /**
     * 总数据条数
     */
    private Long total;

    /**
     * 当前所需页面的数据集合
     */
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}