package com.gde.integral.service.common.dao;

import com.gde.integral.service.common.pojo.IntegralAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户总积分数据访问接口
 *
 * @author ~
 * @date 2019/6/25
 */
public interface IntegralAllDao extends JpaRepository<IntegralAll, String> {

    /**
     * 查询所有数据并根据总积分排序(降序)
     *
     * @return 返回所有积分数据集合
     */
    List<IntegralAll> findByOrderByIntegralTotalDesc();

}
