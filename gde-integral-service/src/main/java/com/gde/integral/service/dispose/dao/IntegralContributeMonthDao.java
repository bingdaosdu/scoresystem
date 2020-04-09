package com.gde.integral.service.dispose.dao;

import com.gde.integral.service.common.pojo.IntegralContributeMonth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 月度贡献积分数据访问接口
 *
 * @author ~
 * @date 2019/6/25
 */
public interface IntegralContributeMonthDao extends JpaRepository<IntegralContributeMonth, Integer> {

    /**
     * 获取指定月份的月度贡献积分
     *
     * @param startDate
     * @return
     */
    List<IntegralContributeMonth> findByStartDate(String startDate);

}
