package com.gde.integral.service.dispose.dao;

import com.gde.integral.service.common.pojo.IntegralExpertMonthly;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 月度达人积分数据访问接口
 *
 * @author ~
 * @date 2019/7/1
 */
public interface IntegralExpertMonthlyDao extends JpaRepository<IntegralExpertMonthly, Integer> {

    /**
     * 获取指定月份的月度达人积分数据
     *
     * @param startDate 指定月份开始日期
     * @return 月度达人积分数据列表
     */
    List<IntegralExpertMonthly> findByStartDate(String startDate);

}
